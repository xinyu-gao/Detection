package com.node.detection.service;

import com.node.detection.entity.mongo.SysUser;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.entity.util.PageResult;

import java.util.List;

/**
 * @author xinyu
 */
public interface UserService {
    /**
     * 通过用户名查询该用户的相关信息
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUsername(String username);

    /**
     * 存储用户
     * @param sysUser 用户信息
     * @throws Exception 存储失败，例如用户名重名
     */
    void saveUser(SysUser sysUser) throws Exception;

    /**
     * 通过 用户名 查询该用户的角色
     * @param username 用户名
     * @return 角色列表
     */
    List<String> findRolesByUserName(String username);

    /**
     * 查询所有用户的角色
     * @param myPageRequest 分页
     * @return 分页查询数据
     */
    PageResult findAllUsers(MyPageRequest myPageRequest);
}
