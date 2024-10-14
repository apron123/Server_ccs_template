package com.ziumks.ccs.model.dto;

import lombok.*;


@ToString
@Builder
@Data
public class BulkResponseDTO {
    private int responseCode;
    private String tableName;
    private String indexName;
    private String msg;


}
