package com.grouptwo.isrp.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(IsrpUser)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public class IsrpUser implements Serializable {
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
    private Date birth;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 个性签名
     */
    private String sign;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}

