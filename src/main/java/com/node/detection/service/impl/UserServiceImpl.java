package com.node.detection.service.impl;

import cn.hutool.core.date.DateUtil;
import com.node.detection.dao.UserRepository;
import com.node.detection.entity.mongo.SysUser;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.service.UserService;
import com.node.detection.entity.util.PageResult;
import com.node.detection.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(SysUser sysUser) throws Exception {
        SysUser user = findByUsername(sysUser.getUsername());
        if(user == null){
            sysUser.setCreatTime(DateUtil.today());
            // 加密
            String passwordAfterEncode = new BCryptPasswordEncoder().encode(sysUser.getPassword());
            sysUser.setPassword(passwordAfterEncode);
            userRepository.save(sysUser);
        }else {
            throw new Exception("username is already exist");
        }
    }

    @Override
    public List<String> findRolesByUserName(String username) {
        return  userRepository.findByUsername(username).getRoles();
    }

    @Override
    public PageResult findAllUsers(MyPageRequest myPageRequest) {
        Page<SysUser> sysUsers = userRepository.findAll(PageRequest.of(myPageRequest.getPage(), myPageRequest.getSize()));
        return PageUtil.setResult(sysUsers, myPageRequest.getPage());
    }
}
