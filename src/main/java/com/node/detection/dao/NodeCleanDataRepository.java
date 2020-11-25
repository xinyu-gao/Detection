package com.node.detection.dao;

import com.node.detection.entity.mongo.Node;
import com.node.detection.entity.mongo.NodeCleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeCleanDataRepository extends MongoRepository<NodeCleanData, String> {

    /**
     * 根据 IMSI 号查询 Node 节点清理数据
     * @return Node 节点清理数据
     */
    NodeCleanData findByIMSI(String IMSI);
}
