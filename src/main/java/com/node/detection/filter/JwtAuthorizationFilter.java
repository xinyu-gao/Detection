package com.node.detection.filter;

import com.alibaba.fastjson.JSON;
import com.node.detection.exception.SendException;
import com.node.detection.entity.util.HttpResult;
import com.node.detection.util.JwtTokenUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 登录成功后 走此类进行鉴权操作
 *
 * @author xinyu
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在过滤之前和之后执行的事件
     */
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        // header 为 null 返回错误
        if (null == tokenHeader) {
            SendException.send(JSON.toJSONString(HttpResult.unauthorized("need authority")), response);
            return;
        }
        UsernamePasswordAuthenticationToken token = getAuthentication(tokenHeader, response);
        if (null == token) {
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(token);
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @param tokenHeader 字符串形式的Token请求头
     * @return 带用户名和密码以及权限的Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader, HttpServletResponse response) throws IOException {
        // 去掉前缀 获取Token字符串
        String token = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        try {
            JwtTokenUtil.checkJwt(token);
        } catch (Exception e) {
            SendException.send(JSON.toJSONString(HttpResult.unauthorized("token 格式不正确")), response);
            return null;
        }
        if (JwtTokenUtil.isExpiration(token)) {
            SendException.send(JSON.toJSONString(HttpResult.unauthorized("token 过期")), response);
            return null;
        }
        // 从Token中解密获取用户名
        String username = JwtTokenUtil.getUsername(token);
        // 从Token中解密获取用户角色
        String role = JwtTokenUtil.getUserRole(token);


        // 将[ROLE_XXX,ROLE_YYY]格式的角色字符串转换为数组
        String[] roles = StringUtils.strip(role, "[]").split(", ");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String s : roles) {
            if ("".equals(s)) {
                continue;
            }
            authorities.add(new SimpleGrantedAuthority(s));
        }
        return null == username ? null : new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}