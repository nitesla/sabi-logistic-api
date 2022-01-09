package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.BrandRequestDto;
import com.sabi.logistics.service.services.BrandService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@SuppressWarnings("All")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"brand")
public class BrandController {

    private final BrandService brandService;


    private final ResponseHelper responseHelper;

    public BrandController(BrandService brandService, ResponseHelper responseHelper) {
        this.brandService = brandService;
        this.responseHelper = responseHelper;
    }

    @PostMapping
    public ResponseEntity<Response> createBrand(@Valid @RequestBody BrandRequestDto request,HttpServletRequest request1){
        return responseHelper.buildResponse( brandService.createBrand(request,request1), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updateBrand(@Valid @RequestBody BrandRequestDto request,HttpServletRequest request1){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(brandService.updateBrand(request,request1), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getBrandById(@PathVariable long id){
        return responseHelper.buildResponse(brandService.findBrand(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllBrands(@RequestParam(value = "name",required = false)String name,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){
        return responseHelper
                .buildResponse(brandService.findAll(name, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request,HttpServletRequest request1){
        brandService.enableDisEnableState(request,request1);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(brandService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}
