package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpUserProp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 用户收货配置表(IsrpUserProp)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Mapper
public interface IsrpUserPropDao {

    /**
     * 通过ID查询单条数据
     *
     * @param propId 主键
     * @return 实例对象
     */
    IsrpUserProp queryById(Long propId);

    /**
     * 查询指定行数据
     *
     * @param isrpUserProp 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpUserProp> queryAllByLimit(IsrpUserProp isrpUserProp, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpUserProp 查询条件
     * @return 总行数
     */
    long count(IsrpUserProp isrpUserProp);

    /**
     * 新增数据
     *
     * @param isrpUserProp 实例对象
     * @return 影响行数
     */
    int insert(IsrpUserProp isrpUserProp);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpUserProp> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpUserProp> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpUserProp> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpUserProp> entities);

    /**
     * 修改数据
     *
     * @param isrpUserProp 实例对象
     * @return 影响行数
     */
    int update(IsrpUserProp isrpUserProp);

    /**
     * 通过主键删除数据
     *
     * @param propId 主键
     * @return 影响行数
     */
    int deleteById(Long propId);

}

