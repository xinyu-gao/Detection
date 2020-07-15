package com.node.detection.entity.mysql;

import lombok.Data;

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
@Table(name = "node_value_boundary")
public class NodeValueBoundary implements Serializable {

    @Id
    private Long nodeId;


    @Column(name = "lower_boundary")
    private float lowerBoundary;


    @Column(name = "upper_boundary")
    private float upperBoundary;
}
