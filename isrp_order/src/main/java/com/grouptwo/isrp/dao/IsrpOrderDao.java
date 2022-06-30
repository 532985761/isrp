package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品订单表(IsrpOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@Mapper
public interface IsrpOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    IsrpOrder selectOrderById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param isrpOrder 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpOrder> queryAllByLimit(IsrpOrder isrpOrder, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpOrder 查询条件
     * @return 总行数
     */
    long count(IsrpOrder isrpOrder);

    /**
     * 新增数据
     *
     * @param isrpOrder 实例对象
     * @return 影响行数
     */
    int insertOrder(IsrpOrder isrpOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpOrder> entities);

    /**
     * 修改数据
     *
     * @param isrpOrder 实例对象
     * @return 影响行数
     */
    int updateOrder(IsrpOrder isrpOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

    /**
     * 查询所有订单信息
     *
     */
    List<IsrpOrder> selectAllOrders();

    /**
     * 通过商家id查询待支付订单信息
     * @param shopUserId
     * @return
     */
    List<IsrpOrder> selectOrderIndexByShopUserId(String shopUserId);

    /**
     * 通过商家id查询已完成订单信息
     * @param shopUserId
     * @return
     */
    List<IsrpOrder> selectOrderFinishByShopUserId(String shopUserId);
    /**
     * 通过商家id查询所有订单信息
     * @param shopUserId
     * @return
     */
    List<IsrpOrder> selectOrderAllByShopUserId(String shopUserId);
}

