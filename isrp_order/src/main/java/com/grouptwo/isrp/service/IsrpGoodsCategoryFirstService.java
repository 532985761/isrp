package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 商品一级分类表(IsrpGoodsCategoryFirst)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public interface IsrpGoodsCategoryFirstService {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsCategoryFirstId 主键
     * @return 实例对象
     */
    IsrpGoodsCategoryFirst queryById(Integer goodsCategoryFirstId);

    /**
     * 分页查询
     *
     * @param isrpGoodsCategoryFirst 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpGoodsCategoryFirst> queryByPage(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpGoodsCategoryFirst 实例对象
     * @return 实例对象
     */
    IsrpGoodsCategoryFirst insert(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst);

    /**
     * 修改数据
     *
     * @param isrpGoodsCategoryFirst 实例对象
     * @return 实例对象
     */
    IsrpGoodsCategoryFirst update(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst);

    /**
     * 通过主键删除数据
     *
     * @param goodsCategoryFirstId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer goodsCategoryFirstId);

}
