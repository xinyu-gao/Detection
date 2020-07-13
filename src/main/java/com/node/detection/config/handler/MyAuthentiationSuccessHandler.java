package com.node.detection.config.handler;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.node.detection.util.HttpResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * 成功登录处理类
 */
@Component("myAuthenctiationSuccessHandler")
public class MyAuthentiationSuccessHandler implements AuthenticationSuccessHandler {

   // 设置response的data中的内容
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8"); //防止中文乱码
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSON.toJSONString(HttpResult.ok("login success")));
        out.close(); //资源关闭
    }
}