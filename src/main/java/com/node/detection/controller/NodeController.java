package com.node.detection.controller;

import com.node.detection.entity.mysql.Node;
import com.node.detection.service.NodeService;
import com.node.detection.util.HttpResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinyu
 */
@Slf4j
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @ApiOperation("查询node信息")
    @PostMapping("/find")
    public HttpResult findNode(@RequestBody Node node){
        return HttpResult.success(nodeService.findByType(node.getType(),0,2));
    }
}
