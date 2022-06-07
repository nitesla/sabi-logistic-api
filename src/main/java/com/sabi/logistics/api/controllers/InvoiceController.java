package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.InvoiceInvoiceItemDto;
import com.sabi.logistics.core.dto.request.InvoiceRequestDto;
import com.sabi.logistics.core.dto.response.InvoiceInvoiceItemResponseDto;
import com.sabi.logistics.core.dto.response.InvoiceResponseDto;
import com.sabi.logistics.core.models.Invoice;
import com.sabi.logistics.service.services.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"invoice")
public class InvoiceController {


    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }


    /** <summary>
     * Invoice creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Invoices</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createInvoice(@Validated @RequestBody InvoiceInvoiceItemDto request,HttpServletRequest httpServletRequest){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceInvoiceItemResponseDto response = service.createInvoiceAndItsItems(request,httpServletRequest);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Invoice update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating invoices</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateInvoice(@Validated @RequestBody  InvoiceRequestDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceResponseDto response = service.updateInvoice(request,request1);
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
    public ResponseEntity<Response> getInvoice(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceResponseDto response = service.findInvoice(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/invoiceNumber")
    public ResponseEntity<Response> getInvoiceByInvoiceNumber(@RequestParam(value = "invoiceNumber") String invoiceNumber){
        HttpStatus httpCode ;
        Response resp = new Response();
        InvoiceResponseDto response = service.findInvoiceNumber(invoiceNumber);
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
    public ResponseEntity<Response> getInvoices(@RequestParam(value = "referenceNo",required = false)String referenceNo,
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
        Page<Invoice> response = service.findAll(referenceNo, deliveryStatus, customerName, customerPhone, deliveryAddress, barCode, QRcode,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Invoices</remarks>
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
        List<Invoice> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

//    @GetMapping("/page")
//    public ResponseEntity<Response> getRecordByDateRange(@RequestParam(value = "date",required = false)String startDate, String endDate,String status,Long invoiceId){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<Invoice> response = service.findRecordByDateRange(startDate,endDate,status);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//    @GetMapping("/date")
//    public ResponseEntity<Response> getRecordByDateRange(@RequestParam(value = "wareHouseId",required = false)Long wareHouseId,
//                                                         @RequestParam(value = "status",required = false)String status,
//                                                         @RequestParam(value = "date",required = false)String isActive,
//                                                         @RequestParam(value = "endDate",required = false)String dateTime){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        double response = service.findByPresentDate(wareHouseId,status,isActive,dateTime);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


}
