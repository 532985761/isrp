package com.grouptwo.isrp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单模式表(IsrpOrderModel)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Data
public class IsrpOrderModel implements Serializable {
    private static final long serialVersionUID = -88148056171429043L;
    /**
     * 订单模式ID
     */
    private Integer orderModelId;
    /**
     * 商品ID
     */
    private Long goodsId;
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderModelName() {
        return orderModelName;
    }

    public void setOrderModelName(String orderModelName) {
        this.orderModelName = orderModelName;
    }

}

