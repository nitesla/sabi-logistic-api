package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.models.User;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerUserActivation;
import com.sabi.logistics.core.dto.request.PartnerUserRequestDto;
import com.sabi.logistics.core.dto.response.PartnerUserResponseDto;
import com.sabi.logistics.core.models.PartnerUser;
import com.sabi.logistics.service.services.PartnerUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@SuppressWarnings("ALL")
@RestController
@RequestMapping(Constants.APP_CONTENT+"partneruser")
public class PartnerUserController {

    private final PartnerUserService partnerUserService;



    public PartnerUserController(PartnerUserService partnerUserService) {
        this.partnerUserService = partnerUserService;

    }

    @PostMapping("")
    public ResponseEntity<Response> createPartnerUser(@Validated @RequestBody PartnerUserRequestDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerUserResponseDto response = partnerUserService.createPartnerUser(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("/activatepartneruser")
    public ResponseEntity<Response> activatePartnerUser(@Validated @RequestBody PartnerUserActivation request){
        HttpStatus httpCode ;
        Response resp = new Response();
        partnerUserService.activatePartnerUser(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

//    @PutMapping("")
//    public ResponseEntity<Response> uploadPartnerUserPicture(@Validated @RequestBody PartnerUserRequestDto partnerUserRequestDto){
//        Response response = new Response(CustomResponseCode.SUCCESS,"Update Successful");
//        response.setData(partnerUserService.uploadPartnerUserPicture(partnerUserRequestDto));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/page")
    public ResponseEntity<Response> getPartnerUser(@RequestParam(value = "firstName",required = false)String firstName,
                                                   @RequestParam(value = "phone",required = false)String phone,
                                                   @RequestParam(value = "email",required = false)String email,
                                                   @RequestParam(value = "username",required = false)String username,
                                                   @RequestParam(value = "roleId",required = false)Long roleId,
                                                   @RequestParam(value = "isActive",required = false)Boolean isActive,
                                                   @RequestParam(value = "lastName",required = false)String lastName,
                                                   @RequestParam(value = "userType",required = false)String userType,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        if(userType !=null ){
            Page<PartnerUser> response = partnerUserService.findPartnerUsers(userType,isActive, PageRequest.of(page, pageSize));
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Record fetched successfully !");
            resp.setData(response);
            httpCode = HttpStatus.OK;
            return new ResponseEntity<>(resp, httpCode);
        }else {
            Page<User> response = partnerUserService.findByClientId(firstName, phone, email, username, roleId,isActive, lastName, PageRequest.of(page, pageSize));
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Record fetched successfully !");
            resp.setData(response);
            httpCode = HttpStatus.OK;
            return new ResponseEntity<>(resp, httpCode);
        }
    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive,
                                           @RequestParam(value = "userType",required = false)String userType){
        HttpStatus httpCode ;
        Response resp = new Response();

        if(userType !=null ){
            List<PartnerUser> response = partnerUserService.findPartnerUsersList(userType,isActive);
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Record fetched successfully !");
            resp.setData(response);
            httpCode = HttpStatus.OK;
            return new ResponseEntity<>(resp, httpCode);
        }else {
            List<User> response = partnerUserService.getAll(isActive);
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Record fetched successfully !");
            resp.setData(response);
            httpCode = HttpStatus.OK;
            return new ResponseEntity<>(resp, httpCode);
        }
    }


}
