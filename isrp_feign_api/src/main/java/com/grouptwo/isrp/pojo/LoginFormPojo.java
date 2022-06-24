package com.grouptwo.isrp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: isrp
 * @description: 登录表实体
 * @author: Wilburn
 * @create: 2022-06-22 21:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginFormPojo {

    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 角色
     */
    private Integer role;
    /**
     * 验证码
     */
    private String code;

    /**
     * 请求
     */
    private String ip;
}