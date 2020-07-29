package com.node.detection.controller;

import com.node.detection.entity.mysql.SysUser;
import com.node.detection.service.UserService;
import com.node.detection.util.HttpResult;
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

    @PostMapping("/save")
    public HttpResult saveUser(@Validated @RequestBody SysUser sysUser) throws Exception {
        userService.saveUser(sysUser);
        return HttpResult.ok(true);
    }
    @PostMapping("/find")
    public HttpResult findUser(@RequestBody SysUser sysUser){
        SysUser result = userService.findByUsername(sysUser.getUsername());
        return HttpResult.ok(result);
    }
    @PostMapping("/find_role")
    public HttpResult findRole(@RequestBody SysUser sysUser){
        log.info(sysUser.getUsername());
        Long id = userService.findByUsername(sysUser.getUsername()).getId();
        List<String> result = userService.findRolesByUserId(id);
        return HttpResult.ok(result);
    }
    @PostMapping("/logins")
    @ResponseBody
    public SysUser getKaptchaImages(SysUser sysUser) throws Exception {
        return sysUser;

    }
}
