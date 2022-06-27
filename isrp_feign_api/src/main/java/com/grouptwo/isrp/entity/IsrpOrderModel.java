package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 订单模式表(IsrpOrderModel)实体类
 *
 * @author makejava
 * @since 2022-06-27 10:03:49
 */
public class IsrpOrderModel implements Serializable {
    private static final long serialVersionUID = -90218866043282196L;
    /**
     * 订单模式ID
     */
    private Integer orderModelId;
    /**
     * 订单模式名称
     */
    private String orderModelName;


    public Integer getOrderModelId() {
        return orderModelId;
    }

    public void setOrderModelId(Integer orderModelId) {
        this.orderModelId = orderModelId;
    }

    public String getOrderModelName() {
        return orderModelName;
    }

    public void setOrderModelName(String orderModelName) {
        this.orderModelName = orderModelName;
    }

}

