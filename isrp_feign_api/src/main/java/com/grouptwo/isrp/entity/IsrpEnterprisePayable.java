package com.grouptwo.isrp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 商家收款信息表(IsrpEnterprisePayable)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:30
 */
@Data
public class IsrpEnterprisePayable implements Serializable {
    private static final long serialVersionUID = -31724476136955399L;
    /**
     * 支付方式编号
     */
    private Integer typeId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 收款详细信息
     */
    private String payableDetail;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayableDetail() {
        return payableDetail;
    }

    public void setPayableDetail(String payableDetail) {
        this.payableDetail = payableDetail;
    }

}

