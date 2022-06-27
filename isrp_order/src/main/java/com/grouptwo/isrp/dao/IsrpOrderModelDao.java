package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpOrderModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单模式表(IsrpOrderModel)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-27 10:03:48
 */
@Mapper
public interface IsrpOrderModelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderModelId 主键
     * @return 实例对象
     */
    IsrpOrderModel queryById(Integer orderModelId);

    /**
     * 查询指定行数据
     *
     * @param isrpOrderModel 查询条件
     * @param pageable       分页对象
     * @return 对象列表
     */
    List<IsrpOrderModel> queryAllByLimit(IsrpOrderModel isrpOrderModel, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpOrderModel 查询条件
     * @return 总行数
     */
    long count(IsrpOrderModel isrpOrderModel);

    /**
     * 新增数据
     *
     * @param isrpOrderModel 实例对象
     * @return 影响行数
     */
    int insert(IsrpOrderModel isrpOrderModel);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrderModel> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpOrderModel> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrderModel> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpOrderModel> entities);

    /**
     * 修改数据
     *
     * @param isrpOrderModel 实例对象
     * @return 影响行数
     */
    int update(IsrpOrderModel isrpOrderModel);

    /**
     * 通过主键删除数据
     *
     * @param orderModelId 主键
     * @return 影响行数
     */
    int deleteById(Integer orderModelId);

}

