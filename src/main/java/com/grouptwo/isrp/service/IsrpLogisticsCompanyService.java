package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpLogisticsCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 物流公司(IsrpLogisticsCompany)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public interface IsrpLogisticsCompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param logisticsCompanyId 主键
     * @return 实例对象
     */
    IsrpLogisticsCompany queryById(Integer logisticsCompanyId);

    /**
     * 分页查询
     *
     * @param isrpLogisticsCompany 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpLogisticsCompany> queryByPage(IsrpLogisticsCompany isrpLogisticsCompany, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpLogisticsCompany 实例对象
     * @return 实例对象
     */
    IsrpLogisticsCompany insert(IsrpLogisticsCompany isrpLogisticsCompany);

    /**
     * 修改数据
     *
     * @param isrpLogisticsCompany 实例对象
     * @return 实例对象
     */
    IsrpLogisticsCompany update(IsrpLogisticsCompany isrpLogisticsCompany);

    /**
     * 通过主键删除数据
     *
     * @param logisticsCompanyId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer logisticsCompanyId);

}
