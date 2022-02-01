package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.StateDto;
import com.sabi.logistics.core.dto.request.TollPricesDto;
import com.sabi.logistics.core.dto.response.StateResponseDto;
import com.sabi.logistics.core.dto.response.TollPricesResponseDto;
import com.sabi.logistics.core.models.State;
import com.sabi.logistics.core.models.TollPrices;
import com.sabi.logistics.service.services.TollPriceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"tollprice")
public class TollPricesController {

    private final TollPriceService service;

    public TollPricesController(TollPriceService service) {
        this.service = service;
    }


    /** <summary>
     * TollPrices creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new TollPrices</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createTollPrices(@Validated @RequestBody TollPricesDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TollPricesResponseDto response = service.createTollPrice(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * TollPrice update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating TollPrice</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateTollPrices(@Validated @RequestBody  TollPricesDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TollPricesResponseDto response = service.updateTollPrice(request);
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
    public ResponseEntity<Response> getTollPrice(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        TollPricesResponseDto response = service.findTollPriceById(id);
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
    public ResponseEntity<Response> getTollPrices(@RequestParam(value = "routeLocationId",required = false)Long routeLocationId,
                                              @RequestParam(value = "assestTypeId",required = false)Long assestTypeId,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<TollPrices> response = service.findAll(routeLocationId,assestTypeId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Toll price</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableState(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "countryId",required = false)Long countryId){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<State> response = service.getAllByCountryId(countryId);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//    @GetMapping("/active/list")
//    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<State> response = service.getAll(isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
}
