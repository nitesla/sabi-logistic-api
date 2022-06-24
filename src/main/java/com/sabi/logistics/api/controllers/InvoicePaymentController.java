package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.InvoiceInvoiceItemDto;
import com.sabi.logistics.core.dto.request.InvoicePaymentRequestDto;
import com.sabi.logistics.core.dto.request.InvoiceRequestDto;
import com.sabi.logistics.core.dto.response.InvoiceInvoiceItemResponseDto;
import com.sabi.logistics.core.dto.response.InvoicePaymentResponseDto;
import com.sabi.logistics.core.dto.response.InvoiceResponseDto;
import com.sabi.logistics.core.models.Invoice;
import com.sabi.logistics.core.models.InvoicePayment;
import com.sabi.logistics.service.services.InvoicePaymentService;
import com.sabi.logistics.service.services.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"invoicepayment")
public class InvoicePaymentController {


    private final InvoicePaymentService service;

    public InvoicePaymentController(InvoicePaymentService service) {
        this.service = service;
    }


    /** <summary>
     * InvoicePayment creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new InvoicePayment</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createInvoicePayment(@Validated @RequestBody InvoicePaymentRequestDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Successful");
        resp.setData(service.createInvoicePayment(request));
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    /** <summary>
     * InvoicePayment update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating invoicePayment</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateInvoicePayment(@Validated @RequestBody  InvoicePaymentRequestDto request){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Update Successful");
        resp.setData(service.updateInvoicePayment(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getInvoicePayment(@PathVariable Long id){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.findInvoicePayment(id));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /** <summary>
     * Get single record endpoint by paymentReference
     * </summary>
     * <remarks>This endpoint is responsible for getting a single invoicePayment record by paymentReference</remarks>
     */
    @GetMapping("/paymentReference")
    public ResponseEntity<Response> getInvoicePaymentByPaymentReference(@RequestParam(value = "paymentReference") String paymentReference){
        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.findByPaymentReference(paymentReference));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> searchInvoicePayments(@RequestParam(value = "paymentChannel",required = false)String paymentChannel,
                                              @RequestParam(value = "totalAmount",required = false) BigDecimal totalAmount,
                                              @RequestParam(value = "amountCollected",required = false)BigDecimal amountCollected,
                                              @RequestParam(value = "balanceBefore",required = false)BigDecimal balanceBefore, @RequestParam(value = "balanceAfter",required = false)BigDecimal balanceAfter,
                                              @RequestParam(value = "invoiceId",required = false) Long invoiceId,
                                              @RequestParam(value = "paymentReference",required = false)String paymentReference,
                                              @RequestParam(value = "isActive",required = false) Boolean isActive,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){

        Response resp = new Response(CustomResponseCode.SUCCESS, "Record fetched successfully !");
        resp.setData(service.searchInvoicePayment(paymentChannel, totalAmount, amountCollected, balanceBefore, balanceAfter, invoiceId, paymentReference,isActive,PageRequest.of(page, pageSize)));
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
        resp.setData(service.findByIsActive(isActive));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
