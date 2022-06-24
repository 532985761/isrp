package com.grouptwo.isrp.service;

import com.grouptwo.isrp.pojo.LoginFormPojo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface AuthService {
    /**
     * 授权
     * @param loginForm
     * @return
     */
    Map authentication(LoginFormPojo loginForm);
}
