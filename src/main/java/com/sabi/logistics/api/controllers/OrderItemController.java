package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.OrderItemRequestDto;
import com.sabi.logistics.core.dto.response.OrderItemResponseDto;
import com.sabi.logistics.core.models.OrderItem;
import com.sabi.logistics.service.services.OrderItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"orderitem")
public class OrderItemController {


    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }


    /** <summary>
     * Order Item creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Order Item</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createOrderItem(@Validated @RequestBody OrderItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        OrderItemResponseDto response = service.createOrderItem(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Order item update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating order item</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateOrderItem(@Validated @RequestBody  OrderItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        OrderItemResponseDto response = service.updateOrderItem(request);
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
    public ResponseEntity<Response> getOrderItem(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        OrderItemResponseDto response = service.findOrderItem(id);
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
    public ResponseEntity<Response> getOrderItems(@RequestParam(value = "wareHouseId",required = false)Long wareHouseId,
                                                  @RequestParam(value = "referenceNo",required = false)String referenceNo,
                                                  @RequestParam(value = "deliveryStatus",required = false) String deliveryStatus,
                                                  @RequestParam(value = "partnerAssetId",required = false)Long partnerAssetId,
                                                  @RequestParam(value = "name",required = false) String name,
                                                  @RequestParam(value = "qty",required = false)Integer qty,
                                                  @RequestParam(value = "page") int page,

                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<OrderItem> response = service.findAll(wareHouseId, referenceNo, deliveryStatus, partnerAssetId, name, qty,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Order Item</remarks>
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
        List<OrderItem> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/delivery")
    public ResponseEntity<Response> getAllDeliveries(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                  @RequestParam(value = "deliveryStatus",required = false)String deliveryStatus,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<OrderItem> response = service.getAllDeliveries(partnerId, deliveryStatus, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
