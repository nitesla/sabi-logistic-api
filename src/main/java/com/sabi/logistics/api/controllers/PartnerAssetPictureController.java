package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerAssetPictureDto;
import com.sabi.logistics.core.dto.response.PartnerAssetPictureResponseDto;
import com.sabi.logistics.core.models.PartnerAssetPicture;
import com.sabi.logistics.service.services.PartnerAssetPictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"assetpicture")
public class PartnerAssetPictureController {

    private final PartnerAssetPictureService service;

    public PartnerAssetPictureController(PartnerAssetPictureService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createAssetPicture(@Validated @RequestBody PartnerAssetPictureDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerAssetPictureResponseDto response = service.createPartnerPicture(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    public ResponseEntity<Response> updateAssetPicture(@Validated @RequestBody PartnerAssetPictureDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerAssetPictureResponseDto response = service.updatePartnerPicture(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Response> getAssetPictures(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerAssetPictureResponseDto response = service.findPartnerPicture(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("")
    public ResponseEntity<Response> getAssetsPictures(@RequestParam(value = "partnerAssetId",required = false)Long partnerAssetId,
                                                     @RequestParam(value = "pictureType",required = false)String pictureType,
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<PartnerAssetPicture> response = service.findAll(partnerAssetId,pictureType, PageRequest.of(page, pageSize));
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
        List<PartnerAssetPicture> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
