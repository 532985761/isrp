package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpOrderProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单流程表(IsrpOrderProcess)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Mapper
public interface IsrpOrderProcessDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderProcessId 主键
     * @return 实例对象
     */
    IsrpOrderProcess queryById(Integer orderProcessId);

    /**
     * 查询指定行数据
     *
     * @param isrpOrderProcess 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpOrderProcess> queryAllByLimit(IsrpOrderProcess isrpOrderProcess, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpOrderProcess 查询条件
     * @return 总行数
     */
    long count(IsrpOrderProcess isrpOrderProcess);

    /**
     * 新增数据
     *
     * @param isrpOrderProcess 实例对象
     * @return 影响行数
     */
    int insert(IsrpOrderProcess isrpOrderProcess);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrderProcess> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpOrderProcess> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrderProcess> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpOrderProcess> entities);

    /**
     * 修改数据
     *
     * @param isrpOrderProcess 实例对象
     * @return 影响行数
     */
    int update(IsrpOrderProcess isrpOrderProcess);

    /**
     * 通过主键删除数据
     *
     * @param orderProcessId 主键
     * @return 影响行数
     */
    int deleteById(Integer orderProcessId);

    /**
     * 通过orderModelId批量删除
     *
     * @param orderModelId
     * @return
     */
    int deleteByModelId(Integer orderModelId);

}

