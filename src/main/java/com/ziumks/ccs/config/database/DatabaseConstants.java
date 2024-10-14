package com.ziumks.ccs.config.database;

public class DatabaseConstants {

    /**
     * 작업자 : 이상민
     * 일 자 : 2024.01.05 16:30
     * 종 류 : Config
     * 용 도 : 데이터베이스별 엔티티매니저 설정 변수에 주입
     **/

    public class BaseDatabase{
        public static final String entity_manager_factory = "baseEntityManagerFactory";
        public static final String tx_manager = "baseTransactionManager";
        public static final String package_domain = "com.ziumks.ccs.model.entity.base";
        public static final String package_repository = "com.ziumks.ccs.repository.base";
    }

}
