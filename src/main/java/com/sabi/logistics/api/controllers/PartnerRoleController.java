package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.RoleDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.dto.responseDto.RoleResponseDto;
import com.sabi.framework.models.Role;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.service.services.PartnerRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping(Constants.APP_CONTENT+"partnerrole")
public class PartnerRoleController {


    private final PartnerRoleService partnerRoleService;



    public PartnerRoleController(PartnerRoleService partnerRoleService) {
        this.partnerRoleService = partnerRoleService;

    }


    @PostMapping("")
    public ResponseEntity<Response> createPartnerRole(@Validated @RequestBody RoleDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleResponseDto response = partnerRoleService.createPartnerRole(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/page")
    public ResponseEntity<Response> getPartnerRole(@RequestParam(value = "name",required = false)String name,
                                                   @RequestParam(value = "isActive",required = false)Boolean isActive,
                                                   @RequestParam(value = "page") int page,
                                                   @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Role> response = partnerRoleService.findByClientId(name,isActive,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Role> response = partnerRoleService.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
