package com.node.detection.service;

import com.node.detection.entity.mysql.Node;
import com.node.detection.util.PageResult;

/**
 * @author xinyu
 */
public interface NodeService {
    /**
     * 通过节点名字查询节点信息
     * @param name 要查询的节点名字
     * @return 节点信息
     */
    public Node findByName(String name);

    /**
     * 通过节点类型查询节点信息，分页查询
     * @param type 节点类型
     * @param page 开始页
     * @param size 查询的数据大小
     * @param <T> 泛型
     * @return 多个节点信息
     */
    public <T> PageResult<Node> findByType(String type, int page, int size);
}
