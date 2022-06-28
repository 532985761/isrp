package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.entity.IsrpGoods;
import com.grouptwo.isrp.dao.IsrpGoodsDao;
import com.grouptwo.isrp.service.IsrpGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品表(IsrpGoods)表服务实现类
 *
 * @author makejava
 * @since 2022-06-22 10:05:16
 */
@Service("isrpGoodsService")
public class IsrpGoodsServiceImpl implements IsrpGoodsService {
    @Resource
    private IsrpGoodsDao isrpGoodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    @Override
    public IsrpGoods queryById(Long goodsId) {
        return this.isrpGoodsDao.queryById(goodsId);
    }

    /**
     * 分页查询
     *
     * @param isrpGoods 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpGoods> queryByPage(IsrpGoods isrpGoods, PageRequest pageRequest) {
        long total = this.isrpGoodsDao.count(isrpGoods);
        return new PageImpl<>(this.isrpGoodsDao.queryAllByLimit(isrpGoods, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpGoods 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoods insert(IsrpGoods isrpGoods) {
        this.isrpGoodsDao.insert(isrpGoods);
        return isrpGoods;
    }

    /**
     * 修改数据
     *
     * @param isrpGoods 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoods update(IsrpGoods isrpGoods) {
        this.isrpGoodsDao.update(isrpGoods);
        return this.queryById(isrpGoods.getGoodsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long goodsId) {
        return this.isrpGoodsDao.deleteById(goodsId) > 0;
    }

    /**
     * 查询商品信息
     * @return
     */

    @Override
    public List<IsrpGoods> queryAllGoods() {
        return this.isrpGoodsDao.queryAllGoods();
    }

    /**
     * 查询所有商品信息
     * @return
     */
    @Override
    public List<IsrpGoods> selectGoods() {
        return isrpGoodsDao.queryAllGoods();
    }

    /**
     * 通过userId查询商品信息
     * @param userId
     * @return
     */
    @Override
    public List<IsrpGoods> selectGoodsByUserId(String userId) {
        return isrpGoodsDao.selectGoodsByUserId(userId);
    }
}
