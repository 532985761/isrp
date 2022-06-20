package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpGoodsCategorySecond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 商品二级分类表(IsrpGoodsCategorySecond)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public interface IsrpGoodsCategorySecondService {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsCategorySecondId 主键
     * @return 实例对象
     */
    IsrpGoodsCategorySecond queryById(Integer goodsCategorySecondId);

    /**
     * 分页查询
     *
     * @param isrpGoodsCategorySecond 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpGoodsCategorySecond> queryByPage(IsrpGoodsCategorySecond isrpGoodsCategorySecond, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpGoodsCategorySecond 实例对象
     * @return 实例对象
     */
    IsrpGoodsCategorySecond insert(IsrpGoodsCategorySecond isrpGoodsCategorySecond);

    /**
     * 修改数据
     *
     * @param isrpGoodsCategorySecond 实例对象
     * @return 实例对象
     */
    IsrpGoodsCategorySecond update(IsrpGoodsCategorySecond isrpGoodsCategorySecond);

    /**
     * 通过主键删除数据
     *
     * @param goodsCategorySecondId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer goodsCategorySecondId);

}
