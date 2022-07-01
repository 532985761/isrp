package com.grouptwo.isrp.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 张洪志
 */
public class GoodsDetailVO {
    private Integer goodsId;
    private String address;
    private BigDecimal goodsPrice;
    private String username;
    private String goodsImg;
    private LocalDateTime createTime;
    private String goodsModel;
    private Integer count;
    private String goodsDesc;
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    @Override
    public String toString() {
        return "GoodsDetailVO{" +
                "goodsId=" + goodsId +
                ", address='" + address + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", username='" + username + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", createTime=" + createTime +
                ", goodsModel='" + goodsModel + '\'' +
                ", count=" + count +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

}
