package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DriverAssetDto;
import com.sabi.logistics.core.dto.response.DriverAssetResponseDto;
import com.sabi.logistics.core.models.DriverAsset;
import com.sabi.logistics.service.services.DriverAssetService;
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
@RequestMapping(Constants.APP_CONTENT+"driverasset")
public class DriverAssetController {

    private final DriverAssetService service;

    public DriverAssetController(DriverAssetService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createDriverAsset(@Validated @RequestBody DriverAssetDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DriverAssetResponseDto response = service.createDriverAsset(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    public ResponseEntity<Response> updateDriverAsset(@Validated @RequestBody DriverAssetDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DriverAssetResponseDto response = service.updateDriverAsset(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getDriverAsset(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DriverAssetResponseDto response = service.findDriverAsset(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/byUserId/{driverUserId}")
    public ResponseEntity<Response> getDriverAssetsByUserId(@PathVariable Long driverUserId){

        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.getDriverAssetsByUserId(driverUserId));
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<Response> getDriversAssets(
                                                     @RequestParam(value = "driverId",required = false)Long driverId,
                                                     @RequestParam(value = "partnerAssetId",required = false)Long partnerAssetId,
                                               @RequestParam(value = "page") int page,
                                               @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DriverAsset> response = service.findAll(driverId,partnerAssetId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableState(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<DriverAsset> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
