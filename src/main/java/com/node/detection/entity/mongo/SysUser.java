package com.node.detection.entity.mongo;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author xinyu
 */
@Data
@Document
@Slf4j
public class SysUser implements Serializable, UserDetails {

    /**
     * 用户名
     */
    @Field(name = "username")
    private String username;

    /**
     * 用户密码
     */
    @Field(name = "password")
    private String password;

    /**
     * 创建时间
     */
    @Field(name = "create_time")
    private String creatTime;

    /**
     * 创建者
     */
    @Field(name = "create_user")
    private String createUser;

    /**
     * 角色
     */
    @Field(name = "roles")
    private List<String> roles;

    /**
     * 账户是否没有过期
     */
    @Field(name = "account_non_expired")
    private boolean accountNonExpired;

    /**
     * 账户是否没有被锁定
     */
    @Field(name = "account_non_locked")
    private boolean accountNonLocked;

    /**
     * 密码是否没有过期
     */
    @Field(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    /**
     * 账户是否可用
     */
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }


    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
