package com.sabi.logistics.api.runner;


import com.sabi.framework.service.ExternalTokenService;
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
    private ExternalTokenService externalTokenService;

    public CronJob(DashboardSummaryService dashboardSummaryService,ExternalTokenService externalTokenService) {
        this.dashboardSummaryService = dashboardSummaryService;
        this.externalTokenService = externalTokenService;

    }


    @Scheduled(cron="${cronExpression}")
    public void moveTripRequest() {
        log.info("move trip request Scheduler called", new Date());
        dashboardSummaryService.moveTripRecordToDashBoard();
    }



    @Scheduled(cron="${tokenGen}")
    public void getNewToken() {
        log.info("::::::::::::: Cron Job Started at :::::::::::: :   %s", new Date());
        externalTokenService.externalTokenRequest();
    }



}
