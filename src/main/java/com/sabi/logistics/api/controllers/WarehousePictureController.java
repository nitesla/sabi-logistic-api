package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.WarehousePictureDto;
import com.sabi.logistics.core.dto.response.WarehousePictureResponseDto;
import com.sabi.logistics.core.models.WarehousePicture;
import com.sabi.logistics.service.services.WarehousePictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"warehousepicture")
public class WarehousePictureController {

    private final WarehousePictureService service;

    public WarehousePictureController(WarehousePictureService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createWarehousePicture(@Validated @RequestBody WarehousePictureDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WarehousePictureResponseDto response = service.createWarehousePicture(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("bulk")
    public ResponseEntity<Response> createWarehousePictures(@Validated @RequestBody List<WarehousePictureDto> request){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<WarehousePictureResponseDto> response = service.createWarehousePictures(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    public ResponseEntity<Response> updateWarehousePicture(@Validated @RequestBody WarehousePictureDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WarehousePictureResponseDto response = service.updateWarehousePicture(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Response> getWarehousePictures(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        WarehousePictureResponseDto response = service.findWarehousePicture(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("")
    public ResponseEntity<Response> getWarehousePictures(@RequestParam(value = "warehouseId",required = false)Long warehouseId,
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<WarehousePicture> response = service.findAll(warehouseId, PageRequest.of(page, pageSize));
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
        List<WarehousePicture> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
