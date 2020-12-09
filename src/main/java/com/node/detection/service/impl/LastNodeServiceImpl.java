package com.node.detection.service.impl;

import com.node.detection.dao.LastNodeRepository;
import com.node.detection.entity.mongo.LastNode;
import com.node.detection.service.LastNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LastNodeServiceImpl implements LastNodeService {

    @Autowired
    private LastNodeRepository lastNodeRepository;

    @Override
    public List<LastNode> getAllLastNode() {
        return lastNodeRepository.findAll();
    }

    @Override
    public LastNode deleteIMSIFromLastNodeList(String imsi) {
        return lastNodeRepository.deleteLastNodeByIMSI(imsi);
    }

    @Override
    public LastNode saveLastNode(LastNode lastNode) {
        return lastNodeRepository.save(lastNode);
    }
}
