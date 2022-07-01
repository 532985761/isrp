package com.grouptwo.isrp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 商品订单表(IsrpOrder)实体类
 *
 * @author makejava
 * @since 2022-06-30 16:20:39
 */
public class IsrpOrder implements Serializable {
    private static final long serialVersionUID = 221850559366350099L;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 支付方式编号
     */
    private Integer typeId;
    /**
     * 物流公司编号
     */
    private Integer logisticsCompanyId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    /**
     * 订单状态：0-未付款，1已付款
     */
    private Integer orderStatus;
    /**
     * 付款时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime payTime;
    /**
     * 商品总价
     */
    private BigDecimal goodsTotalPrice;
    /**
     * 实际付款
     */
    private BigDecimal goodsPayReal;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人邮件
     */
    private String receiverEmail;
    /**
     * 收货人省份
     */
    private String receiverProvince;
    /**
     * 收货人城市
     */
    private String receiverCity;
    /**
     * 收货人地区
     */
    private String receiverArea;
    /**
     * 收货人街道
     */
    private String receiverStreet;
    /**
     * 收货人详细地址
     */
    private String receiverDetailAddress;
    /**
     * 确认订单状态：0-未发货，1-已发货
     */
    private Integer confirmStatus;
    /**
     * 发货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime deliveryTime;
    /**
     * 收货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime receiveTime;
    /**
     * 评论时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime modifyTime;
    /**
     * 下单时租用时间，和模式做匹配，共享租赁不用
     */
    private Double rentDays;
    /**
     * 实际租用时间，三个模式用
     */
    private Double rentRealDays;
    /**
     * 商户ID
     */
    private String shopUserId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getLogisticsCompanyId() {
        return logisticsCompanyId;
    }

    public void setLogisticsCompanyId(Integer logisticsCompanyId) {
        this.logisticsCompanyId = logisticsCompanyId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public BigDecimal getGoodsPayReal() {
        return goodsPayReal;
    }

    public void setGoodsPayReal(BigDecimal goodsPayReal) {
        this.goodsPayReal = goodsPayReal;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverStreet() {
        return receiverStreet;
    }

    public void setReceiverStreet(String receiverStreet) {
        this.receiverStreet = receiverStreet;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Double getRentDays() {
        return rentDays;
    }

    public void setRentDays(Double rentDays) {
        this.rentDays = rentDays;
    }

    public Double getRentRealDays() {
        return rentRealDays;
    }

    public void setRentRealDays(Double rentRealDays) {
        this.rentRealDays = rentRealDays;
    }

    public String getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(String shopUserId) {
        this.shopUserId = shopUserId;
    }
}

