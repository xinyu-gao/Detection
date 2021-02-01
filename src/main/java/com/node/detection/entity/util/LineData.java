package com.node.detection.entity.util;

import lombok.Data;

import java.util.List;

@Data
public class LineData {

    private String imsi;

    private List<String> lineTimeList;

    private List<Integer> lineMcuTempList;

    private List<Integer> lineEnvTempList;

    private List<Integer> lineBrightList;

    private List<Integer> lineSmogList;

    public LineData(String imsi,
                    List<String> lineTimeList,
                    List<Integer> lineMcuTempList,
                    List<Integer> lineEnvTempList,
                    List<Integer> lineBrightList,
                    List<Integer> lineSmogList) {
        this.imsi = imsi;
        this.lineTimeList = lineTimeList;
        this.lineMcuTempList = lineMcuTempList;
        this.lineEnvTempList = lineEnvTempList;
        this.lineBrightList = lineBrightList;
        this.lineSmogList = lineSmogList;
    }
}
