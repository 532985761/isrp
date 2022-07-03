package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpOrder;
import com.grouptwo.isrp.pojo.CartVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品订单表(IsrpOrder)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpOrderService {

    /**
     * 通过ID查询单条订单数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    IsrpOrder selectOrderById(String orderId);

    /**
     * 分页查询
     *
     * @param isrpOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpOrder> queryByPage(IsrpOrder isrpOrder, PageRequest pageRequest);

    /**
     * 新增订单数据
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    IsrpOrder insertOrder(IsrpOrder isrpOrder);

    /**
     * 修改数据（确认发货）
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    IsrpOrder updateOrder(IsrpOrder isrpOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);

    /**
     * 查询所有订单信息
     *
     */
    List<IsrpOrder> selectAllOrders();

    Map<String, Object> insertOrderByGoodsId(Integer goodsId);

    /**
     * 通过商家id查询待支付订单
     * @param shopUserId
     * @return
     */
    List<IsrpOrder> selectOrderIndexByShopUserId(String shopUserId);

    /**
     * 通过商家id查询已完成的订单
     * @param shopUserId
     * @return
     */
    List<IsrpOrder> selectOrderFinishByShopUserId(String shopUserId);
    /**
     * 通过商家id查询所有的订单
     * @param shopUserId
     * @return
     */
    List<IsrpOrder> selectOrderAllByShopUserId(String shopUserId);


    Map<String ,Object> addToCart(Integer goodsId, BigDecimal days);

    Map<String,Object> getCart();

    boolean deleteCartByGoodsId(Integer goodsId);

    Map<String,Object> getPreorderInfo(Integer goodsId);

//    Map<String,Object> getUserAndOrderInfo(String userId);
}
