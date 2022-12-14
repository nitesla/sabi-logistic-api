package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.AssetTypePropertiesDto;
import com.sabi.logistics.core.dto.response.AssetTypePropertiesResponseDto;
import com.sabi.logistics.core.models.AssetTypeProperties;
import com.sabi.logistics.service.services.AssetTypePropertiesService;
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
@RequestMapping(Constants.APP_CONTENT+"assettypeproperties")
public class AssetTypePropertiesController {

    private final AssetTypePropertiesService service;

    public AssetTypePropertiesController(AssetTypePropertiesService service) {
        this.service = service;
    }



    @PostMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> createAssetType(@Validated @RequestBody AssetTypePropertiesDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        AssetTypePropertiesResponseDto response = service.createAssetTypeProperties(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updateAssetType(@Validated @RequestBody  AssetTypePropertiesDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        AssetTypePropertiesResponseDto response = service.updateAssetTypeProperties(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getAssetType(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        AssetTypePropertiesResponseDto response = service.findAsstType(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/page")
    public ResponseEntity<Response> getAssetTypes(@RequestParam(value = "name",required = false)String name,
                                                  @RequestParam(value = "isActive", required = false)Boolean isActive,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<AssetTypeProperties> response = service.findAll(name, isActive, PageRequest.of(page, pageSize));
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
        List<AssetTypeProperties> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
