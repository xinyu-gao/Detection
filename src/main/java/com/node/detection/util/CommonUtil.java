package com.node.detection.util;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class CommonUtil {
    public static void main(String[] args){
        log.info(String.valueOf(DateUtil.between(
                DateUtil.parse(DateUtil.now()),
                DateUtil.parse("2021/12/05 14:21"),
                DateUnit.MINUTE)));
        log.info(isTimeOverOneday("2021/12/05 14:21:00")+"");
        log.info(getMapXFromLbsLocation("14.23,15.34")+"");
//        log.info(DateUtil.formatBetween(,BetweenFormatter.Level.HOUR));
    }
    /**
     * 返回某个时间与当前时间的时间差
     *
     * @param time 需要计算的时间字符串
     * @return 时间差字符串
     */
    public static String getTimeDiffFromNow(String time) {
        return DateUtil.formatBetween(
                DateUtil.betweenMs(
                        DateUtil.parse(time),
                        DateUtil.parse(DateUtil.now())),
                BetweenFormatter.Level.MINUTE);
    }

    public static boolean isTimeOverOneday(String time) {
        return !"0".equals(time) &&
                        DateUtil.between(
                                DateUtil.parse(DateUtil.now()),
                                DateUtil.parse(time),
                                DateUnit.MINUTE)
                 > 1440;
    }

    public static double getMapXFromLbsLocation(String location) {
        return Double.parseDouble(location.split(",")[0]);
    }

    public static double getMapYFromLbsLocation(String location) {
        return Double.parseDouble(location.split(",")[1]);
    }
}
