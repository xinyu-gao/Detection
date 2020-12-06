package com.node.detection.controller;

import com.node.detection.service.IMSIService;
import com.node.detection.util.HttpResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imsi")
public class IMSIController {

    @Autowired
    private IMSIService imsiService;

    @ApiOperation(value = "请求存活的 imsi 列表")
    @GetMapping("/get_set")
    public HttpResult getIMSISet() {
        return HttpResult.success(imsiService.getIMSIlist());
    }

    @ApiOperation(value = "删除不可用的 imsi")
    @GetMapping("/delete")
    public HttpResult getIMSISet(@RequestParam("imsi") String IMSI) {
        return HttpResult.success(imsiService.deleteIMSI(IMSI));
    }
}
