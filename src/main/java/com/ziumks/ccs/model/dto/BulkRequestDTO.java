package com.ziumks.ccs.model.dto;


import lombok.*;

import java.util.List;
import java.util.Map;

@ToString
@Builder
@Data
public class BulkRequestDTO {
    private String tableName;
    private String indexName;
    private String docId;
    private List<Map<String,Object>> dataList;

}
