package com.node.detection.dao;

import com.node.detection.entity.mysql.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
    List<UserRoles> findByUserId(long userId);
}
