package com.node.detection.service;

import com.node.detection.entity.mongo.Node;
import com.node.detection.entity.util.LineData;

/**
 * @author xinyu
 */
public interface NodeService {

    Node saveNode(Node node);
    LineData getLineDataByIMSI(String imsi);
}
