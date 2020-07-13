package com.node.detection.dao;

import com.node.detection.entity.mysql.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    Node findByName(String name);
    Page<Node> findByType(String type, Pageable pageable);
}
