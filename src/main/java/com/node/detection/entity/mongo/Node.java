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
 * 存储所有接收过的数据，用于历史数据查询等
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
    private int sendFrequencySec;

    /**
     * IMEI号
     */
    @Field(name = "imei")
    private String IMEI;

    /**
     * 芯片温度
     */
    @Field(name = "mcu_temp")
    private int mcuTemp;


    /**
     * 光线强度
     */
    @Field(name = "bright")
    private int bright;


    /**
     * 距离，例如 0.3 表示 30 %
     */
    @Field(name = "distance")
    private double distance;

    /**
     * 烟雾
     */
    @Field(name = "smog")
    private int smog;

    /**
     * 环境温度
     */
    @Field(name = "env_temp")
    private int envTemp;

    /**
     * LBS定位信息
     */
    @Field(name = "lbs_location")
    private String lbsLocation;


}
