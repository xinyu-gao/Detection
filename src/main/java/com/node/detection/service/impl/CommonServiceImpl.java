package com.node.detection.service.impl;

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
    public BufferedImage createKaptchaAndSaveToSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //验证码字符
        String kaptchaProducerText = kaptchaProducer.createText();
        //验证码图片
        BufferedImage bufferedImage = kaptchaProducer.createImage(kaptchaProducerText);

        HttpSession session = request.getSession();
        // 保存在 session 中
        KaptchaUtil.setOptions(response);
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, kaptchaProducerText);
        return bufferedImage;
    }
}
