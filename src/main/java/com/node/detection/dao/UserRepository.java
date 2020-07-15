package com.node.detection.dao;

import com.node.detection.entity.mysql.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author xinyu
 */
public interface UserRepository extends JpaRepository<SysUser, Long> {
    /**
     * select * from sysUser where username = ?
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUsername(String username);
}
