package com.ziumks.ccs.model.entity.base;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "system_monitoring")
public class SystemMonitoringEntity {

    @Transient
    private String tableNm = "system_monitoring";
    @Transient
    private String pkNm = "base_table_name";

    @Id
    @Column(name = "base_table_name", length = 64)
    private String baseTableName;

    @Column(name = "base_schema_name", length = 50, nullable = false)
    private String baseSchemaName;

    @Column(name = "crawler_status", nullable = false)
    private int crawlerStatus;

    @Column(name = "save_status", nullable = false)
    private int saveStatus;

    @Column(name = "elastic_status", nullable = false)
    private int elasticStatus;

    @Column(name = "system_name", length = 100)
    private String systemName;

    @Column(name = "data_time")
    private Timestamp dataTime;

    @Column(name = "msg", length = 500)
    private String msg;

    @Column(name = "table_description", length = 100)
    private String tableDescription;

    @Column(name = "data_status", length = 5)
    private String dataStatus;

    @Column(name = "collector_time")
    private Timestamp collectorTime;

    @Column(name = "collector_status", length = 5)
    private String collectorStatus;

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("base_table_name", this.baseTableName);
        map.put("base_schema_name", this.baseSchemaName);
        map.put("crawler_status", this.crawlerStatus);
        map.put("save_status", this.saveStatus);
        map.put("elastic_status", this.elasticStatus);
        map.put("system_name", this.systemName);
        map.put("data_time", this.dataTime != null ? this.dataTime.toLocalDateTime() : null);
        map.put("msg", this.msg);
        map.put("table_description", this.tableDescription);
        map.put("data_status", this.dataStatus);
        map.put("collector_time", this.collectorTime != null ? this.collectorTime.toLocalDateTime() : null);
        map.put("collector_status", this.collectorStatus);

        return map;
    }
}
