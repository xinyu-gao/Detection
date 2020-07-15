package com.node.detection.service.impl;

import com.alibaba.fastjson.JSON;
import com.node.detection.dao.RoleRepository;
import com.node.detection.dao.UserRepository;
import com.node.detection.dao.UserRoleRepository;
import com.node.detection.entity.mysql.Role;
import com.node.detection.entity.mysql.SysUser;
import com.node.detection.entity.mysql.UserRoles;
import com.node.detection.service.UserService;
import com.node.detection.util.EncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xinyu
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public SysUser findByUsername(String username) {
        log.info(JSON.toJSONString(userRepository.findByUsername(username)));
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(SysUser sysUser) throws Exception {
        SysUser user = findByUsername(sysUser.getUsername());
        if(user == null){
            //加密密码
            String passwordAfterEncode = EncoderUtil.passwordEncoder().encode(sysUser.getPassword());
            sysUser.setPassword(passwordAfterEncode);
            userRepository.saveAndFlush(sysUser);
        }else {
            throw new Exception("username is already exist");
        }
    }

    @Override
    public List<String> findRolesByUserId(Long userId) {
        log.info(""+userId);
        List<UserRoles> userRoles = userRoleRepository.findByUserId(userId);

        List<String> roles = new ArrayList<>();
        for(UserRoles userRole : userRoles){
            Optional<Role> role = roleRepository.findById(userRole.getRoleId());
            // 查询到了角色名后添加到数组中
            roles.add(role.map(Role::getName).orElse(null));
        }
        log.info("roles:" + roles);
        return roles;
    }
}
