package com.grouptwo.isrp.config;

import com.grouptwo.isrp.config.interceptor.RolesAuthorizationInterceptor;
import com.grouptwo.isrp.utils.GetFilePath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    @Value("${spring.application.name}")
    private String applicationName;

    private String realPath = new GetFilePath().getClassPath();

    public WebMvcConfig() throws Exception {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + (applicationName.replaceAll("-", "_") + "/src/main/resources/static/").replaceAll("/", "\\\\\\\\"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rolesAuthorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
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
                        // 静态资源
                        "/templates/**",
                        // 用户账号激活
                        "/activation/**",
                        // 文件
                        "/file/**"
                        );

    }
}
