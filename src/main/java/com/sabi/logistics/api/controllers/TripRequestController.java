package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.ShipmentTripRequest;
import com.sabi.logistics.core.dto.request.TripMasterRequestDto;
import com.sabi.logistics.core.dto.request.TripRequestDto;
import com.sabi.logistics.core.dto.response.TripMasterResponseDto;
import com.sabi.logistics.core.dto.response.TripRequestStatusCountResponse;
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

    @PostMapping("/mastertriprequest")
    public ResponseEntity<Response> createMasterTripRequest(@Validated @RequestBody TripMasterRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripMasterResponseDto response = service.createMasterTripRequest(request);
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
    public ResponseEntity<Response> getTripRequests(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                    @RequestParam(value = "status",required = false)String status,
                                                    @RequestParam(value = "referenceNo",required = false)String referenceNo,
                                                    @RequestParam(value = "driverUserId",required = false)Long driverUserId,
                                                    @RequestParam(value = "driverAssistantUserId",required = false)Long driverAssistantUserId,
                                                    @RequestParam(value = "wareHouseId",required = false)Long wareHouseId,
                                                    @RequestParam(value = "wareHouseAddress",required = false)String wareHouseAddress,
                                                    @RequestParam(value = "partnerAssetId",required = false)Long partnerAssetId,
                                                    @RequestParam(value = "unassigned", required = false) Boolean unassigned,
                                                    @RequestParam(value = "page") int page,
                                                    @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<TripRequest> response = service.findAll(partnerId, status, referenceNo, driverUserId, driverAssistantUserId, wareHouseId, wareHouseAddress, partnerAssetId, unassigned, PageRequest.of(page, pageSize));
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

    /** <summary>
     * Get all accepted trip request records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("/delivery")
    public ResponseEntity<Response> getDeliveries(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                    @RequestParam(value = "deliveryStatus",required = false)String deliveryStatus,
                                                    @RequestParam(value = "partnerAssetId",required = false)Long partnerAssetId,
                                                    @RequestParam(value = "page") int page,
                                                    @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<TripRequest> response = service.getDeliveries(partnerId, deliveryStatus,  partnerAssetId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/statuscount")
    public ResponseEntity<Response> getStatusCount(@RequestParam(value = "driverUserId")Long driverUserId){
        HttpStatus httpCode ;
        Response resp = new Response();
        TripRequestStatusCountResponse response = service.getStatus(driverUserId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




    @PostMapping("/shipmenttrip")
    public ResponseEntity<Response> shipmentTripRequest(@Validated @RequestBody ShipmentTripRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.shipmentTripRequest(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("successfully");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
