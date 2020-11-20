package com.node.detection.dao;

import com.node.detection.entity.mongo.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chengzi
 */
@Repository
public interface NodeRepository extends MongoRepository<Node, String> {
    /**
     * 通过 IMSI 查找节点
     * @param name 节点名
     * @return 节点信息
     */
    Node findNodeByIMSI(String name);


    /**
     * 通过数据接受时间查找节点
     * @param currentTime 节点名
     * @return 节点信息
     */
    Node findByCurrentTime(String currentTime);

    Node save(Node node);
}
