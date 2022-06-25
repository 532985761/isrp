package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.pojo.LoginFormPojo;
import com.grouptwo.isrp.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: isrp
 * @description: 获取token类
 * @author: Wilburn
 * @create: 2022-06-22 23:54
 **/
@RestController
@RequestMapping("/isrpAuth")
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * 授权接口
     * 只能用于内部调用
     *
     * @param loginForm
     * @return
     */
    @PostMapping("/authentication")
    public Map authentication(@RequestBody LoginFormPojo loginForm) {
        return authService.authentication(loginForm);
    }

}
