package com.node.detection.service.impl;

import com.node.detection.dao.NodeCleanDataRepository;
import com.node.detection.entity.mongo.NodeCleanData;
import com.node.detection.entity.util.LineData;
import com.node.detection.service.NodeCleanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeCleanDataServiceImpl implements NodeCleanDataService {

    @Autowired
    private NodeCleanDataRepository nodeCleanDataRepository;

    @Override
    public NodeCleanData getNodeCleanDataByIMSI(String IMSI) {
        return nodeCleanDataRepository.findByIMSI(IMSI);
    }

}
