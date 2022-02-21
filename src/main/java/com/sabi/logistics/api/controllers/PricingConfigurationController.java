package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PricingConfigMasterRequest;
import com.sabi.logistics.core.dto.request.PricingConfigurationRequest;
import com.sabi.logistics.core.enums.DynamicType;
import com.sabi.logistics.service.services.PricingConfigurationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping(Constants.APP_CONTENT+"pricingconfiguration")
public class PricingConfigurationController {
    private final ResponseHelper responseHelper;
    private final PricingConfigurationService pricingConfigurationService;

    public PricingConfigurationController(ResponseHelper responseHelper, PricingConfigurationService pricingConfigurationService) {
        this.responseHelper = responseHelper;
        this.pricingConfigurationService = pricingConfigurationService;
    }

    @PostMapping
    public ResponseEntity<Response> createPricingConfiguration(@RequestBody @Valid PricingConfigurationRequest request){
        return responseHelper.buildResponse( pricingConfigurationService.createPricingConfiguration(request), HttpStatus.CREATED, "Successful");
    }

    @PostMapping("/masterpricingconfig")
    public ResponseEntity<Response> createMasterPricingConfiguration(@RequestBody @Valid PricingConfigMasterRequest request){
        return responseHelper.buildResponse( pricingConfigurationService.createMasterPricingConfiguration(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updatePricingConfiguration(@RequestBody @Valid PricingConfigurationRequest request){
        if(request.getId() == null || request.getId() < 0)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(pricingConfigurationService.updatePricingConfiguration(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPricingConfigurationById(@PathVariable long id){
        return responseHelper.buildResponse(pricingConfigurationService.findPricingConfiguration(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllPricingConfigurations(@RequestParam(value = "partnerId", required = false) Long partnerId,
                                                                @RequestParam(value = "routeType", required = false)String routeType,
                                                                @RequestParam(value = "arrivalStateId", required = false)Long arrivalStateId,
                                                                @RequestParam(value = "locationPreference", required = false)String locationPreference,
                                                                @RequestParam(value = "pricePerParameter", required = false)BigDecimal pricePerParameter,
                                                                @RequestParam(value = "pricePerWeight", required = false)BigDecimal pricePerWeight,
                                                                @RequestParam(value = "pricePerDistance", required = false)BigDecimal pricePerDistance,
                                                                @RequestParam(value = "pricePerTime", required = false)BigDecimal pricePerTime,
                                                                @RequestParam(value = "hasPreferentialPricing", required = false) Boolean hasPreferentialPricing,
                                                                @RequestParam(value = "dynamicType", required = false)DynamicType dynamicType,
                                                                @RequestParam(value = "tripType", required = false)String tripType,
                                                                @RequestParam(value = "page") int page,
                                                                @RequestParam(value = "pageSize") int pageSize){
        return responseHelper
                .buildResponse(pricingConfigurationService.findAll(partnerId, routeType, arrivalStateId, locationPreference, pricePerParameter, pricePerWeight,
                                pricePerDistance, pricePerTime, hasPreferentialPricing, dynamicType, tripType, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisEnableDto request){
        pricingConfigurationService.enableDisablePricingConfiguration(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        return responseHelper.buildResponse(pricingConfigurationService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }

}
