package com.node.detection.entity.util;

import lombok.Data;

@Data
public class RecLocation {
    private double mapX;

    private double mapY;

    private double timeWeight;

    private double start;

    private double end;

    public RecLocation(double mapX, double mapY, double timeWeight, double start, double end) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.timeWeight = timeWeight;
        this.start = start;
        this.end = end;
    }
}
