package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.response.FulfilmentDashBoardResponseDto;
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
@RequestMapping(Constants.APP_CONTENT+"fulfillmentdashboard")
public class FulfilmentDashboradController {

    @Autowired
    private FulfilmentDashboardService service;

//    @GetMapping("/record")
//    public ResponseEntity<Response> ProcessRecordForDashboard(@RequestParam(value = "wareHouseId", required = true) Long wareHouseId,
//                                                                @RequestParam(value = "startDate",required = true)String startDate,
//                                                              @RequestParam(value = "endDate",required = true)String endDate){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        FulfilmentDashboard response = service.findAll(wareHouseId,startDate,endDate);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }

    @GetMapping("/record")
    public ResponseEntity<Response> getRecordByDateRange(@RequestParam(value = "startDate",required = false)String startDate,
                                                         @RequestParam(value = "endDate",required = false)String endDate,
                                                         @RequestParam(value = "wareHouseId",required = false)Long wareHouseId,
                                                         @RequestParam(value = "partnerId",required = false) Long partnerId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<FulfilmentDashboard> response = service.findRecordByDateRange(startDate, endDate, wareHouseId, partnerId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
