package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpOrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 订单模式表(IsrpOrderModel)表服务接口
 *
 * @author makejava
 * @since 2022-06-27 10:03:50
 */
public interface IsrpOrderModelService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderModelId 主键
     * @return 实例对象
     */
    IsrpOrderModel queryById(Integer orderModelId);

    /**
     * 分页查询
     *
     * @param isrpOrderModel 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<IsrpOrderModel> queryByPage(IsrpOrderModel isrpOrderModel, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpOrderModel 实例对象
     * @return 实例对象
     */
    IsrpOrderModel insert(IsrpOrderModel isrpOrderModel);

    /**
     * 修改数据
     *
     * @param isrpOrderModel 实例对象
     * @return 实例对象
     */
    IsrpOrderModel update(IsrpOrderModel isrpOrderModel);

    /**
     * 通过主键删除数据
     *
     * @param orderModelId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer orderModelId);

}
