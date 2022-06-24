package com.grouptwo.isrp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.grouptwo.isrp.security.JwtTokenUtil;
import com.grouptwo.isrp.security.SysUserDetails;
import com.grouptwo.isrp.pojo.LoginFormPojo;
import com.grouptwo.isrp.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: isrp
 * @description: 授权服务实现类
 * @author: Wilburn
 * @create: 2022-06-23 08:25
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 授权
     * @param loginForm
     * @return
     */

    @Override
    public Map authentication(LoginFormPojo loginForm) {
        String code = loginForm.getCode();
        String text = stringRedisTemplate.opsForValue().get("captcha:" + loginForm.getIp() + ":");
        Map<String, Object> errorMap = new HashMap<>();
        if(text == null) {
            errorMap.put("status", 400);
            errorMap.put("message", "验证码已过期");
            return errorMap;
        }
        if(StringUtils.isEmpty(code) || !text.equalsIgnoreCase(code)){
            errorMap.put("status", 400);
            errorMap.put("message", "验证码输入错误");
            return errorMap;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getEmail());
        SysUserDetails sysUserDetails = new SysUserDetails();
        BeanUtil.copyProperties(userDetails, sysUserDetails);
        if(userDetails == null) {
            errorMap.put("status", 400);
            errorMap.put("message", "暂无此用户");
            return errorMap;
        }
        if(!passwordEncoder.matches(loginForm.getPassword(), userDetails.getPassword())) {
            errorMap.put("status", 400);
            errorMap.put("message", "密码输入错误");
            return errorMap;
        }
        if(loginForm.getRole() != sysUserDetails.getRole()) {
            errorMap.put("status", 401);
            errorMap.put("message", "暂无权限");
            return errorMap;
        }
        if(!userDetails.isEnabled()) {
            errorMap.put("status", 401);
            errorMap.put("message", "账号被禁用, 请联系管理员");
            return errorMap;
        }
        sysUserDetails.setPassword(null);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("status", 200);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("token", token);
        dataMap.put("tokenHeader", tokenHead);
        dataMap.put("info", sysUserDetails);
        tokenMap.put("data", dataMap);
        return tokenMap;
    }
}
