package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 订单状态表(IsrpOrderStatus)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public class IsrpOrderStatus implements Serializable {
    private static final long serialVersionUID = 390376361335810235L;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单流程ID
     */
    private Integer orderProcessId;
    /**
     * 订单状态描述
     */
    private String orderStatusDesc;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderProcessId() {
        return orderProcessId;
    }

    public void setOrderProcessId(Integer orderProcessId) {
        this.orderProcessId = orderProcessId;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

}

