package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.WarehouseProductDto;
import com.sabi.logistics.core.dto.response.StateResponseDto;
import com.sabi.logistics.core.dto.response.WarehouseProductResponseDto;
import com.sabi.logistics.core.models.WarehouseProduct;
import com.sabi.logistics.service.services.WarehouseProductService;
import com.sabi.logistics.service.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"warehouseproduct")
public class WarehouseProductController {

    @Autowired
    private WarehouseProductService service;



    /** <summary>
     * Warehouse product creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Warehouse product</remarks>
     */

    @PostMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> createWarehouseProduct(@Validated @RequestBody WarehouseProductDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WarehouseProductResponseDto response = service.createWarehouseProduct(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Warehouse product update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Warehouse product</remarks>
     */

    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updateWarehouseProduct(@Validated @RequestBody  WarehouseProductDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WarehouseProductResponseDto response = service.updateWarehouseProduct(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getWarehouseProduct(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        WarehouseProductResponseDto response = service.findWarehouseProduct(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("/page")
    public ResponseEntity<Response> getWarehouseProduct(@RequestParam(value = "warehouseId",required = false)Long warehouseId,
                                              @RequestParam(value = "thirdPartyProductId",required = false)String thirdPartyProductId,
                                              @RequestParam(value = "productName",required = false)String productName,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<WarehouseProduct> response = service.findAll(warehouseId,thirdPartyProductId,productName, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Warehouse product</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableWarehouseProduct(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




    @GetMapping("/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<WarehouseProduct> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
