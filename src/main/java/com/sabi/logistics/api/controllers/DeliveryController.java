package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DeliveryRequestDto;
import com.sabi.logistics.core.dto.response.DeliveryResponseDto;
import com.sabi.logistics.core.models.Delivery;
import com.sabi.logistics.service.services.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"delivery")
public class DeliveryController {


    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }


    /** <summary>
     * Delivery creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Deliverys</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createDelivery(@Validated @RequestBody DeliveryRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        DeliveryResponseDto response = service.createDelivery(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Delivery update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating deliveries</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateDelivery(@Validated @RequestBody  DeliveryRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        DeliveryResponseDto response = service.updateDelivery(request);
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
    public ResponseEntity<Response> getDelivery(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DeliveryResponseDto response = service.findDelivery(id);
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
    @GetMapping("")
    public ResponseEntity<Response> getDeliverys(@RequestParam(value = "partnerAssetID",required = false)Long partnerAssetID,
                                              @RequestParam(value = "orderItemID",required = false) Long orderItemID,
                                              @RequestParam(value = "status",required = false)String status,
                                              @RequestParam(value = "driverID",required = false) Long driverID,
                                              @RequestParam(value = "driverAssistantID",required = false)Long driverAssistantID,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Delivery> response = service.findAll(partnerAssetID, orderItemID, status, driverID, driverAssistantID, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Deliveries</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisable(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Delivery> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
