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

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"partnerasset")
public class PartnerAssetsController {

    private final PartnerAssetService partnerAssetService;


    private final ResponseHelper responseHelper;

    public PartnerAssetsController(PartnerAssetService PartnerAssetService, ResponseHelper responseHelper) {
        this.partnerAssetService = PartnerAssetService;
        this.responseHelper = responseHelper;
    }

    @PostMapping
    public ResponseEntity<Response> createPartnerAsset(@RequestBody @Valid PartnerAssetRequestDto request){
        return responseHelper.buildResponse( partnerAssetService.createPartnerAsset(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updatePartnerAsset(@RequestBody @Valid PartnerAssetRequestDto request){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(partnerAssetService.updatePartnerAsset(request), HttpStatus.OK, "Update Successful");
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
                                                        @RequestParam(value = "partnerAssetTypeId",required = false)Long partnerAssetTypeId,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){

//        String name, Long brandId, String status, Long driverId, Long partnerAssetTypeId,
        return responseHelper
                .buildResponse(partnerAssetService.findAll(name,brandId,status,driverId,partnerAssetTypeId, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request){
        partnerAssetService.enableDisEnableState(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(partnerAssetService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}