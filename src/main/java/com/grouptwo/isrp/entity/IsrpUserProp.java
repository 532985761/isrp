package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 用户收货配置表(IsrpUserProp)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public class IsrpUserProp implements Serializable {
    private static final long serialVersionUID = -13544687363420160L;
    /**
     * 配置编号
     */
    private Long propId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 省
     */
    private String addressProvince;
    /**
     * 城市
     */
    private String addressCity;
    /**
     * 区
     */
    private String addressArea;
    /**
     * 街道
     */
    private String addressStreet;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 收货人电话
     */
    private String receivePhone;


    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

}

