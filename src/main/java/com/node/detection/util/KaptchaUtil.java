package com.node.detection.util;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
public class KaptchaUtil {

    @Autowired
    private static Producer kaptchaProducer;

    /**
     * 将获取到的前端参数转为 string 类型
     * 去除两边的空格
     *
     * @param request http请求
     * @param key     参数的键名
     * @return string 类型的值
     */
    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if ("".equals(result)) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证码校验
     *
     * @param request http 请求
     * @return 验证码是否正确
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //获取用户输入的验证码
        String verifyCodeActual = KaptchaUtil.getString(request, "verifyCodeActual");
        return verifyCodeActual != null && verifyCodeActual.equalsIgnoreCase(verifyCodeExpected);
    }


    /**
     * 向客户端写出验证码图片
     *
     * @param response      HTTP 响应
     * @param bufferedImage 验证码图片
     * @throws IOException 向客户端输出异常
     */
    public static void writeImageToClient(HttpServletResponse response, BufferedImage bufferedImage) throws IOException {

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 设置响应头、响应体
     *
     * @param response 响应
     * @return 设置后的响应
     */
    public static HttpServletResponse setOptions(HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        return response;
    }
}
