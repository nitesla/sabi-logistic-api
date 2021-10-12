package com.sabilogistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.utils.Constants;
import com.sabilogistics.service.services.PartnerCategoriesService;
import com.sabilogisticscore.dto.request.PartnerCategoriesDto;
import com.sabilogisticscore.dto.response.PartnerCategoriesResponseDto;
import com.sabilogisticscore.models.PartnerCategories;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sabi.framework.dto.responseDto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.sabi.framework.utils.CustomResponseCode;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"partnerCategories")
public class PartnerCategoriesController {

    private final PartnerCategoriesService service;

    public PartnerCategoriesController(PartnerCategoriesService service) {
        this.service = service;
    }

    @PostMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> createPartnerCategory(@Validated @RequestBody PartnerCategoriesDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerCategoriesResponseDto response = service.createPartnerCategory(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updatePartnerCategory(@Validated @RequestBody  PartnerCategoriesDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerCategoriesResponseDto response = service.updatePartnerCategory(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getPartnerCategoryById(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerCategoriesResponseDto response = service.findById(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("")
    public ResponseEntity<Response> getPartnerCategories(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                         @RequestParam(value = "categoryId") Long categoryId,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<PartnerCategories> response = service.findAll(partnerId,categoryId, PageRequest.of(page, pageSize));
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
        service.enableDisEnable(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<PartnerCategories> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}