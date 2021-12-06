package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DashboardRequestDto;
import com.sabi.logistics.core.dto.response.DashboardResponseDto;
import com.sabi.logistics.service.services.DashboardSummaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SuppressWarnings("All")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"logisticsdashboard")
public class DashboardSummaryController {


    private final ResponseHelper responseHelper;
    private final DashboardSummaryService service;

    public DashboardSummaryController(ResponseHelper responseHelper, DashboardSummaryService service) {
        this.responseHelper = responseHelper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> getDashboardSummary(@RequestBody @Valid DashboardRequestDto request){

        HttpStatus httpCode ;
        Response resp = new Response();
        DashboardResponseDto response = service.getDashboardSummary(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);

    }



}
