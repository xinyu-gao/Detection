package com.node.detection.config;


import com.node.detection.config.handler.MyAuthentiationFailureHandler;
import com.node.detection.config.handler.MyAuthenticationEntryPoint;
import com.node.detection.config.handler.MyAuthenticationSuccessHandler;
import com.node.detection.config.handler.MyLogoutSuccessHandler;
import com.node.detection.exception.JWTAuthenticationEntryPoint;
import com.node.detection.filter.CustomAuthenticationFilter;
import com.node.detection.filter.JwtAuthenticationFilter;
import com.node.detection.filter.JwtAuthorizationFilter;
import com.node.detection.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * WebSecurityConfigurerAdapter 提供了一种便利的方式去创建 WebSecurityConfigurer 的实例，
 * 只需要重写 WebSecurityConfigurerAdapter 的方法，
 * 即可配置拦截 URL、设置权限等安全控制。
 *
 * @author xinyu
 * '@EnableWebSecurity' 启用 Spring Security 的 Web 安全支持，并提供 Spring MVC 集成
 * '@EnableGlobalMethodSecurity(prePostEnabled = true)' // 实现方法级别的权限控制
 * 在控制器上加 '@PreAuthorize("hasRole('ADMIN')")'， 只有拥有此角色的用户才能调用此接口
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthentiationFailureHandler myAuthentiationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 用来配置拦截保护的请求
     * 比如哪些请求放行，哪些请求需要验证
     *
     * @param http http安全请求对象
     * @throws Exception 认证请求出错
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf 防御机制
                .csrf().disable()
                .authorizeRequests()

                // 允许对于无授权访问的 URL
//                .antMatchers(
//                        "/swagger-ui.html", "/swagger/*",
//                        "/ws", "/", "/user/login_page", "/swagger-ui.html", "/mongodb",
//                        "/kaptcha", "/hello", "/login", "/login?error", "/user/save",
//                        "/node/find", "/user/find_role", "/checkVerifyCode", "/druid/*"
//                ).permitAll()

                // 测试时全部运行访问
                .antMatchers("/**","/node/save")
                .permitAll()

                // 添加 JWT 登录拦截器、鉴权拦截器
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))

                /*
                设置 Session 的创建策略为：
                    Spring Security 永不创建 HttpSession
                    不使用 HttpSession 来获取S ecurityContext
                 */
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 用户访问没有权限的接口，不使用重定向，直接返回JSON提示。
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new JWTAuthenticationEntryPoint());
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        // 忽略URL
        web.ignoring()
                .antMatchers(
                        "/checkVerifyCode",
                        "/",
                        "/woo/user/register",
                        "/v2/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/doc.html",
                        "/druid/**"
                );
    }

    @Bean

    public PasswordEncoder passwordEncoderBean() {

        return new BCryptPasswordEncoder();

    }

    /**
     * 配置用户签名服务，主要是 user-details 机制
     * 还可以赋予用户具体权限控制
     *
     * @param auth 签名管理器构造器，用于构建用户具体权限控制
     * @throws Exception 验证出错
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //加入数据库验证类，际上在验证链中加入了一个 DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoderBean());
    }

//    /**
//     * 注册自定义的 UsernamePasswordAuthenticationFilter
//     */
//    @Bean
//    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
//        filter.setAuthenticationFailureHandler(myAuthentiationFailureHandler);
//        //重用WebSecurityConfigurerAdapter配置的AuthenticationManager
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }
}