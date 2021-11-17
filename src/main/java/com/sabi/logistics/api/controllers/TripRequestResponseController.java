package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.TripRequestResponseReqDto;
import com.sabi.logistics.core.dto.response.TripRequestResponseDto;
import com.sabi.logistics.core.models.TripRequestResponse;
import com.sabi.logistics.service.services.TripRequestResponseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"triprequestresponse")
public class TripRequestResponseController {


    private final TripRequestResponseService service;

    public TripRequestResponseController(TripRequestResponseService service) {
        this.service = service;
    }


    /** <summary>
     * TripRequestResponse creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new TripRequestResponses</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createTripRequestResponse(@Validated @RequestBody TripRequestResponseReqDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripRequestResponseDto response = service.createTripRequestResponse(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * TripRequestResponse update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating TripRequestResponses</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateTripRequestResponse(@Validated @RequestBody  TripRequestResponseReqDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripRequestResponseDto response = service.updateTripRequestResponse(request);
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
    public ResponseEntity<Response> getTripRequestResponse(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripRequestResponseDto response = service.findTripRequestResponse(id);
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
    public ResponseEntity<Response> getTripRequestResponses(@RequestParam(value = "tripRequest",required = false)Long tripRequest,
                                              @RequestParam(value = "partnerID",required = false) Long partnerID,
                                              @RequestParam(value = "status",required = false)String status,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<TripRequestResponse> response = service.findAll(tripRequest, partnerID, status,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a TripRequestResponses</remarks>
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
        List<TripRequestResponse> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
