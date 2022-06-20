package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 商品订单表(IsrpOrder)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    IsrpOrder queryById(String orderId);

    /**
     * 分页查询
     *
     * @param isrpOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpOrder> queryByPage(IsrpOrder isrpOrder, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    IsrpOrder insert(IsrpOrder isrpOrder);

    /**
     * 修改数据
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    IsrpOrder update(IsrpOrder isrpOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);

}
