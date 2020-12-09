package com.node.detection.dao;

import com.node.detection.entity.mongo.LastNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chengzi
 */
@Repository
public interface LastNodeRepository extends MongoRepository<LastNode, String> {
    /**
     * 通过 IMSI 查找节点
     * @param imsi 节点名
     * @return 节点信息
     */
    LastNode findLastNodeByIMSI(String imsi);

    LastNode save(LastNode node);

    @Override
    List<LastNode> findAll();

    LastNode deleteLastNodeByIMSI(String imsi);
}
