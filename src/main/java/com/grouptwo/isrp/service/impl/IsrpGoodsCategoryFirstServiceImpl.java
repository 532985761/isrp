package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.dao.IsrpGoodsCategoryFirstDao;
import com.grouptwo.isrp.service.IsrpGoodsCategoryFirstService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 商品一级分类表(IsrpGoodsCategoryFirst)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@Service("isrpGoodsCategoryFirstService")
public class IsrpGoodsCategoryFirstServiceImpl implements IsrpGoodsCategoryFirstService {
    @Resource
    private IsrpGoodsCategoryFirstDao isrpGoodsCategoryFirstDao;

    /**
     * 通过ID查询单条数据
     *
     * @param goodsCategoryFirstId 主键
     * @return 实例对象
     */
    @Override
    public IsrpGoodsCategoryFirst queryById(Integer goodsCategoryFirstId) {
        return this.isrpGoodsCategoryFirstDao.queryById(goodsCategoryFirstId);
    }

    /**
     * 分页查询
     *
     * @param isrpGoodsCategoryFirst 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpGoodsCategoryFirst> queryByPage(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst, PageRequest pageRequest) {
        long total = this.isrpGoodsCategoryFirstDao.count(isrpGoodsCategoryFirst);
        return new PageImpl<>(this.isrpGoodsCategoryFirstDao.queryAllByLimit(isrpGoodsCategoryFirst, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpGoodsCategoryFirst 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoodsCategoryFirst insert(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst) {
        this.isrpGoodsCategoryFirstDao.insert(isrpGoodsCategoryFirst);
        return isrpGoodsCategoryFirst;
    }

    /**
     * 修改数据
     *
     * @param isrpGoodsCategoryFirst 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoodsCategoryFirst update(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst) {
        this.isrpGoodsCategoryFirstDao.update(isrpGoodsCategoryFirst);
        return this.queryById(isrpGoodsCategoryFirst.getGoodsCategoryFirstId());
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsCategoryFirstId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer goodsCategoryFirstId) {
        return this.isrpGoodsCategoryFirstDao.deleteById(goodsCategoryFirstId) > 0;
    }
}
