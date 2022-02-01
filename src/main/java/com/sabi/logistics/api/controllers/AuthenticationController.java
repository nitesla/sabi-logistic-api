package com.sabi.logistics.api.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sabi.framework.dto.requestDto.GeneratePassword;
import com.sabi.framework.dto.requestDto.LoginRequest;
import com.sabi.framework.dto.responseDto.AccessTokenWithUserDetails;
import com.sabi.framework.dto.responseDto.GeneratePasswordResponse;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.LockedException;
import com.sabi.framework.exceptions.UnauthorizedException;
import com.sabi.framework.loggers.LoggerUtil;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.PermissionRepository;
import com.sabi.framework.security.AuthenticationWithToken;
import com.sabi.framework.service.*;
import com.sabi.framework.utils.AuditTrailFlag;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.framework.utils.Utility;
import com.sabi.logistics.core.models.PartnerUser;
import com.sabi.logistics.service.repositories.PartnerCategoriesRepository;
import com.sabi.logistics.service.repositories.PartnerRepository;
import com.sabi.logistics.service.repositories.PartnerUserRepository;
import com.sabi.logistics.service.services.DriverPasswordService;
import com.sabi.logistics.service.services.PartnerCategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"authenticate")
public class AuthenticationController {
    private  static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Value("${login.attempts}")
    private int loginAttempts;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExternalTokenService externalTokenService;

    private final PartnerCategoriesService partnerCategoriesService;
    private final UserService userService;
    private final PartnerRepository partnerRepository;
    private final PartnerCategoriesRepository partnerCategoriesRepository;
    private final PartnerUserRepository partnerUserRepository;
    private final AuditTrailService auditTrailService;
    private final PermissionRepository permissionRepository;
    private final PermissionService permissionService;
    private final DriverPasswordService driverPasswordService;
    private final UserRoleService userRoleService;


    public AuthenticationController(PartnerCategoriesService partnerCategoriesService,UserService userService,PartnerRepository partnerRepository,
                                    PartnerCategoriesRepository partnerCategoriesRepository,PartnerUserRepository partnerUserRepository,
                                    AuditTrailService auditTrailService,PermissionRepository permissionRepository,
                                    PermissionService permissionService,DriverPasswordService driverPasswordService,
                                    UserRoleService userRoleService) {
        this.partnerCategoriesService = partnerCategoriesService;
        this.userService = userService;
        this.partnerRepository = partnerRepository;
        this.partnerCategoriesRepository = partnerCategoriesRepository;
        this.partnerUserRepository = partnerUserRepository;
        this.auditTrailService = auditTrailService;
        this.permissionRepository = permissionRepository;
        this.permissionService = permissionService;
        this.driverPasswordService = driverPasswordService;
        this.userRoleService = userRoleService;

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) throws JsonProcessingException {

        log.info(":::::::::: login Request %s:::::::::::::" + loginRequest.getUsername());
        String loginStatus;
        String ipAddress = Utility.getClientIp(request);
        User user = userService.loginUser(loginRequest);
        if (user != null) {
            if (user.isLoginStatus()) {
                //FIRST TIME LOGIN
                if (user.getPasswordChangedOn() == null) {
                    Response resp = new Response();
                    resp.setCode(CustomResponseCode.CHANGE_P_REQUIRED);
                    resp.setDescription("Change password Required, account has not been activated");
                    return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);//202
                }
                if (user.getIsActive()==false) {
                    Response resp = new Response();
                    resp.setCode(CustomResponseCode.FAILED);
                    resp.setDescription("User Account Deactivated, please contact Administrator");
                    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (user.getLoginAttempts() >= loginAttempts || user.getLockedDate() != null) {
                    // lock account after x failed attempts or locked date is not null
                    userService.lockLogin(user.getId());
                    throw new LockedException(CustomResponseCode.LOCKED_EXCEPTION, "This account has been locked, Kindly contact support");
                }
//                userService.validateGeneratedPassword(user.getId());
            } else {
                //update login failed count and failed login date
                loginStatus = "failed";
                auditTrailService
                        .logEvent(loginRequest.getUsername(), "Login by username :" + loginRequest.getUsername()
                                        + " " + loginStatus,
                                AuditTrailFlag.LOGIN, "Failed Login Request by :" + loginRequest.getUsername(),1, ipAddress);
                userService.updateFailedLogin(user.getId());
                throw new UnauthorizedException(CustomResponseCode.UNAUTHORIZED, "Invalid Login details.");
            }
        } else {
            //NO NEED TO update login failed count and failed login date SINCE IT DOES NOT EXIST
            throw new UnauthorizedException(CustomResponseCode.UNAUTHORIZED, "Login details does not exist");
        }

        String accessList = permissionService.getPermissionsByUserId(user.getId());
        AuthenticationWithToken authWithToken = new AuthenticationWithToken(user, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,"+accessList));
        String newToken = "Bearer" +" "+this.tokenService.generateNewToken();
        authWithToken.setToken(newToken);
        tokenService.store(newToken, authWithToken);
        SecurityContextHolder.getContext().setAuthentication(authWithToken);
        userService.updateLogin(user.getId());

       String clientId= "";
        String referralCode="";
        String isEmailVerified="";
        List<com.sabi.framework.dto.responseDto.PartnersCategoryReturn> partnerCategory= null;
        if (user.getUserCategory().equals(Constants.OTHER_USER)) {
            PartnerUser partner = partnerUserRepository.findByUserId(user.getId());
            if(partner !=null){
                clientId = String.valueOf(partner.getPartnerId());
                partnerCategory = partnerCategoriesService.partnerCategoryReturn(partner.getPartnerId());

            }
        }
        AccessTokenWithUserDetails details = new AccessTokenWithUserDetails(newToken, user,
                accessList,userService.getSessionExpiry(),clientId,referralCode,isEmailVerified,partnerCategory);
        auditTrailService
                .logEvent(loginRequest.getUsername(), "Login by username : " + loginRequest.getUsername(),
                        AuditTrailFlag.LOGIN, "Successful Login Request by : " + loginRequest.getUsername() , 1, ipAddress);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }





    @PostMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean logout() {
        try {
            AuthenticationWithToken auth = (AuthenticationWithToken) SecurityContextHolder.getContext().getAuthentication();
            return tokenService.remove(auth.getToken());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            LoggerUtil.logError(logger, ex);
        }
        return false;
    }




    @PostMapping("/externaltoken")
    public void externalToken() throws Exception {
       externalTokenService.externalTokenRequest();
    }



    @PutMapping("/generatepassword")
    public ResponseEntity<Response> generatePassword(@Validated @RequestBody GeneratePassword request){
        HttpStatus httpCode ;
        Response resp = new Response();
        GeneratePasswordResponse response=driverPasswordService.generatePassword(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Password generated successfully");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
