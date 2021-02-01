package com.node.detection.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.node.detection.entity.mongo.LastNode;
import com.node.detection.entity.mongo.Node;
import com.node.detection.entity.util.*;
import com.node.detection.service.LastNodeService;
import com.node.detection.service.NodeCleanDataService;
import com.node.detection.service.NodeService;
import com.node.detection.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xinyu
 */
@RestController
@RequestMapping("/node")
@Slf4j
public class NodeController {

    private final NodeCleanDataService nodeCleanDataService;

    private final LastNodeService lastNodeService;

    private final NodeService nodeService;

    @Autowired
    public NodeController(NodeCleanDataService nodeCleanDataService, LastNodeService lastNodeService, NodeService nodeService) {
        this.nodeCleanDataService = nodeCleanDataService;
        this.lastNodeService = lastNodeService;
        this.nodeService = nodeService;
    }

    @ApiOperation("查询node清理数据")
    @GetMapping("/get_clean_data")
    public HttpResult getNodeCleanData(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(nodeCleanDataService.getNodeCleanDataByIMSI(IMSI));
    }

    @ApiOperation("查询node曲线数据")
    @GetMapping("/line_data")
    public HttpResult getLineDataForCurrentNode(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(nodeService.getLineDataByIMSI(IMSI));
    }

    @ApiOperation("查询所有node最新数据")
    @GetMapping("/last_data")
    public HttpResult getNodeData(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageResult<LastNode> lastNodeList = lastNodeService.getLastNode(page, size);
        Map<String, Object> map = new HashMap<>();
        int nodeConnectCount = (int) lastNodeList.getList()
                .parallelStream()
                .filter(lastNode -> lastNode.getConnectStatus().equals("connecting"))
                .count();
        map.put("nodeNums", lastNodeList.getList().size());
        map.put("nodeConnectCount", nodeConnectCount);
        map.put("nodeConnectRate", NumberUtil.div(nodeConnectCount, lastNodeList.getList().size(), 2));
        map.put("nodeData", lastNodeList);
        return HttpResult.success(map);
    }

    @GetMapping("/map_data")
    public HttpResult getMapData() {
        List<LastNode> lastNodeList = lastNodeService.getAllLastNode();
        List<MapData> mapData = lastNodeList.stream()
                .map(lastNode -> new MapData(
                        lastNode.getIMSI(),
                        CommonUtil.getMapXFromLbsLocation(lastNode.getLbsLocation()),
                        CommonUtil.getMapYFromLbsLocation(lastNode.getLbsLocation()),
                        lastNode.getDistance(),
                        lastNode.getSmog(),
                        lastNode.getEnvTemp(),
                        lastNode.getConnectStatus())
                )
                .collect(Collectors.toList());
        return HttpResult.success(mapData);
    }

    @GetMapping("/rec_location")
    public HttpResult getRecLocation() {
        List<LastNode> lastNodeList = lastNodeService.getAllLastNode();
        log.info(lastNodeList.toString());
        double start = CommonUtil.getMapXFromLbsLocation(lastNodeList.get(0).getLbsLocation());
        double end = CommonUtil.getMapYFromLbsLocation(lastNodeList.get(0).getLbsLocation());
        List<RecLocation> recLocationsList = lastNodeList.stream()
                .map(lastNode ->
                        new RecLocation(
                                CommonUtil.getMapXFromLbsLocation(lastNode.getLbsLocation()),
                                CommonUtil.getMapYFromLbsLocation(lastNode.getLbsLocation()),
                                CommonUtil.isTimeOverOneday(lastNode.getCurrentTime()) ? 0.8 : 1,
                                start, end
                        )
                )
                .collect(Collectors.toList());
        log.info(recLocationsList.toString());
        return HttpResult.success(recLocationsList);
    }

    @GetMapping("/path_plan")
    public HttpResult getPathPlan(@RequestParam("info") String info) {
        String[] temp = info.split(",");
        int columnNum = Integer.parseInt(temp[temp.length - 1]);
        DateTime start = DateUtil.parse(DateUtil.now());
        List<List<Double>> distance = new ArrayList<>();
        List<Double> timeweight = new ArrayList<>();
        List<Double> row;
        for (int i = 0; i < (temp.length - 1) / columnNum - 1; i++) {
            row = new ArrayList<>();
            for (int j = 0; j < columnNum; j++) {
                row.add(NumberUtil.round(Double.parseDouble(temp[i * columnNum + j]), 2).doubleValue());
            }
            distance.add(row);
        }
        for (int i = 0; i < columnNum; i++) {
            timeweight.add(NumberUtil.round(Double.parseDouble(temp[temp.length - columnNum - 1 + i]), 2).doubleValue());
        }
        String[] distanceRecy = new String[columnNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (i == j)
                    distanceRecy[i] = distance.get(i).get(j).toString();
            }
        }
        //最终的序列结果
        List<Integer> loc = new ArrayList<>();
        for (int i = 0; i < distanceRecy.length; i++) {
            int tempIndex = -1;
            double min = 1000000000;
            for (int j = 0; j < distanceRecy.length; j++) {
                if (loc.contains(j)) continue;
                if (Double.parseDouble(distanceRecy[j]) <= min) {
                    min = Double.parseDouble(distanceRecy[j]);
                    tempIndex = j;
                }
            }
            loc.add(tempIndex);
        }
        //计算适应度
        double result = 0;

        //中间每个点之间的距离*时间权值
        for (int i = 0; i < loc.size() - 1; i++) {
            result += distance.get(loc.get(i)).get(loc.get(i + 1)) * timeweight.get(loc.get(i));
        }
        //加上首个点*时间权值(最后一个点没有权值)
        result += distance.get(loc.get(0)).get(loc.get(0)) * timeweight.get(loc.get(0))
                + distance.get(loc.get(loc.size() - 1)).get(loc.get(loc.size() - 1));

        StringBuilder route = new StringBuilder();
        for (Integer integer : loc) route.append(integer).append(",");
        route.append(result);
        DateTime stop = DateUtil.parse(DateUtil.now());
        long timeSpan = DateUtil.between(start, stop, DateUnit.MS);
        return HttpResult.success(route + "," + timeSpan + "ms");
    }

    @PostMapping("/last_data")
    public HttpResult setNodeData(@RequestBody LastNode lastNode) {
        return HttpResult.success(lastNodeService.saveLastNode(lastNode));
    }

    @PostMapping("/node_data")
    public HttpResult setNodeData(@RequestBody Node node) {
        return HttpResult.success(nodeService.saveNode(node));
    }

}
