package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 商品表(IsrpGoods)表服务接口
 *
 * @author makejava
 * @since 2022-06-22 10:05:16
 */
public interface IsrpGoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    IsrpGoods queryById(Long goodsId);

    /**
     * 分页查询
     *
     * @param isrpGoods 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpGoods> queryByPage(IsrpGoods isrpGoods, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpGoods 实例对象
     * @return 实例对象
     */
    IsrpGoods insert(IsrpGoods isrpGoods);

    /**
     * 修改数据
     *
     * @param isrpGoods 实例对象
     * @return 实例对象
     */
    IsrpGoods update(IsrpGoods isrpGoods);

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    boolean deleteById(Long goodsId);

    /**
     * 查询所有商品信息
     * @return 返回商品数据
     */
    List<IsrpGoods> queryAllGoods();

    /**
     * 查询所有商品信息
     * @return 返回商品数据
     */
    List<IsrpGoods> selectGoods();

    /**
     * 通过userId查询商品信息
     * @param userId
     * @return 返回用户的商品信息
     */
    List<IsrpGoods> selectGoodsByUserId(String userId);

    Map<String, Object> getGoodsInfo(int firstId, int secondId);
}
