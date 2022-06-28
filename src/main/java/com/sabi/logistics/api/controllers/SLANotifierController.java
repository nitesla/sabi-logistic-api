package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.SLARequestDto;
import com.sabi.logistics.core.enums.SlaName;
import com.sabi.logistics.service.services.SLANotifierService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT+"slaNotifier")
public class SLANotifierController {


    private final SLANotifierService service;

    public SLANotifierController(SLANotifierService service) {
        this.service = service;
    }




    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getSLA(@PathVariable Long id){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.findSingleSLANotifier(id));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> searchSLANotifiers(@Validated @RequestParam(value = "slaName",required = false)SlaName slaName,
                                               @RequestParam(value = "name",required = false) String name,
                                               @RequestParam(value = "email",required = false) String email,
                                               @RequestParam(value = "isActive",required = false) Boolean isActive,
                                               @RequestParam(value = "page") int page,
                                               @RequestParam(value = "pageSize") int pageSize){

        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.searchAll(slaName, name, email,isActive,PageRequest.of(page,pageSize)));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.getAllByIsActive(isActive));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
