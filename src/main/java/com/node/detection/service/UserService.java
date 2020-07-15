package com.node.detection.service;

import com.node.detection.entity.mysql.Role;
import com.node.detection.entity.mysql.SysUser;

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
    public SysUser findByUsername(String username);

    /**
     * 存储用户
     * @param sysUser 用户信息
     * @throws Exception 存储失败，例如用户名重名
     */
    public void saveUser(SysUser sysUser) throws Exception;

    /**
     * 通过用户 id 查询该用户的角色
     * @param userId 用户 id
     * @return 角色列表
     */
    public List<String> findRolesByUserId(Long userId);
}
