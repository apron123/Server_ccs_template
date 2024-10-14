package com.ziumks.ccs.config.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

        // 스레드 풀 설정
        taskScheduler.setPoolSize(4);  // 원하는 스레드 수로 설정
        log.info("taskScheduler PoolSize : {}",taskScheduler.getPoolSize());
        taskScheduler.setThreadNamePrefix("webc-scheduler-");
        log.info("taskScheduler ThreadNamePrefix : {}",taskScheduler.getThreadNamePrefix());

        // 다양한 스레드 풀 설정을 추가할 수 있음

        return taskScheduler;
    }
}