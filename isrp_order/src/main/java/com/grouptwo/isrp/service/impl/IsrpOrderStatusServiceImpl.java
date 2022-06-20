package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.dao.IsrpOrderStatusDao;
import com.grouptwo.isrp.entity.IsrpOrderStatus;
import com.grouptwo.isrp.service.IsrpOrderStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单状态表(IsrpOrderStatus)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpOrderStatusService")
public class IsrpOrderStatusServiceImpl implements IsrpOrderStatusService {
    @Resource
    private IsrpOrderStatusDao isrpOrderStatusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public IsrpOrderStatus queryById(Integer orderId) {
        return this.isrpOrderStatusDao.queryById(orderId);
    }

    /**
     * 分页查询
     *
     * @param isrpOrderStatus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpOrderStatus> queryByPage(IsrpOrderStatus isrpOrderStatus, PageRequest pageRequest) {
        long total = this.isrpOrderStatusDao.count(isrpOrderStatus);
        return new PageImpl<>(this.isrpOrderStatusDao.queryAllByLimit(isrpOrderStatus, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpOrderStatus 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrderStatus insert(IsrpOrderStatus isrpOrderStatus) {
        this.isrpOrderStatusDao.insert(isrpOrderStatus);
        return isrpOrderStatus;
    }

    /**
     * 修改数据
     *
     * @param isrpOrderStatus 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrderStatus update(IsrpOrderStatus isrpOrderStatus) {
        this.isrpOrderStatusDao.update(isrpOrderStatus);
        return this.queryById(isrpOrderStatus.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer orderId) {
        return this.isrpOrderStatusDao.deleteById(orderId) > 0;
    }
}
