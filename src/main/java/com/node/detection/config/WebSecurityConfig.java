package com.node.detection.config;


import com.node.detection.config.handler.MyAuthentiationFailureHandler;
import com.node.detection.config.handler.MyAuthentiationSuccessHandler;
import com.node.detection.config.handler.MyAuthenticationEntryPoint;
import com.node.detection.config.handler.MyLogoutSuccessHandler;
import com.node.detection.filter.CustomAuthenticationFilter;
import com.node.detection.service.Impl.UserDetailsServiceImpl;
import com.node.detection.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter 提供了一种便利的方式去创建 WebSecurityConfigurer 的实例，
 * 只需要重写 WebSecurityConfigurerAdapter 的方法，
 * 即可配置拦截 URL、设置权限等安全控制。
 */

@Configuration
@EnableWebSecurity // 启用 Spring Security 的 Web 安全支持，并提供 Spring MVC 集成
@EnableGlobalMethodSecurity(prePostEnabled = true)// 实现方法级别的权限控制
// @PreAuthorize("hasRole('ADMIN')") //只有拥有此角色的用户才能调用此接口
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthentiationFailureHandler myAuthentiationFailureHandler;
    @Autowired
    private MyAuthentiationSuccessHandler myAuthentiationSuccessHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
                .authorizeRequests()
                .antMatchers(
                        "/ws", "/", "/user/login_page", "/swagger-ui.html", "/mongodb",
                        "/kaptcha", "/hello", "/login", "/login?error", "/user/save",
                        "/node/find", "/user/find_role"
                )
                .permitAll()
                .anyRequest().authenticated()  // 其他请求,登录后可以访问

                //登录地址、方法
                .and().formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .and().logout() // 配置登出账号
                .logoutUrl("/logout") // 登出请求路径
                .logoutSuccessHandler(myLogoutSuccessHandler) //成功处理器，返回 JSON
                .permitAll() // 登出
                .and().rememberMe()// remember me功能
                .tokenValiditySeconds(86400 * 7)//有效时间
                .key("remember-me-key")//保存的cookie键名

                .and()
                // 关闭 crsf 防御机制
                .csrf().disable()
                // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling() // 用户访问没有权限的接口，不使用重定向，直接返回JSON提示。
                .authenticationEntryPoint(myAuthenticationEntryPoint)
        ;


        http.addFilterAt(customAuthenticationFilter(), CustomAuthenticationFilter.class);
    }

    public void configure(WebSecurity web){
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
                        "/doc.html"
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
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //加入数据库验证类，际上在验证链中加入了一个 DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthentiationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthentiationFailureHandler);
        //重用WebSecurityConfigurerAdapter配置的AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}