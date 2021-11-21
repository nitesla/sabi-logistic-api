package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.ProductRequestDto;
import com.sabi.logistics.core.dto.response.ProductResponseDto;
import com.sabi.logistics.core.models.Product;
import com.sabi.logistics.service.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"product")
public class ProductController {


    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /** <summary>
     * Product creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new product</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createProduct(@Validated @RequestBody ProductRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        ProductResponseDto response = service.createProduct(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Product update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating product</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateProduct(@Validated @RequestBody  ProductRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        ProductResponseDto response = service.updateProduct(request);
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
    public ResponseEntity<Response> getProduct(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        ProductResponseDto response = service.findProduct(id);
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
    public ResponseEntity<Response> getProducts(@RequestParam(value = "thirdPartyId",required = false)Long thirdPartyId,
                                                @RequestParam(value = "name",required = false)String name,
                                                @RequestParam(value = "totalStock",required = false)Double totalStock,
                                                @RequestParam(value = "stockSold",required = false)Double stockSold,
                                                @RequestParam(value = "page") int page,
                                                @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Product> response = service.findAll(thirdPartyId,name,totalStock,stockSold, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Product</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisable(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive,
                                           @RequestParam(value = "thirdPartyId",required = false)Long thirdPartyId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Product> response = service.getAll(thirdPartyId, isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
