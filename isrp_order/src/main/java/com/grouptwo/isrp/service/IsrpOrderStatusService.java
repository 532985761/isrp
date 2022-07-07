package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpOrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 订单状态表(IsrpOrderStatus)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpOrderStatusService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    IsrpOrderStatus queryById(String orderId);

    /**
     * 分页查询
     *
     * @param isrpOrderStatus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpOrderStatus> queryByPage(IsrpOrderStatus isrpOrderStatus, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpOrderStatus 实例对象
     * @return 实例对象
     */
    IsrpOrderStatus insert(IsrpOrderStatus isrpOrderStatus);

    /**
     * 修改数据
     *
     * @param isrpOrderStatus 实例对象
     * @return 实例对象
     */
    IsrpOrderStatus update(IsrpOrderStatus isrpOrderStatus);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer orderId);

    int insertBatch(List<IsrpOrderStatus> list);

    List<IsrpOrderStatus> selectByOrderId(String orderId);

    Integer selectStatusByOrderId(String orderId);

    int changeStatus(String orderId,Integer orderProcessId);

    int selectStatusByDesc(String orderId, String desc);

    void updateByOrderId(String orderId);

    List<Integer> getNextTwo(String orderId);

    void updateNextTwo(String orderId, List<Integer> list);
}
