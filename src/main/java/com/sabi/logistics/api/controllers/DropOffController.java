package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DropOffRequestDto;
import com.sabi.logistics.core.dto.request.DropOffStatusDto;
import com.sabi.logistics.core.dto.response.DropOffResponseDto;
import com.sabi.logistics.core.models.DropOff;
import com.sabi.logistics.service.services.DropOffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"dropoff")
public class DropOffController {


    private final DropOffService service;

    public DropOffController(DropOffService service) {
        this.service = service;
    }


    /** <summary>
     * DropOff  creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new DropOff </remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createDropOff(@Validated @RequestBody DropOffRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffResponseDto response = service.createDropOff(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * DropOff  update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating DropOff </remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateDropOff(@Validated @RequestBody  DropOffRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffResponseDto response = service.updateDropOff(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("/updatestatus")
    public ResponseEntity<Response> updateDropOffStatus(@Validated @RequestBody DropOffStatusDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffResponseDto response = service.updateDropOffStatus(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("/paidstatus")
    public ResponseEntity<Response> updatePaidStatus(@RequestParam(value = "paidStatus")String paidStatus,
                                                     @RequestParam(value = "dropOffId") Long dropOffId){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffResponseDto response = service.updatePaidStatus(paidStatus, dropOffId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("/returnstatus")
    public ResponseEntity<Response> updateReturnStatus(@RequestParam(value = "returnStatus")String returnStatus,
                                                     @RequestParam(value = "dropOffId") Long dropOffId){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffResponseDto response = service.updateReturnStatus(returnStatus, dropOffId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/getalldropoffs")
    public ResponseEntity<Response> getAllDropOffs(@RequestParam(value = "paidStatus")String paidStatus,
                                                   @RequestParam(value = "tripRequestId")Long tripRequestId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<DropOff> response = service.getAllDropOffs(paidStatus, tripRequestId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
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
    public ResponseEntity<Response> getDropOff(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffResponseDto response = service.findDropOff(id);
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
    public ResponseEntity<Response> getDropOffs(@RequestParam(value = "orderId",required = false)Long orderId,
                                              @RequestParam(value = "tripRequestId",required = false) Long tripRequestId,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DropOff> response = service.findAll(orderId, tripRequestId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a DropOff </remarks>
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
    public ResponseEntity<Response> getAll(@RequestParam(value = "tripRequestId")Long tripRequestId,
                                           @RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<DropOff> response = service.getAll(isActive, tripRequestId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
