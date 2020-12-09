package com.node.detection.service;

import com.node.detection.entity.mongo.NodeCleanData;

public interface NodeCleanDataService {

    /**
     * 根据 IMSI 号查询 Node 节点清理数据
     * @return Node 节点清理数据
     */
    NodeCleanData getNodeCleanDataByIMSI(String ISMI);

}
