package com.node.detection.entity.mysql;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author xinyu
 */
@Entity
@Table(name = "sys_user")
@Data
@Component
@Slf4j
public class SysUser implements Serializable, UserDetails {

    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue()
    private Long id;
    /**
     * 用户名
     */
    @NotNull(message = "username is null")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "password is null")
    private String password;



    /**
     * 用户的手机号码
     */
    private String phoneNumber;

    /**
     * 用户的电子邮件地址
     */
    @Column(name = "email")
    private String email;
    /**
     * 账户是否没有过期
     */
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;
    /**
     * 账户是否没有被锁定
     */
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    /**
     * 密码是否没有过期
     */
    @Column(name = "credentials_non_expired")
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
