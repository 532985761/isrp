package com.grouptwo.isrp.config.security;

import com.alibaba.fastjson2.JSON;
import com.grouptwo.isrp.service.IsrpUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author: wilburn
 * @description: Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IsrpUserService isrpUserService;

    @Resource
    private RestAuthorizationEntityPoint restAuthorizationEntityPoint;

    @Resource
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthorizationEntityPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                // 登录
                "/isrpUser/userLogin",
                "/isrpUser/businessLogin",
                "/isrpUser/managerLogin",
                // 注册
                "/isrpUser/register",
                // 验证码
                "/captcha",
                // 授权认证
                "/isrpAuth/authentication",
                "/isrpUser/emailInner/**",
                //用户账号激活
                "/activation/**"
        );
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            SysUserDetails user = JSON.parseObject(JSON.toJSONString(isrpUserService.queryByEmail(username)), SysUserDetails.class);
            if(null != user) {
                return user;
            }
            return null;
        };
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
