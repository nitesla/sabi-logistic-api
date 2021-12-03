package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.TripItemRequestDto;
import com.sabi.logistics.core.dto.response.TripItemResponseDto;
import com.sabi.logistics.core.models.TripItem;
import com.sabi.logistics.service.services.TripItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"tripitem")
public class TripItemController {


    private final TripItemService service;

    public TripItemController(TripItemService service) {
        this.service = service;
    }


    /** <summary>
     * Trip Item creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Trip Item</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createTripItem(@Validated @RequestBody TripItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripItemResponseDto response = service.createTripItem(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Trip item update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Trip item</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateTripItem(@Validated @RequestBody  TripItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripItemResponseDto response = service.updateTripItem(request);
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
    public ResponseEntity<Response> getTripItem(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripItemResponseDto response = service.findTripItem(id);
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
    public ResponseEntity<Response> getTripItems(@RequestParam(value = "orderItemID",required = false)Long orderItemID,
                                              @RequestParam(value = "tripRequest",required = false) Long tripRequest,
                                              @RequestParam(value = "status",required = false)String status,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<TripItem> response = service.findAll(orderItemID, tripRequest, status, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Trip Item</remarks>
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
        List<TripItem> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/invoice")
    public ResponseEntity<Response> getInvoice(@RequestParam(value = "tripRequestID")Long tripRequestID,
                                               @RequestParam(value = "orderID")Long orderID){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<TripItem> response = service.getInvoice(tripRequestID, orderID);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
