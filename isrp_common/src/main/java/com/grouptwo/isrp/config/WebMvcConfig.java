package com.grouptwo.isrp.config;

import com.grouptwo.isrp.config.interceptor.RolesAuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @program: isrp
 * @description: 配置mvc
 * @author: Wilburn
 * @create: 2022-06-25 23:12
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private RolesAuthorizationInterceptor rolesAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rolesAuthorizationInterceptor);
    }
}
