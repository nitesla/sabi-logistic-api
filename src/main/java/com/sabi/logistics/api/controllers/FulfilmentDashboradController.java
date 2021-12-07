package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.models.FulfilmentDashboard;
import com.sabi.logistics.service.services.FulfilmentDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"dashboard")
public class FulfilmentDashboradController {

    @Autowired
    private FulfilmentDashboardService service;

    @GetMapping("/{wareHouseId}")
    public ResponseEntity<Response> ProcessRecordForDashboard(@PathVariable Long wareHouseId){
        HttpStatus httpCode ;
        Response resp = new Response();
        FulfilmentDashboard response = service.findAll("",wareHouseId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getRecordByDateRange(@RequestParam(value = "date",required = false)String startDate, String endDate){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<FulfilmentDashboard> response = service.findRecordByDateRange(startDate,endDate);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
