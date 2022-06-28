package com.grouptwo.isrp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: isrp
 * @description: 添加IsrpUser实体
 * @author: Wilburn
 * @create: 2022-06-28 22:03
 **/
@Data
@ToString
public class IsrpUserAddPojo implements Serializable {
    private static final long serialVersionUID = 751617049170797869L;
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
     * 城市
     */
    private String addressCity;
    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birth;
    /**
     * 个性签名
     */
    private String sign;
}
