package com.grouptwo.isrp.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 商品表(IsrpGoods)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public class IsrpGoods implements Serializable {
    private static final long serialVersionUID = -24434301539217657L;
    /**
     * 商品ID
     */
    private Long goodsId;
    
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
    private Object goodsPrice;
    /**
     * 销量
     */
    private Long goodsSaleCount;
    /**
     * 创建时间
     */
    private Date createTime;


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

    public Object getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Object goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getGoodsSaleCount() {
        return goodsSaleCount;
    }

    public void setGoodsSaleCount(Long goodsSaleCount) {
        this.goodsSaleCount = goodsSaleCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

