package com.sabi.logistics.api.runner;


import com.sabi.framework.globaladminintegration.AccessTokenService;
import com.sabi.framework.service.ExternalTokenService;
import com.sabi.logistics.service.services.DashboardSummaryService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@SuppressWarnings("ALL")
@Slf4j
@Service
@Component
public class CronJob {

    private DashboardSummaryService dashboardSummaryService;
    private ExternalTokenService externalTokenService;
    private final AccessTokenService accessTokenService;

    public CronJob(DashboardSummaryService dashboardSummaryService,ExternalTokenService externalTokenService,
                   AccessTokenService accessTokenService) {
        this.dashboardSummaryService = dashboardSummaryService;
        this.externalTokenService = externalTokenService;
        this.accessTokenService = accessTokenService;

    }



//    @Scheduled(cron="${cronExpression}")
//    public void moveTripRequest() {
//        log.info("move trip request Scheduler called", new Date());
//        dashboardSummaryService.moveTripRecordToDashBoard();
//    }


    @Scheduled(cron = "${cronExpression}")
    @SchedulerLock(name = "moveTripRequest", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    public void moveTripRequest() {
        log.info("move trip request Scheduler called", new Date());
        dashboardSummaryService.moveTripRecordToDashBoard();
    }



//    @Scheduled(cron="${tokenGen}")
//    public void getNewToken() {
//        log.info("::::::::::::: Cron Job Started at :::::::::::: :   %s", new Date());
//        externalTokenService.externalTokenRequest();
//    }

    @Scheduled(cron = "${tokenGen}")
    @SchedulerLock(name = "getNewToken", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    public void getNewToken() {
        log.info("::::::::::::: Cron Job Started at :::::::::::: :   %s", new Date());
        externalTokenService.externalTokenRequest();
    }


//    @Scheduled(cron="${globalAdminToken}")
//    public void globalToken() {
//        log.info("::::::::::::: global token Cron Job Started at :::::::::::: :   %s", new Date());
//        accessTokenService.globalTokenRequest();
//    }

    @Scheduled(cron = "${globalAdminToken}")
    @SchedulerLock(name = "globalToken", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    public void globalToken() {
        log.info("::::::::::::: global token Cron Job Started at :::::::::::: :   %s", new Date());
        accessTokenService.globalTokenRequest();
    }

}
