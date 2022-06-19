package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 订单流程表(IsrpOrderProcess)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public class IsrpOrderProcess implements Serializable {
    private static final long serialVersionUID = 625134227694713163L;
    /**
     * 订单流程ID
     */
    private Integer orderProcessId;
    /**
     * 订单模式ID
     */
    private Integer orderModelId;
    /**
     * 订单流程名称
     */
    private String orderProcessName;


    public Integer getOrderProcessId() {
        return orderProcessId;
    }

    public void setOrderProcessId(Integer orderProcessId) {
        this.orderProcessId = orderProcessId;
    }

    public Integer getOrderModelId() {
        return orderModelId;
    }

    public void setOrderModelId(Integer orderModelId) {
        this.orderModelId = orderModelId;
    }

    public String getOrderProcessName() {
        return orderProcessName;
    }

    public void setOrderProcessName(String orderProcessName) {
        this.orderProcessName = orderProcessName;
    }

}

