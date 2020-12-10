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

    /**
     * 该 imsi 上次发送数据的时间到目前时间的间隔
     */
    private String lastToNowTime;

     public IMSIInfo(String imsi, String currentTime, String lastToNowTime) {
        this.imsi = imsi;
        this.lastSendTime = currentTime;
        this.lastToNowTime = lastToNowTime;
    }
}
