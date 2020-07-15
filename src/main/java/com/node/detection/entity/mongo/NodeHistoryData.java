package com.node.detection.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xinyu
 */
@Document
@Data
public class NodeHistoryData implements Serializable {

    @Id
    private Date time;

    @Id
    private Long nodeId;

    @Field(name = "node_type")
    private String nodeType;

    @Field(name = "detection_value")
    private float detectionValue;

    @Field(name = "node_temperature")
    private float nodeTemperature;

    @Field(name = "is_normal")
    private Boolean isNormal;

}
