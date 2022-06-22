package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 商品表(IsrpGoods)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-22 09:28:38
 */
public interface IsrpGoodsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    IsrpGoods queryById(Long goodsId);

    /**
     * 查询指定行数据
     *
     * @param isrpGoods 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpGoods> queryAllByLimit(IsrpGoods isrpGoods, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpGoods 查询条件
     * @return 总行数
     */
    long count(IsrpGoods isrpGoods);

    /**
     * 新增数据
     *
     * @param isrpGoods 实例对象
     * @return 影响行数
     */
    int insert(IsrpGoods isrpGoods);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpGoods> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpGoods> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpGoods> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpGoods> entities);

    /**
     * 修改数据
     *
     * @param isrpGoods 实例对象
     * @return 影响行数
     */
    int update(IsrpGoods isrpGoods);

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 影响行数
     */
    int deleteById(Long goodsId);

}

