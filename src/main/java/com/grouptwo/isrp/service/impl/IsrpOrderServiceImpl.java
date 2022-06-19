package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.entity.IsrpOrder;
import com.grouptwo.isrp.dao.IsrpOrderDao;
import com.grouptwo.isrp.service.IsrpOrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

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

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public IsrpOrder queryById(String orderId) {
        return this.isrpOrderDao.queryById(orderId);
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
    public IsrpOrder insert(IsrpOrder isrpOrder) {
        this.isrpOrderDao.insert(isrpOrder);
        return isrpOrder;
    }

    /**
     * 修改数据
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrder update(IsrpOrder isrpOrder) {
        this.isrpOrderDao.update(isrpOrder);
        return this.queryById(isrpOrder.getOrderId());
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
}
