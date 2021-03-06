package com.node.detection.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xinyu
 */
public class EncoderUtil {
    /**
     * 对用户的密码进行加密
     * @return 加密方式
     */
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
