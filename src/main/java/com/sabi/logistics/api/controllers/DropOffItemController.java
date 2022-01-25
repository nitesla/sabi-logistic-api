package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DropOffItemRequestDto;
import com.sabi.logistics.core.dto.response.DropOffItemDetailDto;
import com.sabi.logistics.core.dto.response.DropOffItemResponseDto;
import com.sabi.logistics.core.models.DropOffItem;
import com.sabi.logistics.service.services.DropOffItemService;
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
@RequestMapping(Constants.APP_CONTENT+"dropoffitem")
public class DropOffItemController {


    private final DropOffItemService service;

    public DropOffItemController(DropOffItemService service) {
        this.service = service;
    }


    /** <summary>
     * DropOff Item creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new DropOff Item</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createDropOffItem(@Validated @RequestBody DropOffItemRequestDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffItemResponseDto response = service.createDropOffItem(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * DropOff Item update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating DropOff Item</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateDropOffItem(@Validated @RequestBody  DropOffItemRequestDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffItemResponseDto response = service.updateDropOffItem(request,request1);
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
    public ResponseEntity<Response> getDropOffItem(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffItemResponseDto response = service.findDropOffItem(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/details")
    public ResponseEntity<Response> getDropOffItemDetails(@RequestParam(value = "orderItemId")Long orderItemId){
        HttpStatus httpCode ;
        Response resp = new Response();
        DropOffItemDetailDto response = service.findDropOffItemDetails(orderItemId);
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
    public ResponseEntity<Response> getDropOffItems(@RequestParam(value = "orderItemId",required = false)Long orderItemId,
                                              @RequestParam(value = "dropOffId",required = false) Long dropOffId,
                                              @RequestParam(value = "status",required = false)String status,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DropOffItem> response = service.findAll(orderItemId, dropOffId, status, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a DropOff Item</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisable(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "dropOffId",required = false)Long dropOffId,
                                           @RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<DropOffItem> response = service.getAll(dropOffId, isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/invoice")
    public ResponseEntity<Response> getInvoice(@RequestParam(value = "dropOffId")Long dropOffId,
                                               @RequestParam(value = "orderId")Long orderId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<DropOffItem> response = service.getInvoice(dropOffId, orderId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
