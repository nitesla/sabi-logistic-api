package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.RouteLocationRequest;
import com.sabi.logistics.core.dto.request.RouteLocationTollPriceRequest;
import com.sabi.logistics.core.dto.response.RouteLocationTollPriceResponse;
import com.sabi.logistics.service.services.RouteLocationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping(Constants.APP_CONTENT + "routelocation")
public class RouteLocationController {
    private final ResponseHelper responseHelper;
    private final RouteLocationService routeLocationService;

    public RouteLocationController(ResponseHelper responseHelper, RouteLocationService routeLocationService) {
        this.responseHelper = responseHelper;
        this.routeLocationService = routeLocationService;
    }

    @PostMapping
    public ResponseEntity<Response> createrouteLocation(@RequestBody @Valid RouteLocationRequest request) {
        return responseHelper.buildResponse(routeLocationService.createrouteLocation(request), HttpStatus.CREATED, "Successful");
    }

    @PostMapping("tollprice")
    public ResponseEntity<Response> createShipments(@Validated @RequestBody RouteLocationTollPriceRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        RouteLocationTollPriceResponse response = routeLocationService.createrouteLocationTollPrice(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping
    public ResponseEntity<Response> updaterouteLocation(@RequestBody @Valid RouteLocationRequest request) {
        if (request.getId() == null || request.getId() < 0)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(routeLocationService.updaterouteLocation(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getrouteLocationById(@PathVariable long id) {
        return responseHelper.buildResponse(routeLocationService.findrouteLocation(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllrouteLocations(@RequestParam(value = "name",required = false) String name,
                                                         @RequestParam(value = "stateId",required = false) Long stateId,
                                                         @RequestParam(value = "tollRate",required = false) BigDecimal tollRate,
                                                         @RequestParam(value = "hasToll",required = false) Boolean hasToll,
                                                        @RequestParam(value = "page") int page,
                                                        @RequestParam(value = "pageSize") int pageSize) {
        return responseHelper
                .buildResponse(routeLocationService.findAll(name, stateId, tollRate, hasToll, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request) {
        routeLocationService.enableDisEnablerouteLocation(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive") Boolean isActive) {
        return responseHelper.buildResponse(routeLocationService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }

}
