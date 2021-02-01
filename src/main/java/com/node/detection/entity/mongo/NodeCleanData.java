package com.node.detection.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 存储 Node 清理数据
 */
@Document
@Data
public class NodeCleanData implements Serializable {

    @Id
    @Field(name = "imsi")
    private String IMSI;

    @Field(name = "last_clean_time")
    private String lastCleanTime;

    @Field(name = "day_clean_time")
    private int dayCleanCount;

    @Field(name = "week_clean_time")
    private int weekCleanCount;

    @Field(name = "month_clean_time")
    private int monthCleanCount;

    @Field(name = "year_clean_time")
    private int yearCleanCount;

}
