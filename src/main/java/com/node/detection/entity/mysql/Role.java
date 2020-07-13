package com.node.detection.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 角色更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 角色创建人
     */
    @Column(name = "creat_by")
    private String createBy;

    /**
     * 角色更新人
     */
    @Column(name = "last_update_by")
    private String lastUpdateBy;

}
