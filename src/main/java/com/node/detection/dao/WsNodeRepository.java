package com.node.detection.dao;

import com.node.detection.entity.ws.WsNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WsNodeRepository extends MongoRepository<WsNode, String> {

    WsNode save(WsNode wsNode);
}
