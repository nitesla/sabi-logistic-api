package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.TripRequestDto;
import com.sabi.logistics.core.dto.response.TripResponseDto;
import com.sabi.logistics.core.models.TripRequest;
import com.sabi.logistics.service.services.TripRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"triprequest")
public class TripRequestController {


    private final TripRequestService service;

    public TripRequestController(TripRequestService service) {
        this.service = service;
    }


    /** <summary>
     * Trip Request creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Trip Request</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createTripRequest(@Validated @RequestBody TripRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripResponseDto response = service.createTripRequest(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Trip Request update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Trip requests</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateTripRequest(@Validated @RequestBody  TripRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripResponseDto response = service.updateTripRequest(request);
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
    public ResponseEntity<Response> getTripRequest(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripResponseDto response = service.findTripRequest(id);
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
    public ResponseEntity<Response> getTripRequests(@RequestParam(value = "partnerID",required = false)Long partnerID,
                                              @RequestParam(value = "orderItemID",required = false) Long orderItemID,
                                              @RequestParam(value = "status",required = false)String status,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<TripRequest> response = service.findAll(partnerID, orderItemID, status, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Trip Requests</remarks>
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
        List<TripRequest> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}