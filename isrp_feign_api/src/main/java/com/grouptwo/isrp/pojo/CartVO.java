package com.grouptwo.isrp.pojo;

import java.math.BigDecimal;

/**
 * @author 张洪志
 */
public class CartVO {
    private Integer goodsId;
    private String goodsImg;
    private String goodsName;
    private String goodsDesc;
    private BigDecimal rentDays = BigDecimal.valueOf(1.00);
    private BigDecimal rentPrice;
    private String modal;
    private BigDecimal rentLimit;
    private BigDecimal total;

    private BigDecimal goodsPrice;

    @Override
    public String toString() {
        return "CartVO{" +
                "goodsId=" + goodsId +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", rentDays=" + rentDays +
                ", rentPrice=" + rentPrice +
                ", modal='" + modal + '\'' +
                ", rentLimit=" + rentLimit +
                ", total=" + total +
                ", goodsPrice=" + goodsPrice +
                '}';
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public BigDecimal getRentDays() {
        return rentDays;
    }

    public void setRentDays(BigDecimal rentDays) {
        this.rentDays = rentDays;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public BigDecimal getRentLimit() {
        return rentLimit;
    }

    public void setRentLimit(BigDecimal rentLimit) {
        this.rentLimit = rentLimit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
