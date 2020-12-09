package com.node.detection.service;

import com.node.detection.entity.mongo.LastNode;

import java.util.List;

public interface LastNodeService {

    List<LastNode> getAllLastNode();

    LastNode deleteIMSIFromLastNodeList(String imsi);

    LastNode saveLastNode(LastNode lastNode);
}
