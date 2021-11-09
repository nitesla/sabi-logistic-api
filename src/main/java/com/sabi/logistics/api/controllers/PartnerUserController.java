package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerUserRequestDto;
import com.sabi.logistics.core.dto.response.PartnerUserResponseDto;
import com.sabi.logistics.service.services.PartnerUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("ALL")
@RestController
@RequestMapping(Constants.APP_CONTENT+"partneruser")
public class PartnerUserController {
    private final PartnerUserService partnerUserService;



    public PartnerUserController(PartnerUserService PartnerUserService) {
        this.partnerUserService = PartnerUserService;

    }



    @PostMapping("")
    public ResponseEntity<Response> createPartnerUser(@Validated @RequestBody PartnerUserRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerUserResponseDto response = partnerUserService.createPartnerUser(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }








}
