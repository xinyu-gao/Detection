package com.node.detection.controller;

import cn.hutool.core.util.NumberUtil;
import com.node.detection.entity.mongo.LastNode;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.entity.util.PageResult;
import com.node.detection.service.LastNodeService;
import com.node.detection.service.NodeCleanDataService;
import com.node.detection.entity.util.HttpResult;
import com.node.detection.service.NodeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private NodeService nodeService;

    @ApiOperation("查询node清理数据")
    @GetMapping("/get_clean_data")
    public HttpResult getNodeCleanData(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(nodeCleanDataService.getNodeCleanDataByIMSI(IMSI));
    }

    @ApiOperation("查询node曲线数据")
    @GetMapping("/get_line_data")
    public HttpResult getLineDataForCurrentNode(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(nodeService.getLineDataByIMSI(IMSI));
    }

    @ApiOperation("查询所有node最新数据")
    @PostMapping("/get_last_data")
    public HttpResult getNodeData(@RequestBody MyPageRequest myPageRequest) {
        PageResult<LastNode> lastNodeList = lastNodeService.getAllLastNode(myPageRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("nodeNums", lastNodeList.getList().size());
        int nodeConnectCount = 0;
        for (LastNode lastNode : lastNodeList.getList()) {
            if(lastNode.getConnectStatus().equals("connecting")) {
                nodeConnectCount++;
            }
        }
        map.put("nodeConnectCount", nodeConnectCount);
        map.put("nodeConnectRate", NumberUtil.div(nodeConnectCount, lastNodeList.getList().size(), 2));
        map.put("nodeData", lastNodeList);
        return HttpResult.success(map);
    }
}
