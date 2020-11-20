package com.node.detection.config.handler;

import com.alibaba.fastjson.JSON;
import com.node.detection.util.HttpResult;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xinyu
 */
@Component("myAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        if (e instanceof InsufficientAuthenticationException) {
            out.write(JSON.toJSONString(HttpResult.unauthorized("need authority")));
        } else {
            out.write(JSON.toJSONString(HttpResult.failed("unknown error")));
        }
        out.close();
    }
}