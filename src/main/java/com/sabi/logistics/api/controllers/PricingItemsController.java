package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PricingItemsRequest;
import com.sabi.logistics.service.services.PricingItemsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping(Constants.APP_CONTENT + "pricingitems")
public class PricingItemsController {
    private final ResponseHelper responseHelper;
    private final PricingItemsService pricingItemsService;

    public PricingItemsController(ResponseHelper responseHelper, PricingItemsService pricingItemsService) {
        this.responseHelper = responseHelper;
        this.pricingItemsService = pricingItemsService;
    }

    @PostMapping
    public ResponseEntity<Response> createpricingItems(@RequestBody @Valid PricingItemsRequest request) {
        return responseHelper.buildResponse(pricingItemsService.createpricingItems(request), HttpStatus.CREATED, "Successful");
    }

    @PutMapping
    public ResponseEntity<Response> updatepricingItems(@RequestBody @Valid PricingItemsRequest request) {
        if (request.getId() == null || request.getId() < 0)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Id Can not be empty");
        return responseHelper.buildResponse(pricingItemsService.updatepricingItems(request), HttpStatus.OK, "Update Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getpricingItemsById(@PathVariable long id) {
        return responseHelper.buildResponse(pricingItemsService.findpricingItems(id), HttpStatus.OK, "Record fetched successfully !");
    }

    @GetMapping
    public ResponseEntity<Response> getAllpricingItemss(@RequestParam(value = "pricingConfigurationId", required = false) Long pricingConfigurationId,
                                                        @RequestParam(value = "assetTypeId", required = false) Long assetTypeId,
                                                        @RequestParam(value = "assetTypeId", required = false) BigDecimal price,
                                                        @RequestParam(value = "page") int page,
                                                        @RequestParam(value = "pageSize") int pageSize) {
        return responseHelper
                .buildResponse(pricingItemsService.findAll(pricingConfigurationId, assetTypeId, price, PageRequest.of(page, pageSize)),
                        HttpStatus.OK, "Record fetched successfully !");
    }

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@RequestBody EnableDisEnableDto request) {
        pricingItemsService.enableDisEnablepricingItems(request);
        return responseHelper.buildEnableDisable();
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive") Boolean isActive) {
        return responseHelper.buildResponse(pricingItemsService.getAll(isActive), HttpStatus.OK, "Record fetched successfully !");
    }

}
