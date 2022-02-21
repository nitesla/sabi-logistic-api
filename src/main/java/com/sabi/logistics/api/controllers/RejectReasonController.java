package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.RejectReasonDto;
import com.sabi.logistics.service.services.RejectReasonService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SuppressWarnings("All")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"rejectreason")
public class RejectReasonController {


    private final ResponseHelper responseHelper;
    private final RejectReasonService service;

    public RejectReasonController(ResponseHelper responseHelper, RejectReasonService service) {
        this.responseHelper = responseHelper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> createRejectReason(@RequestBody @Valid RejectReasonDto request){
        return responseHelper.buildResponse( service.createRejectReason(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updateRejectReason(@RequestBody @Valid RejectReasonDto request){
        if(request.getId() == null || request.getId() < 0)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(service.updateRejectReason(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getRejectReasonById(@PathVariable long id){
        return responseHelper.buildResponse(service.findRejectReason(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllRejectReasons(@RequestParam(value = "name",required = false)String name,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){
        return responseHelper
                .buildResponse(service.findAll(name, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(){
        return responseHelper.buildResponse(service.getAll(), HttpStatus.OK, "Record fetched successfully !");
    }
}
