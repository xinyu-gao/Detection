package com.node.detection.config.handler;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.node.detection.entity.Result;
import com.node.detection.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * 成功登录处理类
 * @author xinyu
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private Result result;
    /**
     * 设置response的data中的内容
     * @param httpServletRequest HTTP 请求
     * @param httpServletResponse HTTP 响应
     * @param authentication 认证信息
     * @throws IOException 向客户端写入数据失败
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        //返回 json，并防止中文乱码
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        result.setResult("login success");
        out.write(JSON.toJSONString(HttpResult.ok(result)));
        out.close(); //资源关闭
    }
}