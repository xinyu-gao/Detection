package com.node.detection.service.impl;

import com.node.detection.dao.NodeRepository;
import com.node.detection.entity.mongo.Node;
import com.node.detection.service.NodeService;
import com.node.detection.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xinyu
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

//    @Override
//    public Node findByName(String name) {
//        return nodeRepository.findByName(name);
//    }
//
//    @Override
//    public <T> PageResult<Node> findByType(String type, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return PageResult.restructure(nodeRepository .findByType(type, pageable));
//    }

    @Override
    @Transactional
    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }
}
