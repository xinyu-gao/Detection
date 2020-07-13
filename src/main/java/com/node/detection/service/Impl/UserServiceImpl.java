package com.node.detection.service.Impl;

import com.alibaba.fastjson.JSON;
import com.node.detection.dao.RoleRepository;
import com.node.detection.dao.UserRepository;
import com.node.detection.dao.UserRoleRepository;
import com.node.detection.entity.mysql.Role;
import com.node.detection.entity.mysql.SysUser;
import com.node.detection.entity.mysql.UserRoles;
import com.node.detection.service.UserService;
import com.node.detection.util.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;
    public SysUser findByUsername(String username) {
        log.info(JSON.toJSONString(userRepository.findByUsername(username)));
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(SysUser sysUser) throws Exception {
        SysUser user = findByUsername(sysUser.getUsername());
        if(user == null){
            String passwordAfterEncode = Encoder.passwordEncoder().encode(sysUser.getPassword()); //加密密码
            sysUser.setPassword(passwordAfterEncode);
            userRepository.saveAndFlush(sysUser); // 存储数据
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
            roles.add(role.map(Role::getName).orElse(null)); // 查询到了角色名后添加到数组中
        }
        log.info("roles:" + roles);
        return roles;
    }
}
