package com.grouptwo.isrp.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 商品订单表(IsrpOrder)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public class IsrpOrder implements Serializable {
    private static final long serialVersionUID = -13074211167157554L;
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
    private Date createTime;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 付款时间
     */
    private Date payTime;
    /**
     * 商品总价
     */
    private Object goodsTotalPrice;
    /**
     * 实际付款
     */
    private Object goodsPayReal;
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
     * 确认订单状态
     */
    private Integer confirmStatus;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date receiveTime;
    /**
     * 评论时间
     */
    private Date modifyTime;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Object getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(Object goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public Object getGoodsPayReal() {
        return goodsPayReal;
    }

    public void setGoodsPayReal(Object goodsPayReal) {
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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}

