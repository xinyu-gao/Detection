package com.node.detection.entity.util;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
public class IMSIInfo {

    /**
     * imsi
     */
    private String imsi;

    /**
     * 该 imsi 上次发送数据的时间
     */
    private String lastSendTime;

    public IMSIInfo(String imsi, String currentTime) {
        this.imsi = imsi;
        this.lastSendTime = currentTime;
    }
}
