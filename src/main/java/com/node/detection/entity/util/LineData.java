package com.node.detection.entity.util;

import lombok.Data;

import java.util.List;

@Data
public class LineData {

    private String imsi;

    private List<String> lineTimeList;

    private List<Integer> lineMcuTempList;

    private List<Integer> lineSignalPowerList;

    private List<Integer> lineBrightList;


    public LineData(String imsi, List<String> lineTimeList, List<Integer> lineMcuTempList, List<Integer> lineSignalPowerList ,List<Integer> lineBrightList) {
        this.imsi = imsi;
        this.lineTimeList = lineTimeList;
        this.lineMcuTempList = lineMcuTempList;
        this.lineSignalPowerList = lineSignalPowerList;
        this.lineBrightList = lineBrightList;
    }
}
