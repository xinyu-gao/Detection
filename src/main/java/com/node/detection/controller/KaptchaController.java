package com.node.detection.controller;

import com.node.detection.service.CommonService;
import com.node.detection.util.HttpResult;
import com.node.detection.util.KaptchaUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@Slf4j
public class KaptchaController {
    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "请求验证码", notes = "资源路径请求")
    @GetMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        commonService.createAndPrintKaptcha(request, response);
    }

    @GetMapping("/checkVerifyCode")
    @ResponseBody
    public HttpResult checkVerifyCode(HttpServletRequest request) {
        return HttpResult.ok(KaptchaUtil.checkVerifyCode(request));
    }


}