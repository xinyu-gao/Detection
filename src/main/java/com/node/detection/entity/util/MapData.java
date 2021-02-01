package com.node.detection.entity.util;

import lombok.Data;

@Data
public class MapData {

    private String IMSI;

    private double mapX;

    private double mapY;

    private double distance;

    private int smog;

    private int envTemp;

    private String connectStatus;

    public MapData(String IMSI, double mapX, double mapY, double distance, int smog, int envTemp, String connectStatus) {
        this.IMSI = IMSI;
        this.mapX = mapX;
        this.mapY = mapY;
        this.distance = distance;
        this.smog = smog;
        this.envTemp = envTemp;
        this.connectStatus = connectStatus;
    }
}
