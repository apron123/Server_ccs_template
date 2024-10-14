package com.ziumks.ccs.config.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {

    /**
     * 작업자 : 이상민
     * 일 자 : 2024.01.05 16:30
     * 종 류 : Config
     * 용 도 : 데이터베이스별 시스템 프로퍼티 값을 변수에 주입
     **/

    private Base base;

    @Data
    public static class Base{
        private String driver;
        private String dialect;
        private String url;
        private String username;
        private String password;
        private String schema;
        private String showSql;
        private String ddlAuto;
        private String maxPoolSize;
    }

}
