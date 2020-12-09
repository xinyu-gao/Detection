package com.node.detection.service.impl;

import com.node.detection.dao.NodeRepository;
import com.node.detection.entity.mongo.Node;
import com.node.detection.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xinyu
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    @Transactional
    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }
}
