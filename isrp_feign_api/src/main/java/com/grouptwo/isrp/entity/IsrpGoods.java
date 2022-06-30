package com.grouptwo.isrp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 商品表(IsrpGoods)实体类
 *
 * @author makejava
 * @since 2022-06-30 16:12:38
 */
public class IsrpGoods implements Serializable {
    private static final long serialVersionUID = -64811633993740194L;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 二级分类ID
     */
    private Integer goodsCategorySecondId;
    /**
     * 用户ID
     */
    private String userId;
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
     * 订单模式ID
     */
    private Integer orderModelId;
    /**
     * 商品状态:1为已发布商品;0为待审核
     */
    private Integer goodsStatus;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    /**
     * 限制租用时间，以租代售，先租后买
     */
    private Double rentLimitDays;
    /**
     * 每天租用价钱
     */
    private Double rentPricePerDay;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Integer getOrderModelId() {
        return orderModelId;
    }

    public void setOrderModelId(Integer orderModelId) {
        this.orderModelId = orderModelId;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Double getRentLimitDays() {
        return rentLimitDays;
    }

    public void setRentLimitDays(Double rentLimitDays) {
        this.rentLimitDays = rentLimitDays;
    }

    public Double getRentPricePerDay() {
        return rentPricePerDay;
    }

    public void setRentPricePerDay(Double rentPricePerDay) {
        this.rentPricePerDay = rentPricePerDay;
    }

}

