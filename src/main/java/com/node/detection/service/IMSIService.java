package com.node.detection.service;

import com.node.detection.entity.mongo.IMSIInfo;

import java.util.List;


public interface IMSIService {

    /**
     * 把 imsi 插入到 mongodb 集合中
     * 若已存在、或者插入失败则返回false
     * @param imsiInfo imsiInfo 对象
     * @return 成功与否
     */
    boolean insertIMSI(IMSIInfo imsiInfo);

    /**
     * 把 imsi 从 mongodb 集合中删除
     * 若不存在、或者删除失败则返回false
     * @param imsi imsi
     * @return 成功与否
     */
    boolean deleteIMSI(String imsi);

    /**
     * 返回正常的 imsi 集合
     * @return imsi 列表集合
     */
    List<IMSIInfo> getIMSIlist();
}
