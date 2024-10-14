package com.ziumks.ccs.repository.base;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ziumks.ccs.model.entity.base.QSystemMonitoringEntity;
import com.ziumks.ccs.model.entity.base.SystemMonitoringEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TestRepositoryCustomImpl implements TestRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SystemMonitoringEntity> getSystemMonitoringList() {
        return queryFactory
                .selectFrom(QSystemMonitoringEntity.systemMonitoringEntity)
                .fetch();
    }

}
