package com.node.detection.dao;

import com.node.detection.entity.mongo.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * @author xinyu
 */
@Repository
public interface UserRepository extends MongoRepository<SysUser, Long> {
    /**
     * 查询用户
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUsername(String username);

    Page<SysUser> findAll(Pageable pageable);

    SysUser save(SysUser sysUser);

    void deleteByUsername(String username);

}
