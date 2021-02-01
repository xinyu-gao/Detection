package com.node.detection.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 用于查询每个 node 节点最新的数据
 */
@Document
@Data
public class LastNode implements Serializable {

    /**
     * 帧号
     */
    @Field(name = "sn")
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


    /**
     * 是否倾斜
     */
    @Field(name = "heeling_condition")
    private boolean heelingCondition;

    /**
     * 是否着火
     */
    @Field(name = "is_on_fire")
    private boolean isOnFire;

    /**
     *  节点连接状态
     */
    @Field(name = "connect_status")
    private String connectStatus;

    /**
     *  上一次垃圾充满时间
     */
    @Field(name = "last_full_time")
    private String lastFullTime;

    /**
     * 上一次清理的时间
     */
    @Field(name = "last_clean_time")
    private String lastCleanTime;

    /**
     * 当天清理次数
     */
    @Field(name = "day_clean_time")
    private int dayCleanCount;

    /**
     * 当周清理次数
     */
    @Field(name = "week_clean_time")
    private int weekCleanCount;

    /**
     * 当月清理次数
     */
    @Field(name = "month_clean_time")
    private int monthCleanCount;

    /**
     * 当年清理次数
     */
    @Field(name = "year_clean_time")
    private int yearCleanCount;

}
