package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpEnterprisePayable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 商家收款信息表(IsrpEnterprisePayable)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:31
 */
public interface IsrpEnterprisePayableService {

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    IsrpEnterprisePayable queryById(Integer typeId);

    /**
     * 分页查询
     *
     * @param isrpEnterprisePayable 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpEnterprisePayable> queryByPage(IsrpEnterprisePayable isrpEnterprisePayable, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpEnterprisePayable 实例对象
     * @return 实例对象
     */
    IsrpEnterprisePayable insert(IsrpEnterprisePayable isrpEnterprisePayable);

    /**
     * 修改数据
     *
     * @param isrpEnterprisePayable 实例对象
     * @return 实例对象
     */
    IsrpEnterprisePayable update(IsrpEnterprisePayable isrpEnterprisePayable);

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer typeId);

}
