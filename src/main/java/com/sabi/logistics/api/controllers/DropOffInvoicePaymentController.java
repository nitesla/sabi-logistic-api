package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DropOffInvoicePaymentRequestDto;
import com.sabi.logistics.core.enums.PaymentChannel;
import com.sabi.logistics.service.services.DropOffInvoicePaymentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"dropoffinvoicepayment")
public class DropOffInvoicePaymentController {


    private final DropOffInvoicePaymentService service;

    public DropOffInvoicePaymentController(DropOffInvoicePaymentService service) {
        this.service = service;
    }

    /** <summary>
     * DropOffInvoicePayment creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new DropOffInvoicePayment</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createDropOffInvoicePayment(@Validated @RequestBody DropOffInvoicePaymentRequestDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Successful");
        resp.setData(service.createDropOffInvoicePayment(request));
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    /** <summary>
     * DropOffInvoicePayment update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating dropOffInvoicePayment</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateDropOffInvoicePayment(@Validated @RequestBody  DropOffInvoicePaymentRequestDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Update Successful");
        resp.setData(service.updateDropOffInvoicePayment(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getDropOffInvoicePayment(@PathVariable Long id){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.findSingleDropOffInvoicePayment(id));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> searchDropOffInvoicePayments( @RequestParam(value = "invoiceReferenceNo",required = false) String invoiceReferenceNo,
                                                                  @RequestParam(value = "paymentChannel",required = false) PaymentChannel paymentChannel,
                                                                  @RequestParam(value = "deliveryStatus",required = false)String deliveryStatus,
                                                                  @RequestParam(value = "paymentReference",required = false)String paymentReference,
                                                                  @RequestParam(value = "customerName",required = false)String customerName,
                                                                  @RequestParam(value = "customerPhone",required = false)String customerPhone,
                                                                  @RequestParam(value = "isActive",required = false) Boolean isActive,
                                                                  @RequestParam(value = "page") int page,
                                                                  @RequestParam(value = "pageSize") int pageSize){

        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.searchAllDropOffInvoicePayment(invoiceReferenceNo,paymentChannel, deliveryStatus,paymentReference, customerName, customerPhone,isActive,PageRequest.of(page, pageSize)));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a dropOffInvoicePayment</remarks>
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
        resp.setData(service.findAllDropOffInvoicePaymentByIsActive(isActive));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
