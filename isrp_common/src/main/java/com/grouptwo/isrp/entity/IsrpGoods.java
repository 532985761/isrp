package com.grouptwo.isrp.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 商品表(IsrpGoods)实体类
 *
 * @author makejava
 * @since 2022-06-22 10:05:10
 */
public class IsrpGoods implements Serializable {
    private static final long serialVersionUID = 330785941062640324L;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDesc;
    /**
     * 商品图片
     */
    private String goodsImg;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 销量
     */
    private Long goodsSaleCount;
    /**
     * 商品状态:1为暂未被购买;0为已购买
     */
    private Integer goodsStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    
    private Integer goodsCategorySecondId;
    /**
     * 用户ID
     */
    private String userId;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getGoodsSaleCount() {
        return goodsSaleCount;
    }

    public void setGoodsSaleCount(Long goodsSaleCount) {
        this.goodsSaleCount = goodsSaleCount;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGoodsCategorySecondId() {
        return goodsCategorySecondId;
    }

    public void setGoodsCategorySecondId(Integer goodsCategorySecondId) {
        this.goodsCategorySecondId = goodsCategorySecondId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

