package com.node.detection.controller;

import com.node.detection.entity.Result;
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
import java.awt.image.BufferedImage;


/**
 * @author xinyu
 */
@Controller
@Slf4j
public class KaptchaController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private Result result;

    @ApiOperation(value = "请求验证码", notes = "资源路径请求")
    @GetMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedImage bufferedImage = commonService.createKaptchaAndSaveToSession(request, response);
        KaptchaUtil.writeImageToClient(response, bufferedImage);
    }

    @GetMapping("/checkVerifyCode")
    @ResponseBody
    public HttpResult checkVerifyCode(HttpServletRequest request) {
        log.info(""+KaptchaUtil.checkVerifyCode(request));
        result.setResult(KaptchaUtil.checkVerifyCode(request));
        return HttpResult.success(result);
    }

}