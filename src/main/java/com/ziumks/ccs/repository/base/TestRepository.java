package com.ziumks.ccs.repository.base;

import com.ziumks.ccs.model.entity.base.SystemMonitoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("testRepository")
public interface TestRepository extends JpaRepository<SystemMonitoringEntity, String>, TestRepositoryCustom {

    /**
     * 작업자 : 이상민
     * 일 자 : 2024.03.29 10:20
     * 종 류 : Repository - interface
     * 용 도 : Entity, JpaRepository 상속받아 간단하게 구현.
     **/

}
