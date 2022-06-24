package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.InvoiceItemRequestDto;
import com.sabi.logistics.core.dto.response.InvoiceItemResponseDto;
import com.sabi.logistics.core.models.InvoiceItem;
import com.sabi.logistics.service.services.InvoiceItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"invoiceitem")
public class InvoiceItemController {


    private final InvoiceItemService service;

    public InvoiceItemController(InvoiceItemService service) {
        this.service = service;
    }


    /** <summary>
     * Invoice Item creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Invoice Item</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createInvoiceItem(@Validated @RequestBody InvoiceItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceItemResponseDto response = service.createInvoiceItem(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Invoice item update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating invoice item</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateInvoiceItem(@Validated @RequestBody  InvoiceItemRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceItemResponseDto response = service.updateInvoiceItem(request);
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
    public ResponseEntity<Response> getInvoiceItem(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceItemResponseDto response = service.findInvoiceItem(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Get/search all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable
     * For  the dateRanges,  startDate and endDate should be in this  format
     *      startDate=YYYY-MM-DDTHH:mm:ss
     *      Example:
     *      startDate=2022-03-18T12:08:06
     *      endDate=2022-03-18T12:08:06
     * </remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> getInvoiceItems(@RequestParam(value = "wareHouseId",required = false)Long wareHouseId,
                                                  @RequestParam(value = "deliveryStatus",required = false) String deliveryStatus,
                                                  @RequestParam(value = "hasInventory",required = false) Boolean hasInventory,
                                                  @RequestParam(value = "productName",required = false) String productName,
                                                  @RequestParam(value = "qty",required = false)Integer qty,
                                                  @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                  @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                  @RequestParam(value = "customerName",required = false) String customerName,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<InvoiceItem> response = service.findAll(wareHouseId, deliveryStatus, hasInventory, productName, qty, startDate,endDate,customerName,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Invoice Item</remarks>
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
        List<InvoiceItem> response = service.getAll(isActive);
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
        Page<InvoiceItem> response = service.getAllDeliveries(partnerId, deliveryStatus, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



//    @GetMapping("/check")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "wareHosueId")Long wareHosueId){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<InvoiceItem> response = service.getAllByWareHouse(wareHosueId);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
}
