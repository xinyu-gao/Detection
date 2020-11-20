package com.node.detection.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xinyu
 */
@Document
@Data
public class NodeValueBoundary implements Serializable {

    @Id
    private Long nodeId;


    @Field(name = "lower_boundary")
    private float lowerBoundary;


    @Field(name = "upper_boundary")
    private float upperBoundary;
}
