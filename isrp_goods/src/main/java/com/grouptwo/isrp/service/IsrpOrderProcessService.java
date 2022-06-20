package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpOrderProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 订单流程表(IsrpOrderProcess)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpOrderProcessService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderProcessId 主键
     * @return 实例对象
     */
    IsrpOrderProcess queryById(Integer orderProcessId);

    /**
     * 分页查询
     *
     * @param isrpOrderProcess 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpOrderProcess> queryByPage(IsrpOrderProcess isrpOrderProcess, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpOrderProcess 实例对象
     * @return 实例对象
     */
    IsrpOrderProcess insert(IsrpOrderProcess isrpOrderProcess);

    /**
     * 修改数据
     *
     * @param isrpOrderProcess 实例对象
     * @return 实例对象
     */
    IsrpOrderProcess update(IsrpOrderProcess isrpOrderProcess);

    /**
     * 通过主键删除数据
     *
     * @param orderProcessId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer orderProcessId);

}
