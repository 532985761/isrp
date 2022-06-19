package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpPaymentType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 支付方式表(IsrpPaymentType)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Mapper
public interface IsrpPaymentTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    IsrpPaymentType queryById(Integer typeId);

    /**
     * 查询指定行数据
     *
     * @param isrpPaymentType 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpPaymentType> queryAllByLimit(IsrpPaymentType isrpPaymentType, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpPaymentType 查询条件
     * @return 总行数
     */
    long count(IsrpPaymentType isrpPaymentType);

    /**
     * 新增数据
     *
     * @param isrpPaymentType 实例对象
     * @return 影响行数
     */
    int insert(IsrpPaymentType isrpPaymentType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpPaymentType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpPaymentType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpPaymentType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpPaymentType> entities);

    /**
     * 修改数据
     *
     * @param isrpPaymentType 实例对象
     * @return 影响行数
     */
    int update(IsrpPaymentType isrpPaymentType);

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 影响行数
     */
    int deleteById(Integer typeId);

}

