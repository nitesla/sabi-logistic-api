package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.ColorRequestDto;
import com.sabi.logistics.service.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"color")
public class ColorController {
    private final ColorService ColorService;

    @Autowired
    private ResponseHelper responseHelper;

    public ColorController(ColorService ColorService) {
        this.ColorService = ColorService;
    }

    @PostMapping
    public ResponseEntity<Response> createColor(@RequestBody @Valid ColorRequestDto request){
        return responseHelper.buildResponse( ColorService.createColor(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updateColor(@RequestBody @Valid ColorRequestDto request){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(ColorService.updateColor(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getColorById(@PathVariable long id){
        return responseHelper.buildResponse(ColorService.findColor(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllColors(@RequestParam(value = "name",required = false)String name,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){
        return responseHelper
                .buildResponse(ColorService.findAll(name, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request){
        ColorService.enableDisEnableState(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(ColorService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}
