package com.node.detection.controller;

import com.node.detection.entity.mongo.LastNode;
import com.node.detection.service.LastNodeService;
import com.node.detection.service.NodeCleanDataService;
import com.node.detection.entity.util.HttpResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xinyu
 */
@Slf4j
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeCleanDataService nodeCleanDataService;

    @Autowired
    private LastNodeService lastNodeService;

    @ApiOperation("查询node清理数据")
    @GetMapping("/get_clean_data")
    public HttpResult getNodeCleanData(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(nodeCleanDataService.getNodeCleanDataByIMSI(IMSI));
    }

    @ApiOperation("查询所有node最新数据")
    @GetMapping("/get_last_data")
    public HttpResult getNodeData() {
        List<LastNode> lastNodeList = lastNodeService.getAllLastNode();
        Map<String, Object> map = new HashMap<>();
        map.put("nodeNums", lastNodeList.size());
        int nodeConnectCount = 0;
        for (LastNode lastNode : lastNodeList) {
            if(lastNode.getConnectStatus().equals("connecting")) {
                nodeConnectCount++;
            }
        }
        map.put("nodeConnectRate", nodeConnectCount);
        map.put("nodeData", lastNodeList);
        return HttpResult.success(map);
    }
}
