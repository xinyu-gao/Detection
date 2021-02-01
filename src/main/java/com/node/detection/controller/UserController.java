package com.node.detection.controller;

import com.node.detection.dao.UserRepository;
import com.node.detection.entity.mongo.SysUser;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.service.UserService;
import com.node.detection.entity.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xinyu
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public HttpResult saveUser(@Validated @RequestBody SysUser sysUser) throws Exception {
        userService.saveUser(sysUser);
        return HttpResult.success(true);
    }

    @PostMapping("/password")
    public HttpResult updatePassword(@Validated @RequestBody SysUser sysUser) throws Exception {
        SysUser user = userService.findByUsername(sysUser.getUsername());
        user.setPassword(sysUser.getPassword());
        userService.saveUser(user);
        return HttpResult.success(true);
    }

    @PostMapping("/find")
    public HttpResult findUser(@RequestBody SysUser sysUser) {
        SysUser result = userService.findByUsername(sysUser.getUsername());
        return HttpResult.success(result);
    }

    @DeleteMapping()
    public HttpResult deleteUser(@RequestBody SysUser sysUser) {
        userRepository.deleteByUsername(sysUser.getUsername());
        return HttpResult.success("true");
    }
    @PostMapping("/findAll")
    public HttpResult findAllUsers(@RequestBody MyPageRequest myPageRequest) {
        return HttpResult.success(userService.findAllUsers(myPageRequest));
    }

    @PostMapping("/find_role")
    public HttpResult findRole(@RequestBody SysUser sysUser) {
        List<String> result = userService.findRolesByUserName(sysUser.getUsername());
        return HttpResult.success(result);
    }

}
