package com.node.detection.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommonService {
    /**
     * 生成验证码和图片，验证码存到 session 中，将图片输出到客户端
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @throws IOException 写入图片失败
     */
    void createAndPrintKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
