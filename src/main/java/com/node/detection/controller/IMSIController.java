package com.node.detection.controller;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.node.detection.entity.util.IMSIInfo;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.service.LastNodeService;
import com.node.detection.entity.util.HttpResult;
import com.node.detection.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imsi")
public class IMSIController {

    @Autowired
    private LastNodeService lastNodeService;

    @ApiOperation(value = "请求存活的 imsi 列表")
    @GetMapping
    public HttpResult getIMSISet(@RequestParam("page") int page , @RequestParam("size") int size) {
        List<IMSIInfo> iMSIInfoList = lastNodeService.getLastNode(page, size)
                .getList()
                .parallelStream()
                .map(lastNode -> new IMSIInfo(
                        lastNode.getIMSI(),
                        lastNode.getCurrentTime(),
                        CommonUtil.getTimeDiffFromNow(lastNode.getCurrentTime())
                        ))
                .collect(Collectors.toList());
        return HttpResult.success(iMSIInfoList);
    }

    @ApiOperation(value = "删除不可用的 imsi")
    @DeleteMapping("/")
    public HttpResult deleteUnusedIMSI(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(null != lastNodeService.deleteIMSIFromLastNodeList(IMSI));
    }
}
