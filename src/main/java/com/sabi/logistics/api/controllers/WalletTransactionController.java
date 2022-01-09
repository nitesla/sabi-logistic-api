package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.WalletTransactionDto;
import com.sabi.logistics.core.dto.response.WalletTransactionResponseDto;
import com.sabi.logistics.core.models.WalletTransaction;
import com.sabi.logistics.service.services.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"wallettransaction")
public class WalletTransactionController {

    @Autowired
    private WalletTransactionService service;

    /** <summary>
     * wallet transaction creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new wallet transaction</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createWalletTransaction(@Validated @RequestBody WalletTransactionDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        WalletTransactionResponseDto response = service.createWalletTransaction(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



//    /** <summary>
//     * wallet transaction update endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for updating wallet transaction</remarks>
//     */
//
//    @PutMapping("")
//    public ResponseEntity<Response> updateWalletTransaction(@Validated @RequestBody  WalletTransactionDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        WalletTransactionResponseDto response = service.updateWalletTransaction(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getWalletTransactionById(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        WalletTransactionResponseDto response = service.findWalletTransaction(id);
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
    @GetMapping("/page")
    public ResponseEntity<Response> getWalletTransactions(@RequestParam(value = "driverWalletId",required = false)Long driverWalletId,
                                                     @RequestParam(value = "dropOffId",required = false)Long dropOffId,
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<WalletTransaction> response = service.findWalletTransactions(driverWalletId,dropOffId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
