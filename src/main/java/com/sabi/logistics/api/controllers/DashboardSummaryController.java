package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.DashBoardSummaryRequest;
import com.sabi.logistics.core.dto.response.DashboardResponseDto;
import com.sabi.logistics.service.repositories.DashboardSummaryRepository;
import com.sabi.logistics.service.services.DashboardSummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("All")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"dashboard")
public class DashboardSummaryController {


    private final ResponseHelper responseHelper;
    private final DashboardSummaryService service;
    private final DashboardSummaryRepository dashboardSummaryRepository;

    public DashboardSummaryController(ResponseHelper responseHelper, DashboardSummaryService service,
                                      DashboardSummaryRepository dashboardSummaryRepository) {
        this.responseHelper = responseHelper;
        this.service = service;
        this.dashboardSummaryRepository = dashboardSummaryRepository;
    }

    @GetMapping("/logistics")
    public ResponseEntity<Response> getDashboardSummary(@RequestParam(value = "partnerId")Long partnerId,
                                                        @RequestParam(value = "startDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                        @RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){

        HttpStatus httpCode ;
        Response resp = new Response();
        DashboardResponseDto response = service.getDashboardSummary(partnerId, startDate, endDate);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);

    }



    @GetMapping("")
    public ResponseEntity<Response> dashboardSummary(@RequestParam(value = "outStanding",required = false)String outstanding,
                                                        @RequestParam(value = "partnerId",required = false)Long partnerId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){

        HttpStatus httpCode ;
        Response resp = new Response();
        if(outstanding !=null ) {
            List<DashBoardSummaryRequest> response = service.dashBoardOutStandingSummary(partnerId,startDate,endDate);
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Successful");
            resp.setData(response);
            httpCode = HttpStatus.CREATED;
            return new ResponseEntity<>(resp, httpCode);
        }else {
            List<DashBoardSummaryRequest> response = service.dashBoardSummary(partnerId,startDate,endDate);
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Successful");
            resp.setData(response);
            httpCode = HttpStatus.CREATED;
            return new ResponseEntity<>(resp, httpCode);
        }
    }





}
