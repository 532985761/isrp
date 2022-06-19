package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 商品二级分类表(IsrpGoodsCategorySecond)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public class IsrpGoodsCategorySecond implements Serializable {
    private static final long serialVersionUID = 484820546162522114L;
    
    private Integer goodsCategorySecondId;
    /**
     * 商品一级分类ID
     */
    private Integer goodsCategoryFirstId;
    
    private String goodsCategorySecondName;


    public Integer getGoodsCategorySecondId() {
        return goodsCategorySecondId;
    }

    public void setGoodsCategorySecondId(Integer goodsCategorySecondId) {
        this.goodsCategorySecondId = goodsCategorySecondId;
    }

    public Integer getGoodsCategoryFirstId() {
        return goodsCategoryFirstId;
    }

    public void setGoodsCategoryFirstId(Integer goodsCategoryFirstId) {
        this.goodsCategoryFirstId = goodsCategoryFirstId;
    }

    public String getGoodsCategorySecondName() {
        return goodsCategorySecondName;
    }

    public void setGoodsCategorySecondName(String goodsCategorySecondName) {
        this.goodsCategorySecondName = goodsCategorySecondName;
    }

}

