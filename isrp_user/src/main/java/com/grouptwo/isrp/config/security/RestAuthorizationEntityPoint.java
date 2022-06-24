package com.grouptwo.isrp.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: wilburn
 * @description: 当为登录或者Token失效访问接口时自定义访问接口
 */
@Component
public class RestAuthorizationEntityPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        out.write("权限不足,请登录");
        out.flush();
        out.close();
    }
}
