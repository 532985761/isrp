package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpEnterprisePayable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商家收款信息表(IsrpEnterprisePayable)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-19 19:08:29
 */
@Mapper
public interface IsrpEnterprisePayableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    IsrpEnterprisePayable queryById(Integer typeId);

    /**
     * 查询指定行数据
     *
     * @param isrpEnterprisePayable 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpEnterprisePayable> queryAllByLimit(IsrpEnterprisePayable isrpEnterprisePayable, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpEnterprisePayable 查询条件
     * @return 总行数
     */
    long count(IsrpEnterprisePayable isrpEnterprisePayable);

    /**
     * 新增数据
     *
     * @param isrpEnterprisePayable 实例对象
     * @return 影响行数
     */
    int insert(IsrpEnterprisePayable isrpEnterprisePayable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpEnterprisePayable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpEnterprisePayable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpEnterprisePayable> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpEnterprisePayable> entities);

    /**
     * 修改数据
     *
     * @param isrpEnterprisePayable 实例对象
     * @return 影响行数
     */
    int update(IsrpEnterprisePayable isrpEnterprisePayable);

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 影响行数
     */
    int deleteById(Integer typeId);

}

