package com.ziumks.ccs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service("scheduledService")
public class ScheduledService {

    /**
     * 작업자 : 이상민
     * 일 자 : 2024.03.29 09:50
     * 종 류 : Service
     * 용 도 : 스케쥴러 서비스 로직 - Start Point
     *
     **/

    @Scheduled(cron = "0 * * * * *")
    public void schedule() {
        log.info("schedule start ..");
    }


}
