package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerUserRequestDto;
import com.sabi.logistics.service.services.PartnerUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"partneruser")
public class PartnerUserController {
    private final PartnerUserService partnerUserService;

    private final ResponseHelper responseHelper;

    public PartnerUserController(PartnerUserService PartnerUserService, ResponseHelper responseHelper) {
        this.partnerUserService = PartnerUserService;
        this.responseHelper = responseHelper;
    }

    @PostMapping
    public ResponseEntity<Response> createPartnerUser(@RequestBody @Valid PartnerUserRequestDto request){
        return responseHelper.buildResponse( partnerUserService.createPartnerUser(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updatePartnerUser(@RequestBody @Valid PartnerUserRequestDto request){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(partnerUserService.updatePartnerUser(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPartnerUserById(@PathVariable long id){
        return responseHelper.buildResponse(partnerUserService.findPartnerUser(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllPartnerUsers(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){
        return responseHelper
                .buildResponse(partnerUserService.findAll(partnerId, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request){
        partnerUserService.enableDisEnableState(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(partnerUserService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}
