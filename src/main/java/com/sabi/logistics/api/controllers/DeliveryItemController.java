package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DeliveryItemRequestDto;
import com.sabi.logistics.core.dto.response.DeliveryItemResponseDto;
import com.sabi.logistics.core.models.DeliveryItem;
import com.sabi.logistics.service.services.DeliveryItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"deliveryitem")
public class DeliveryItemController {


    private final DeliveryItemService service;

    public DeliveryItemController(DeliveryItemService service) {
        this.service = service;
    }


    /** <summary>
     * Delivery Item creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Delivery Item</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createDeliveryItem(@Validated @RequestBody DeliveryItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        DeliveryItemResponseDto response = service.createDeliveryItem(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Delivery item update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating delivery item</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateDeliveryItem(@Validated @RequestBody  DeliveryItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        DeliveryItemResponseDto response = service.updateDeliveryItem(request);
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
    public ResponseEntity<Response> getDeliveryItem(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DeliveryItemResponseDto response = service.findDeliveryItem(id);
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
    public ResponseEntity<Response> getDeliveryItems(@RequestParam(value = "deliveryID",required = false)Long deliveryID,
                                              @RequestParam(value = "tripRequestID",required = false) Long tripRequestID,
                                              @RequestParam(value = "status",required = false)String status,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DeliveryItem> response = service.findAll(deliveryID, tripRequestID, status, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Delivery Item</remarks>
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
        List<DeliveryItem> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
