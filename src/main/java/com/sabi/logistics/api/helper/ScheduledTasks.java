package com.sabi.logistics.api.helper;


import com.sabi.logistics.service.integrations.SyncOrderService;
import com.sabi.logistics.service.repositories.TripRequestRepository;
import com.sabi.logistics.service.repositories.TripRequestResponseRepository;
import com.sabi.logistics.service.services.TripRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Amaechi Ekene on 26/06/2021.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {


@Value("${remote.web.service.url}")
private String requestUrl;
private final SyncOrderService syncOrderService;
private final TripRequestService tripRequestService;
//    @Autowired
//    private AsyncService asyncService;
//
//
//
////    @Scheduled(cron="${cronExpression}")
//    @Scheduled(fixedRateString = "${name-enquiry-rate}")
//    @SchedulerLock(name = "moveTripRequest", lockAtMostFor = "50s", lockAtLeastFor = "30s")
//    public void moveTripRequest() throws Exception {
//        log.info("move trip request Scheduler called");
//        this.asyncService.moveTripRecordToDashBoard();
//    }


    @Scheduled(fixedDelayString = "${sync-interval-in-miliseconds}")
    public void syncExternalDB() throws Exception{
        log.info("Scheduler for syncing orders with sabi called");
        syncOrderService.syncAndPullExternalOrders(requestUrl);
    }

    @Scheduled(fixedDelayString = "${trip.request.expired.time}")
    private void expireAcceptedAndExpiredTrips() throws Exception{
        log.info("Getting ready to expire unaccepted trips.");
        tripRequestService.expireUnAcceptedTrips();
    }


}
