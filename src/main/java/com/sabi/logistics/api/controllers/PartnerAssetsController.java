package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerAssetRequestDto;
import com.sabi.logistics.service.services.PartnerAssetService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@SuppressWarnings("ALL")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"partnerasset")
public class PartnerAssetsController {

    private final PartnerAssetService partnerAssetService;


    private final ResponseHelper responseHelper;

    public PartnerAssetsController(PartnerAssetService partnerAssetService, ResponseHelper responseHelper) {
        this.partnerAssetService = partnerAssetService;
        this.responseHelper = responseHelper;
    }

    @PostMapping
    public ResponseEntity<Response> createPartnerAsset(@RequestBody @Valid PartnerAssetRequestDto request,HttpServletRequest request1){
        return responseHelper.buildResponse( partnerAssetService.createPartnerAsset(request,request1), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updatePartnerAsset(@RequestBody @Valid PartnerAssetRequestDto request,HttpServletRequest request1){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(partnerAssetService.updatePartnerAsset(request,request1), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPartnerAssetById(@PathVariable long id){
        return responseHelper.buildResponse(partnerAssetService.findPartnerAsset(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllPartnerAssets(@RequestParam(value = "name",required = false)String name,
                                                        @RequestParam(value = "brandId",required = false)Long brandId,
                                                        @RequestParam(value = "status",required = false)String status,
                                                        @RequestParam(value = "driverId",required = false)Long driverId,
                                                        @RequestParam(value = "partnerId",required = false)Long partnerId,
                                                        @RequestParam(value = "partnerAssetTypeId",required = false)Long partnerAssetTypeId,
                                                        @RequestParam(value = "isActive",required = false)Boolean isActive,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){

        return responseHelper
                .buildResponse(partnerAssetService.findAll(name,brandId,status,driverId,partnerId,partnerAssetTypeId, isActive, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request){
        partnerAssetService.enableDisEnableState(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                           @RequestParam(value = "isActive",required = false)Boolean isActive){
        return responseHelper.buildResponse(partnerAssetService.getAll(partnerId,isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}
