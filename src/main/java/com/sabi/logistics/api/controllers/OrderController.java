package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.OrderRequestDto;
import com.sabi.logistics.core.dto.response.OrderResponseDto;
import com.sabi.logistics.core.models.Order;
import com.sabi.logistics.service.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"order")
public class OrderController {


    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    /** <summary>
     * Order creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Orders</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createOrder(@Validated @RequestBody OrderRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        OrderResponseDto response = service.createOrder(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Order update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating orders</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateOrder(@Validated @RequestBody  OrderRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        OrderResponseDto response = service.updateOrder(request);
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
    public ResponseEntity<Response> getOrder(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        OrderResponseDto response = service.findOrder(id);
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
    public ResponseEntity<Response> getOrders(@RequestParam(value = "wareHouseID",required = false)Long wareHouseID,
                                              @RequestParam(value = "deliveryPartnerID",required = false) Long deliveryPartnerID,
                                              @RequestParam(value = "referenceNo",required = false)String referenceNo,
                                              @RequestParam(value = "deliveryStatus",required = false) String deliveryStatus,
                                              @RequestParam(value = "customerName",required = false)String customerName,
                                              @RequestParam(value = "customerPhone",required = false)String customerPhone,
                                              @RequestParam(value = "deliveryAddress",required = false) String deliveryAddress,
                                              @RequestParam(value = "barCode",required = false)String barCode,
                                              @RequestParam(value = "QRcode",required = false) String QRcode,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Order> response = service.findAll(wareHouseID, deliveryPartnerID, referenceNo, deliveryStatus, customerName, customerPhone, deliveryAddress, barCode, QRcode,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Orders</remarks>
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
        List<Order> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
