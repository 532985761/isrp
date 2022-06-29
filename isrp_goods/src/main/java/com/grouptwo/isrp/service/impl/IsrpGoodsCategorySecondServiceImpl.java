package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.dao.IsrpGoodsCategorySecondDao;
import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.entity.IsrpGoodsCategorySecond;
import com.grouptwo.isrp.service.IsrpGoodsCategorySecondService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品二级分类表(IsrpGoodsCategorySecond)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@Service("isrpGoodsCategorySecondService")
public class IsrpGoodsCategorySecondServiceImpl implements IsrpGoodsCategorySecondService {
    @Resource
    private IsrpGoodsCategorySecondDao isrpGoodsCategorySecondDao;

    /**
     * 通过ID查询单条数据
     *
     * @param goodsCategorySecondId 主键
     * @return 实例对象
     */
    @Override
    public IsrpGoodsCategorySecond queryById(Integer goodsCategorySecondId) {
        return this.isrpGoodsCategorySecondDao.queryById(goodsCategorySecondId);
    }

    /**
     * 分页查询
     *
     * @param isrpGoodsCategorySecond 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpGoodsCategorySecond> queryByPage(IsrpGoodsCategorySecond isrpGoodsCategorySecond, PageRequest pageRequest) {
        long total = this.isrpGoodsCategorySecondDao.count(isrpGoodsCategorySecond);
        return new PageImpl<>(this.isrpGoodsCategorySecondDao.queryAllByLimit(isrpGoodsCategorySecond, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpGoodsCategorySecond 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoodsCategorySecond insert(IsrpGoodsCategorySecond isrpGoodsCategorySecond) {
        this.isrpGoodsCategorySecondDao.insert(isrpGoodsCategorySecond);
        return isrpGoodsCategorySecond;
    }

    /**
     * 修改数据
     *
     * @param isrpGoodsCategorySecond 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoodsCategorySecond update(IsrpGoodsCategorySecond isrpGoodsCategorySecond) {
        this.isrpGoodsCategorySecondDao.update(isrpGoodsCategorySecond);
        return this.queryById(isrpGoodsCategorySecond.getGoodsCategorySecondId());
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsCategorySecondId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer goodsCategorySecondId) {
        return this.isrpGoodsCategorySecondDao.deleteById(goodsCategorySecondId) > 0;
    }

    /**
     * 获取一级商品分类ID
     * @param id
     * @return
     */
    @Override
    public int getGoodsCategory(int id) {
        return isrpGoodsCategorySecondDao.getGoodsCategoryFirst(id);
    }

    @Override
    public List<IsrpGoodsCategorySecond> getGoodsCategorySecondByFirstId(int firstId) {
        return isrpGoodsCategorySecondDao.getGoodsCategorySecondByFirstId(firstId);
    }
}
