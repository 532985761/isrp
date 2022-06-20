package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpUserProp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户收货配置表(IsrpUserProp)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpUserPropService {

    /**
     * 通过ID查询单条数据
     *
     * @param propId 主键
     * @return 实例对象
     */
    IsrpUserProp queryById(Long propId);

    /**
     * 分页查询
     *
     * @param isrpUserProp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpUserProp> queryByPage(IsrpUserProp isrpUserProp, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpUserProp 实例对象
     * @return 实例对象
     */
    IsrpUserProp insert(IsrpUserProp isrpUserProp);

    /**
     * 修改数据
     *
     * @param isrpUserProp 实例对象
     * @return 实例对象
     */
    IsrpUserProp update(IsrpUserProp isrpUserProp);

    /**
     * 通过主键删除数据
     *
     * @param propId 主键
     * @return 是否成功
     */
    boolean deleteById(Long propId);

}
