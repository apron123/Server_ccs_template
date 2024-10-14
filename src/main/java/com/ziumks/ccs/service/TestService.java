package com.ziumks.ccs.service;


import com.ziumks.ccs.model.entity.base.SystemMonitoringEntity;
import com.ziumks.ccs.repository.base.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("testService")
public class TestService {

    /**
     * 작업자 : 이상민
     * 일 자 : 2024.03.29 16:30
     * 종 류 : Service
     * 용 도 : 서비스 로직
     **/

    @Autowired
    TestRepository testRepository;

    public void testService() {
        List<SystemMonitoringEntity> systemMonitoringEntity = testRepository.getSystemMonitoringList();
        log.info("systemMonitoringEntity size : " + systemMonitoringEntity.size());
    }

}