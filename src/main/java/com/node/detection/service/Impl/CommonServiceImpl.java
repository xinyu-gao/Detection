package com.node.detection.service.Impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.node.detection.service.CommonService;
import com.node.detection.util.KaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private Producer kaptchaProducer;
    @Override
    public void createAndPrintKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String kaptchaProducerText = kaptchaProducer.createText(); //验证码字符
        BufferedImage bufferedImage = kaptchaProducer.createImage(kaptchaProducerText); //验证码图片
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, kaptchaProducerText); // 保存在 session 中
        KaptchaUtil.setOptions(response);
        KaptchaUtil.writeImageToClient(response, bufferedImage);
    }
}
