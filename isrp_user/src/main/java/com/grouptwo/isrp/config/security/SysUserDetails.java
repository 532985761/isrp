package com.grouptwo.isrp.config.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @program: isrp
 * @description: security用户配置
 * @author: Wilburn
 * @create: 2022-06-22 23:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDetails implements UserDetails {
    private static final long serialVersionUID = 751617049170797869L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String headerImg;
    /**
     * 密码
     */
    private String password;
    /**
     * 角色0-普通用户 1-商家 2-平台管理员
     */
    private Integer role;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 身份证号
     */
    private String idCardNum;
    /**
     * 性别 0-女 1-男 2-未知
     */
    private Integer sex;
    /**
     * 账户状态 0-封禁 1-正常
     */
    private Integer status;
    /**
     * 城市
     */
    private String addressCity;
    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime birth;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    /**
     * 个性签名
     */
    private String sign;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}
