package com.sabi.logistics.api.controllers;

import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import com.sabi.logistics.service.services.DashboardSummaryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("All")
@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT+"dashboard")
public class DashboardSummaryController {


    private final ResponseHelper responseHelper;
    private final DashboardSummaryService service;

    public DashboardSummaryController(ResponseHelper responseHelper, DashboardSummaryService service) {
        this.responseHelper = responseHelper;
        this.service = service;
    }

//    @GetMapping("/logistics")
//    public ResponseEntity<Response> getDashboardSummary(@RequestParam(value = "partnerId")Long partnerId,
//                                                        @RequestParam(value = "startDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//                                                        @RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
//
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        DashboardResponseDto response = service.getDashboardSummary(partnerId, startDate, endDate);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.CREATED;
//        return new ResponseEntity<>(resp, httpCode);
//
//    }



}
