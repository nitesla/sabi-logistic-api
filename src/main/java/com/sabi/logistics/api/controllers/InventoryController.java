package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.InventoryDto;
import com.sabi.logistics.core.dto.response.InventoryResponseDto;
import com.sabi.logistics.core.models.Inventory;
import com.sabi.logistics.service.services.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createInventory(@Validated @RequestBody InventoryDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        InventoryResponseDto response = service.createInventory(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    public ResponseEntity<Response> updateInventory(@Validated @RequestBody  InventoryDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        InventoryResponseDto response = service.updateInventory(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getInventory(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        InventoryResponseDto response = service.findInventoryById(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getCountries(@RequestParam(value = "thirdPartyId",required = false) Long thirdPartyId,
                                                  @RequestParam(value = "productName",required = false) String productName,
//                                                  @RequestParam(value = "qty",required = false) int qty,
                                                  @RequestParam(value = "totalAmount",required = false) BigDecimal totalAmount,
                                                  @RequestParam(value = "status",required = false) String status,
                                                  @RequestParam(value = "deliveryPartnerName",required = false) String deliveryPartnerName,
                                                  @RequestParam(value = "deliveryPartnerEmail",required = false) String deliveryPartnerEmail,
                                                  @RequestParam(value = "deliveryPartnerPhone",required = false) String deliveryPartnerPhone,
                                                  @RequestParam(value = "partnerId",required = false) Long partnerId,
                                                  @RequestParam(value = "shippingId",required = false) Long shippingId,
                                                 @RequestParam(value = "warehouseId",required = false) Long warehouseId,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Inventory> response = service.findAll(thirdPartyId,productName,totalAmount,status,deliveryPartnerName,deliveryPartnerEmail,deliveryPartnerPhone,partnerId,shippingId,warehouseId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

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


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Inventory> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
