package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.dao.IsrpUserPropDao;
import com.grouptwo.isrp.entity.IsrpUserProp;
import com.grouptwo.isrp.service.IsrpUserPropService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户收货配置表(IsrpUserProp)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpUserPropService")
public class IsrpUserPropServiceImpl implements IsrpUserPropService {
    @Resource
    private IsrpUserPropDao isrpUserPropDao;

    /**
     * 通过ID查询单条数据
     *
     * @param propId 主键
     * @return 实例对象
     */
    @Override
    public IsrpUserProp queryById(Long propId) {
        return this.isrpUserPropDao.queryById(propId);
    }

    /**
     * 分页查询
     *
     * @param isrpUserProp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpUserProp> queryByPage(IsrpUserProp isrpUserProp, PageRequest pageRequest) {
        long total = this.isrpUserPropDao.count(isrpUserProp);
        return new PageImpl<>(this.isrpUserPropDao.queryAllByLimit(isrpUserProp, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpUserProp 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpUserProp insert(IsrpUserProp isrpUserProp) {
        this.isrpUserPropDao.insert(isrpUserProp);
        return isrpUserProp;
    }

    /**
     * 修改数据
     *
     * @param isrpUserProp 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpUserProp update(IsrpUserProp isrpUserProp) {
        this.isrpUserPropDao.update(isrpUserProp);
        return this.queryById(isrpUserProp.getPropId());
    }

    /**
     * 通过主键删除数据
     *
     * @param propId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long propId) {
        return this.isrpUserPropDao.deleteById(propId) > 0;
    }
}
