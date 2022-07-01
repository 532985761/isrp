package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.client.GoodsClient;
import com.grouptwo.isrp.dao.IsrpOrderDao;
import com.grouptwo.isrp.entity.IsrpGoods;
import com.grouptwo.isrp.entity.IsrpOrder;
import com.grouptwo.isrp.service.IsrpOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品订单表(IsrpOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpOrderService")
public class IsrpOrderServiceImpl implements IsrpOrderService {
    @Resource
    private IsrpOrderDao isrpOrderDao;
    @Resource
    private GoodsClient goodsClient;



    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public IsrpOrder selectOrderById(String orderId) {
        return this.isrpOrderDao.selectOrderById(orderId);
    }

    /**
     * 分页查询
     *
     * @param isrpOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpOrder> queryByPage(IsrpOrder isrpOrder, PageRequest pageRequest) {
        long total = this.isrpOrderDao.count(isrpOrder);
        return new PageImpl<>(this.isrpOrderDao.queryAllByLimit(isrpOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrder insertOrder(IsrpOrder isrpOrder) {
        this.isrpOrderDao.insertOrder(isrpOrder);
        return isrpOrder;
    }

    /**
     * 修改数据（确认发货）
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrder updateOrder(IsrpOrder isrpOrder) {
        this.isrpOrderDao.updateOrder(isrpOrder);
        return this.selectOrderById(isrpOrder.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String orderId) {
        return this.isrpOrderDao.deleteById(orderId) > 0;
    }

    /**
     * 查询所有订单信息
     * @return 查询结果
     */
    @Override
    public List<IsrpOrder> selectAllOrders() {
        return this.isrpOrderDao.selectAllOrders();
    }

    @Override
    public Map<String, Object> insertOrderByGoodsId(Integer goodsId) {
        IsrpGoods goods = goodsClient.queryByGoodsId(goodsId);
        Map<String, Object> map = new HashMap<>();
        //查询商品是否可以租
        if (goods.getGoodsStatus() == 0){
            map.put("msg","商品已被租赁，请选择其他商品");
            return map;
        }
        return map;
    }

    /**
     * 通过商家id查询待支付订单
     * @param shopUserId
     * @return
     */
    @Override
    public List<IsrpOrder> selectOrderIndexByShopUserId(String shopUserId) {
        return isrpOrderDao.selectOrderIndexByShopUserId(shopUserId);
    }

    /**
     * 通过商家id查询已完成订单
     * @param shopUserId
     * @return
     */
    @Override
    public List<IsrpOrder> selectOrderFinishByShopUserId(String shopUserId) {
        return isrpOrderDao.selectOrderFinishByShopUserId(shopUserId);
    }
    /**
     * 通过商家id查询所有订单
     * @param shopUserId
     * @return
     */
    @Override
    public List<IsrpOrder> selectOrderAllByShopUserId(String shopUserId) {
        return isrpOrderDao.selectOrderAllByShopUserId(shopUserId);
    }

//    @Override
//    public Map<String, Object> getUserAndOrderInfo(String userId) {
//        //查询所有订单后提取goodsId
//
//        //通过订单表goodsId查询goods表，保证唯一性，提升查询速度
//        List<Long> goodsIds = new ArrayList<>();
//        goodsIds.add(1L);
//        goodsIds.add(2L);
//        goodsClient.queryByGoodsIds(goodsIds);
//        //得到上述goods表所有数据，进行数据帅选，筛选条件为--传入的`userId` 和goods表中userId相等--
//
//        //筛选之后得到list列表，此时list数据就是和当前用户相关的订单及订单对应商品信息
//
////         goodsClient.queryByUserId(userId);
//        return new HashMap<>();
//    }
}
