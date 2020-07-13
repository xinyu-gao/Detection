package com.node.detection.dao;

import com.node.detection.entity.mysql.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
