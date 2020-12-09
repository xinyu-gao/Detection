package com.node.detection.service.impl;

import com.alibaba.fastjson.JSON;
import com.node.detection.entity.mongo.SysUser;
import com.node.detection.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录时查询 user 信息服务
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("username:{}", username);
        SysUser users = userService.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("username not found");
        }

        String password = users.getPassword();
        log.info("password"+password);
        List<String> userRoles = userService.findRolesByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : userRoles) authorities.add(new SimpleGrantedAuthority(role));

        log.info("grantedAuthority:" + JSON.toJSONString(authorities));
        return User.builder()
                .username(username)
                .password(password)
                .authorities(authorities)
                .build();
    }
}