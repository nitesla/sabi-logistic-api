package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DriverWalletDto;
import com.sabi.logistics.core.dto.response.DriverWalletResponseDto;
import com.sabi.logistics.core.models.DriverWallet;
import com.sabi.logistics.service.services.DriverWalletService;
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
@RequestMapping(Constants.APP_CONTENT+"driverwallet")
public class DriverWalletController {

    @Autowired
    private DriverWalletService service;

    /** <summary>
     * driver wallet creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new driver wallet</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createDriverWallet(@Validated @RequestBody DriverWalletDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DriverWalletResponseDto response = service.createDriverWallet(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * driver wallet update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating driver wallet</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateDriverWallet(@Validated @RequestBody  DriverWalletDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DriverWalletResponseDto response = service.updateDriverWallet(request,request1);
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
    public ResponseEntity<Response> getDriverWallet(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DriverWalletResponseDto response = service.findDriverWallet(id);
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
    public ResponseEntity<Response> getDriverWallets(@RequestParam(value = "driverId",required = false)Long driverId,
                                            @RequestParam(value = "page") int page,
                                            @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DriverWallet> response = service.findDriverWallets(driverId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
