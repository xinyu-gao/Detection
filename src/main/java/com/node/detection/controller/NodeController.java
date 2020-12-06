package com.node.detection.controller;

import com.node.detection.dao.WsNodeRepository;
import com.node.detection.entity.ws.WsNode;
import com.node.detection.service.IMSIService;
import com.node.detection.service.NodeCleanDataService;
import com.node.detection.service.NodeService;
import com.node.detection.util.HttpResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xinyu
 */
@Slf4j
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodeCleanDataService nodeCleanDataService;

    @Autowired
    private WsNodeRepository wsNodeRepository;

    @ApiOperation("新增node信息")
    @PostMapping("/save")
    public HttpResult insertNode(@RequestBody WsNode node) {
        return HttpResult.success(wsNodeRepository.save(node));
    }

    @ApiOperation("查询node清理数据")
    @GetMapping("/get_clean_data")
    public HttpResult getNodeCleanData(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(nodeCleanDataService.getNodeCleanDataByIMSI(IMSI));
    }
}
