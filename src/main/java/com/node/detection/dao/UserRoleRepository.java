package com.node.detection.dao;

import com.node.detection.entity.mysql.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xinyu
 */
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
    /**
     * select * from user_roles where userId = ?
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<UserRoles> findByUserId(long userId);
}
