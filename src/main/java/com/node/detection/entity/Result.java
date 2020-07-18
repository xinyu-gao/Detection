package com.node.detection.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xinyu
 */
@Data
@Component
public class Result implements Serializable {
    private Object result;

}
