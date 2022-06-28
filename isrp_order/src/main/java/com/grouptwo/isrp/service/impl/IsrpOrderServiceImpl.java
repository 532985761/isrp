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
     * 修改数据
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
}
