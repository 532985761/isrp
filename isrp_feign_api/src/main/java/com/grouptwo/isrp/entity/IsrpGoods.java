package com.grouptwo.isrp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.io.Serializable;

/**
 * 商品表(IsrpGoods)实体类
 *
 * @author makejava
 * @since 2022-06-22 10:05:10
 */
@Data
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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 二级分类ID
     */
    private Integer goodsCategorySecondId;
    /**
     * 用户ID
     */
    private String userId;




}

