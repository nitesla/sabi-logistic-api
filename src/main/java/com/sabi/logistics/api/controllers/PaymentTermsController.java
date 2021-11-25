package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PaymentTermsDto;
import com.sabi.logistics.core.dto.response.PaymentTermsResponseDto;
import com.sabi.logistics.core.models.PaymentTerms;
import com.sabi.logistics.service.services.PaymentTermsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"paymentterms")
public class PaymentTermsController {


    private final PaymentTermsService service;

    public PaymentTermsController(PaymentTermsService service) {
        this.service = service;
    }


    /** <summary>
     * PaymentTerms creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new paymentTerms</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createPaymentTerms(@Validated @RequestBody PaymentTermsDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PaymentTermsResponseDto response = service.createPaymentTerms(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * PaymentTerms update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating paymentTerms</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updatePaymentTerms(@Validated @RequestBody  PaymentTermsDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PaymentTermsResponseDto response = service.updatePaymentTerms(request);
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
    public ResponseEntity<Response> getPaymentTerms(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        PaymentTermsResponseDto response = service.findPaymentTerms(id);
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
    public ResponseEntity<Response> getPaymentTerms(@RequestParam(value = "partnerAssetTypeId",required = false)Long partnerAssetTypeId,
                                                    @RequestParam(value = "days",required = false)Integer days,
                                                    @RequestParam(value = "partnerId",required = false)Long partnerId,
                                                    @RequestParam(value = "page") int page,
                                                    @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<PaymentTerms> response = service.findAll(partnerAssetTypeId, days, partnerId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a paymentTerms</remarks>
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
    public ResponseEntity<Response> getAll(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                           @RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<PaymentTerms> response = service.getAll(partnerId, isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
