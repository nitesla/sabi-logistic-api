package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.SLARequestDto;
import com.sabi.logistics.core.enums.SlaName;
import com.sabi.logistics.service.services.SLAService;
import com.sabi.logistics.service.services.SLAService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(Constants.APP_CONTENT+"sla")
public class SLAController {


    private final SLAService service;

    public SLAController(SLAService service) {
        this.service = service;
    }


    /** <summary>
     * SLA creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new SLA</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createSLA(@Validated @RequestBody SLARequestDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Successful");
        resp.setData(service.createSLA(request));
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    /** <summary>
     * SLA update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating SLA</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateSLA(@Validated @RequestBody  SLARequestDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Update Successful");
        resp.setData(service.updateSLA(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getSLA(@PathVariable Long id){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.findSingleSLA(id));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> searchSLAs(@Validated @RequestParam(value = "slaName",required = false)SlaName slaName,
                                               @RequestParam(value = "slaDuration",required = false) Long slaDuration,
                                               @RequestParam(value = "triggerDuration",required = false)Long triggerDuration,
                                               @RequestParam(value = "isActive",required = false) Boolean isActive,
                                               @RequestParam(value = "page") int page,
                                               @RequestParam(value = "pageSize") int pageSize){

        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.searchAllSLA(slaDuration,slaName,triggerDuration,isActive,PageRequest.of(page,pageSize)));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Invoices</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Successful");
        service.enableDisable(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.findAllSLAByIsActive(isActive));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
