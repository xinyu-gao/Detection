package com.node.detection.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Data
@Document
public class IMSIInfo {

    /**
     * imsi
     */
    @Field(name = "imsi")
    private String imsi;

    /**
     * 该 imsi 上次发送数据的时间
     */
    @Field(name = "last_send_time")
    private String lastSendTime;

    public IMSIInfo(String imsi, String currentTime) {
    }
}
