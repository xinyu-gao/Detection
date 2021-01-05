package com.node.detection.controller;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.node.detection.entity.util.IMSIInfo;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.service.LastNodeService;
import com.node.detection.entity.util.HttpResult;
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
    @PostMapping("/get_set")
    public HttpResult getIMSISet(@RequestBody MyPageRequest myPageRequest) {
        List<IMSIInfo> iMSIInfoList = lastNodeService.getAllLastNode(myPageRequest)
                .getList()
                .parallelStream()
                .map(lastNode -> new IMSIInfo(
                        lastNode.getIMSI(),
                        lastNode.getCurrentTime(),
                        DateUtil.formatBetween(
                                DateUtil.between(
                                        DateUtil.parse(DateUtil.now()),
                                        DateUtil.parse(lastNode.getCurrentTime()),
                                        DateUnit.DAY),
                                BetweenFormatter.Level.MINUTE)))
                .collect(Collectors.toList());
        return HttpResult.success(iMSIInfoList);
    }

    @ApiOperation(value = "删除不可用的 imsi")
    @GetMapping("/delete")
    public HttpResult getIMSISet(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(null != lastNodeService.deleteIMSIFromLastNodeList(IMSI));
    }
}
