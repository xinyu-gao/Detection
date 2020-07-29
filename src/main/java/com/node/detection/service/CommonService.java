package com.node.detection.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author xinyu
 */
public interface CommonService {
    /**
     * 生成验证码文本和图片，验证码文本存到 session 中，
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @return  验证码图片
     * @throws IOException 写入图片失败
     */
    BufferedImage createKaptchaAndSaveToSession(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
