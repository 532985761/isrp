package com.grouptwo.isrp.dao;

import com.grouptwo.isrp.entity.IsrpOrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单状态表(IsrpOrderStatus)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Mapper
public interface IsrpOrderStatusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    IsrpOrderStatus queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param isrpOrderStatus 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IsrpOrderStatus> queryAllByLimit(IsrpOrderStatus isrpOrderStatus, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param isrpOrderStatus 查询条件
     * @return 总行数
     */
    long count(IsrpOrderStatus isrpOrderStatus);

    /**
     * 新增数据
     *
     * @param isrpOrderStatus 实例对象
     * @return 影响行数
     */
    int insert(IsrpOrderStatus isrpOrderStatus);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrderStatus> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IsrpOrderStatus> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IsrpOrderStatus> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IsrpOrderStatus> entities);

    /**
     * 修改数据
     *
     * @param isrpOrderStatus 实例对象
     * @return 影响行数
     */
    int update(IsrpOrderStatus isrpOrderStatus);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(Integer orderId);

    List<IsrpOrderStatus> selectByOrderId(String orderId);

    Integer selectStatusByOrderId(String orderId);

    int changeStatus(@Param("orderId") String orderId,@Param("orderProcessId") Integer orderProcessId);

    int selectStatusByDesc(@Param("orderId") String orderId,@Param("desc") String desc);

    void updateByOrderId(String orderId);

    List<Integer> getNextTwo(String orderId);

    void updateTwo(@Param("orderId") String orderId,@Param("list") List<Integer> list);
}

