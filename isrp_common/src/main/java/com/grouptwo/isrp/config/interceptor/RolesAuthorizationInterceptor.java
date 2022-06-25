package com.grouptwo.isrp.config.interceptor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.annotation.RolesAuthorization;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @program: isrp
 * @description: 用户鉴权拦截器
 * @author: Wilburn
 * @create: 2022-06-25 22:41
 **/
@Component
public class RolesAuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // 反射是注解生效
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RolesAuthorization rolesAuthorization = method.getAnnotation(RolesAuthorization.class);
            List<String> whiteNames = Arrays.asList(rolesAuthorization.value());
            JSONObject user = JSONObject.parseObject(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
            int role = Integer.parseInt(user.get("role").toString());
            String userRole;
            switch (role){
                case 0 : userRole = "user"; break;
                case 1 : userRole = "business"; break;
                case 2 : userRole = "manager"; break;
                default:
                    throw new IllegalStateException("Unexpected value: " + role);
            }
            if (!whiteNames.contains(userRole)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setStatus(403);
                PrintWriter out = response.getWriter();
                out.write("权限不足,禁止访问");
                out.flush();
                out.close();
                return false;
            }
        }
        return true;
    }
}
