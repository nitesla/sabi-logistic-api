package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.WarehouseRequestDto;
import com.sabi.logistics.core.models.Warehouse;
import com.sabi.logistics.service.services.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    private final ResponseHelper responseHelper;

    public WarehouseController(WarehouseService WarehouseService, ResponseHelper responseHelper) {
        this.warehouseService = WarehouseService;
        this.responseHelper = responseHelper;
    }

    @PostMapping
    public ResponseEntity<Response> createWarehouse(@RequestBody @Valid WarehouseRequestDto request){
        return responseHelper.buildResponse( warehouseService.createWarehouse(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updateWarehouse(@RequestBody @Valid WarehouseRequestDto request){
        if(request.getId() == null || request.getId() < 0) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(warehouseService.updateWarehouse(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getWarehouseById(@PathVariable long id){
        return responseHelper.buildResponse(warehouseService.findWarehouse(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllWarehouses(@RequestParam(value = "owner",required = false)String owner,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "partnerId", required = false) Long partnerId,
                                                 @RequestParam(value = "lgaId", required = false) Long lgaId,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){

        HttpStatus httpCode ;
        Response resp = new Response();

        Page<Warehouse> response = warehouseService.findAll(owner, name, partnerId, lgaId, PageRequest.of(page, pageSize));

        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);

    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request){
        warehouseService.enableDisEnableState(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(warehouseService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }
}
