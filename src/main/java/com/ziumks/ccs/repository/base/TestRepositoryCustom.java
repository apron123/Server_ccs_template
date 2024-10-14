package com.ziumks.ccs.repository.base;

import com.ziumks.ccs.model.entity.base.SystemMonitoringEntity;

import java.util.List;

public interface TestRepositoryCustom {

    /**
     * 작업자 : 이상민
     * 일 자 : 2024.03.29 10:20
     * 종 류 : Repository - interface
     * 용 도 : Query Dsl 메소드 작성
     **/

    List<SystemMonitoringEntity> getSystemMonitoringList();

}
