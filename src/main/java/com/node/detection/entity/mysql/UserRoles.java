package com.node.detection.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_roles")
@Data
public class UserRoles {
    @Id
    private Long id;
    /**
     * 用户 id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 角色 id
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 创建人
     */
    @Column(name = "creat_by")
    private String createBy;

    /**
     * 更新人
     */
    @Column(name = "last_update_by")
    private String lastUpdateBy;
}
