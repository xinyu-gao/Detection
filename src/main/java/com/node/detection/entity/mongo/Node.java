package com.node.detection.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xinyu
 */
@Document
@Data
public class Node implements Serializable {

    /**
     * 帧号
     */
    private String sn;

    /**
     * IMSI号
     */
    @Id
    @Field(name = "imsi")
    private String IMSI;

    /**
     * 服务器 IP
     */
    @Field(name = "server_ip")
    private String serverIP;

    /**
     * 服务器端口
     */
    @Field(name = "server_port")
    private String serverPort;

    /**
     * 发送时间
     */
    @Field(name = "current_time")
    private String currentTime;

    /**
     * 上传间隔（秒）
     */
    @Field(name = "send_frequency_sec")
    private String sendFrequencySec;

    /**
     * IMEI号
     */
    @Field(name = "imei")
    private String IMEI;

    /**
     * 芯片温度
     */
    @Field(name = "mcu_temp")
    private String mcuTemp;

    /**
     * 信号强度
     */
    @Field(name = "signal_power")
    private String signalPower;

    /**
     * 光线强度
     */
    @Field(name = "bright")
    private String bright;

    /**
     * TSI次数
     */
    @Field(name = "touch_num")
    private String touchNum;
    /**
     * LBS定位信息
     */
    @Field(name = "lbs_location")
    private String lbsLocation;


}
