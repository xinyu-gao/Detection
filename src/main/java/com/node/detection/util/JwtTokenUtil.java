package com.node.detection.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xinyu
 */
@Data
public class JwtTokenUtil {

    /**
     * Token请求头
      */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Token前缀
      */
    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * 签名主题
      */
    private static final String SUBJECT = "detection";

    /**
     * 过期时间
     */
    private static final long EXPIRATION = 1000 * 24 * 60 * 60 * 7;

    /**
     * 应用密钥
     */
    private static final String APPSECRET_KEY = "detection";

    /**
     * 角色权限声明
     */
    private static final String ROLE_CLAIMS = "role";

    /**
     * 生成Token
     */
    public static String createToken(String username,String role) {
        Map<String,Object> map = new HashMap<>(1);
        map.put(ROLE_CLAIMS, role);
        return Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username",username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
    }

    /**
     * 校验Token
     */
    public static void checkJwt(String token) {
        Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 从Token中获取用户角色
     */
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}
