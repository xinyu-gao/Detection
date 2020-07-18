package com.node.detection.entity.mysql;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xinyu
 */
@Data
@Entity
@Table(name = "node")
public class Node implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    private Long id;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点类型
     */
    private String type;
    /**
     * 节点地址
     */
    @Column(name = "home_adderss")
    private Address homeAddress;
    /**
     * 节点监控的值
     */
    private float value;

    @Data
    private static class Address implements Serializable {

        /**
         * 节点地址的横坐标
         */
        private float addressX;
        /**
         * 节点地址的纵坐标
         */
        private float addressY;
    }
}
