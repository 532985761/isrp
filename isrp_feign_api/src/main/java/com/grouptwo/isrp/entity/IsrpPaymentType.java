package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 支付方式表(IsrpPaymentType)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public class IsrpPaymentType implements Serializable {
    private static final long serialVersionUID = -51817924188834886L;
    /**
     * 支付方式编号
     */
    private Integer typeId;
    /**
     * 支付方式名称
     */
    private String typeName;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}

