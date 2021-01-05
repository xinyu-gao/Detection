package com.node.detection.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.node.detection.entity.mongo.SysUser;
import com.node.detection.entity.util.HttpResult;
import com.node.detection.util.JwtTokenUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证用户名密码正确后 生成一个token并将token返回给客户端
 *
 * @author xinyu
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 验证操作 接收并解析用户凭证
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JSONObject jsonObject = JSONUtil.parseObj(getBodyFromRequest(request));
        SysUser sysUser = JSONUtil.toBean(jsonObject, SysUser.class);
        log.info(sysUser.getUsername()+sysUser.getPassword());
        // 从输入流中获取到登录的信息
        // 创建一个 token 并调用 authenticationManager.authenticate() 让 Spring Security 进行验证
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword()));
    }

    /**
     * 验证【成功】后调用的方法
     * 若验证成功 生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        User user = (User) authResult.getPrincipal();
        // 从 user 中获取权限信息
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 创建Token
        String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
        // 设置编码 防止乱码问题
        response.setContentType("application/json; charset=utf-8");
        Map<String,String> map = new HashMap<>(1);
        map.put("token",token);
        response.getWriter().write(JSON.toJSONString(HttpResult.success(map)));
    }

    /**
     * 验证【失败】调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException{
        String returnData;
        log.info("登陆失败");
        // 账号过期
        if (failed instanceof AccountExpiredException) {
            returnData = "账号过期";
        }
        // 密码错误
        else if (failed instanceof BadCredentialsException) {
            returnData = "密码错误";
        }
        // 密码过期
        else if (failed instanceof CredentialsExpiredException) {
            returnData = "密码过期";
        }
        // 账号不可用
        else if (failed instanceof DisabledException) {
            returnData = "账号不可用";
        }
        //账号锁定
        else if (failed instanceof LockedException) {
            returnData = "账号锁定";
        }
        // 用户不存在
        else if (failed instanceof InternalAuthenticationServiceException) {
            returnData = "用户不存在";
        }
        // 其他错误
        else {
            returnData = "未知异常";
        }

        // 处理编码方式 防止中文乱码
        response.setContentType("application/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(HttpResult.failed(returnData)));
    }

    private String getBodyFromRequest(HttpServletRequest request) throws IOException {
        BufferedReader br;
        StringBuilder sb = new StringBuilder("");
        br = request.getReader();
        String str;
        while ((str = br.readLine()) != null)
        {
            sb.append(str);
        }
        br.close();
        return sb.toString();
    }
}