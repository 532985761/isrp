package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpPaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 支付方式表(IsrpPaymentType)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpPaymentTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    IsrpPaymentType queryById(Integer typeId);

    /**
     * 分页查询
     *
     * @param isrpPaymentType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpPaymentType> queryByPage(IsrpPaymentType isrpPaymentType, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpPaymentType 实例对象
     * @return 实例对象
     */
    IsrpPaymentType insert(IsrpPaymentType isrpPaymentType);

    /**
     * 修改数据
     *
     * @param isrpPaymentType 实例对象
     * @return 实例对象
     */
    IsrpPaymentType update(IsrpPaymentType isrpPaymentType);

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer typeId);

}
