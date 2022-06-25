package com.grouptwo.isrp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: isrp
 * @description: 登陆表
 * @author: Wilburn
 * @create: 2022-06-23 18:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
}
