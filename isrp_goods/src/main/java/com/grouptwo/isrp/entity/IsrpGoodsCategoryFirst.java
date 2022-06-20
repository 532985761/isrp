package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 商品一级分类表(IsrpGoodsCategoryFirst)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public class IsrpGoodsCategoryFirst implements Serializable {
    private static final long serialVersionUID = -90204794537139013L;
    /**
     * 商品一级分类ID
     */
    private Integer goodsCategoryFirstId;
    /**
     * 商品一级分类名称
     */
    private String goodsCategoryFirstName;


    public Integer getGoodsCategoryFirstId() {
        return goodsCategoryFirstId;
    }

    public void setGoodsCategoryFirstId(Integer goodsCategoryFirstId) {
        this.goodsCategoryFirstId = goodsCategoryFirstId;
    }

    public String getGoodsCategoryFirstName() {
        return goodsCategoryFirstName;
    }

    public void setGoodsCategoryFirstName(String goodsCategoryFirstName) {
        this.goodsCategoryFirstName = goodsCategoryFirstName;
    }

}

