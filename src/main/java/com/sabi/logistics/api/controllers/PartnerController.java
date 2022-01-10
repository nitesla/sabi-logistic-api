package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.ChangePasswordDto;
import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.CompleteSignupRequest;
import com.sabi.logistics.core.dto.request.ExternalPartnerSignUp;
import com.sabi.logistics.core.dto.request.PartnerDto;
import com.sabi.logistics.core.dto.request.PartnerSignUpDto;
import com.sabi.logistics.core.dto.response.*;
import com.sabi.logistics.core.models.Partner;
import com.sabi.logistics.service.services.ExternalSignUpService;
import com.sabi.logistics.service.services.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"partner")
public class PartnerController {

    private final PartnerService service;
    private final ExternalSignUpService externalSignUpService;

    public PartnerController(PartnerService service,ExternalSignUpService externalSignUpService) {
        this.service = service;
        this.externalSignUpService = externalSignUpService;
    }



    @PostMapping("/signup")
    public ResponseEntity<Response> partnerSignUp(@Validated @RequestBody PartnerSignUpDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerSignUpResponseDto response = service.partnerSignUp(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PostMapping("/externalsignup")
    public ResponseEntity<Response> externalSignUp(@Validated @RequestBody ExternalPartnerSignUp request){
        HttpStatus httpCode ;
        Response resp = new Response();
        ExternalPartnerSignUpResponse response = externalSignUpService.externalSignUp(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/details/{supplierId}")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> externalDetails(@PathVariable Long supplierId){
        HttpStatus httpCode ;
        Response resp = new Response();
        ExternalDetailsResponse response = externalSignUpService.externalDetails(supplierId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/completesignup")
    public ResponseEntity<Response> completeSignUp(@Validated @RequestBody CompleteSignupRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        CompleteSignUpResponse response = service.completeSignUp(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/passwordactivation")
    public ResponseEntity<Response> partnerPasswordActivation(@Validated @RequestBody ChangePasswordDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerActivationResponse response = service.partnerPasswordActivation(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Password changed successfully");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PostMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> createPartnerProperties(@Validated @RequestBody PartnerDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerResponseDto response = service.createPartnerProperties(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updatePartnerProperties(@Validated @RequestBody  PartnerDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerResponseDto response = service.updatePartnerProperties(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getPartnerProperty(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerResponseDto response = service.findPartnerAsset(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("")
    public ResponseEntity<Response> getPartnerProperties(@RequestParam(value = "name",required = false)String name,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Partner> response = service.findAll(name, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnable(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Partner> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
