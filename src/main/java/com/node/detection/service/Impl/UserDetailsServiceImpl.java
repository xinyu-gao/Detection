package com.node.detection.service.Impl;

import com.alibaba.fastjson.JSON;
import com.node.detection.entity.mysql.SysUser;
import com.node.detection.exception.ServiceException;
import com.node.detection.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws ServiceException {

        SysUser users = userService.findByUsername(username);

        if (users == null) {
            throw new ServiceException("username not found");
        }
        String password = users.getPassword();
        long userId = users.getId();
        List<String> userRoles = userService.findRolesByUserId(userId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : userRoles){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(grantedAuthority);
        }
        log.info("grantedAuthority:" + JSON.toJSONString(authorities));
        return User.builder()
                .username(username)
                .password(password)
                .authorities(authorities)
                .build();
    }
}