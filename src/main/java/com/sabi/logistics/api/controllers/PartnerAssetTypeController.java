package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerAssetTypeRequestDto;
import com.sabi.logistics.service.services.PartnerAssetTypeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"partnerassettype")
public class PartnerAssetTypeController {


    private final PartnerAssetTypeService partnerAssetTypeService;

    private final ResponseHelper responseHelper;

    public PartnerAssetTypeController(PartnerAssetTypeService partnerAssetTypeService, ResponseHelper responseHelper) {
        this.partnerAssetTypeService = partnerAssetTypeService;
        this.responseHelper = responseHelper;
    }

    @PostMapping
    public ResponseEntity<Response> createPartnerAssetType(@RequestBody @Valid PartnerAssetTypeRequestDto request){
        return responseHelper.buildResponse( partnerAssetTypeService.createPartnerAssetType(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updatePartnerAssetType(@RequestBody @Valid PartnerAssetTypeRequestDto request){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(partnerAssetTypeService.updatePartnerAssetType(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPartnerAssetTypeById(@PathVariable long id){
        return responseHelper.buildResponse(partnerAssetTypeService.findPartnerAssetType(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllPartnerAssetTypes(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                        @RequestParam(value = "page") int page,
                                                        @RequestParam(value = "pageSize") int pageSize){
        return responseHelper
                .buildResponse(partnerAssetTypeService.findAll(partnerId, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request){
        partnerAssetTypeService.enableDisEnableState(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(partnerAssetTypeService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}
