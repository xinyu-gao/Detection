package com.node.detection.dao;

import com.node.detection.entity.mysql.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xinyu
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
