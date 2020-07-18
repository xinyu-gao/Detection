package com.node.detection.dao;

import com.node.detection.entity.mysql.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author chengzi
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    /**
     * select * from node where name = ?
     * @param name 节点名
     * @return 节点信息
     */
    @Transactional
    Node findByName(String name);

    /**
     * select * from node where type = ? limit 1, 3
     * @param type 节点类型
     * @param pageable 分页
     * @return 节点信息分页结果
     */
    Page<Node> findByType(String type, Pageable pageable);
}
