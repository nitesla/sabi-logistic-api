package com.sabi.logistics.api.runner;


import com.sabi.logistics.service.services.DashboardSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class CronJob {

    private DashboardSummaryService dashboardSummaryService;

    public CronJob(DashboardSummaryService dashboardSummaryService) {
        this.dashboardSummaryService = dashboardSummaryService;

    }


    @Scheduled(cron="${cronExpression}")
    public void moveTripRequest() {
        log.info("move trip request Scheduler called", new Date());
        dashboardSummaryService.moveTripRecordToDashBoard();
    }



}
