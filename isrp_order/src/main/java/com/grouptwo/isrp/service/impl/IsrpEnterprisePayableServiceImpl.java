package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.dao.IsrpEnterprisePayableDao;
import com.grouptwo.isrp.entity.IsrpEnterprisePayable;
import com.grouptwo.isrp.service.IsrpEnterprisePayableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商家收款信息表(IsrpEnterprisePayable)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@Service("isrpEnterprisePayableService")
public class IsrpEnterprisePayableServiceImpl implements IsrpEnterprisePayableService {
    @Resource
    private IsrpEnterprisePayableDao isrpEnterprisePayableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    @Override
    public IsrpEnterprisePayable queryById(Integer typeId) {
        return this.isrpEnterprisePayableDao.queryById(typeId);
    }

    /**
     * 分页查询
     *
     * @param isrpEnterprisePayable 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpEnterprisePayable> queryByPage(IsrpEnterprisePayable isrpEnterprisePayable, PageRequest pageRequest) {
        long total = this.isrpEnterprisePayableDao.count(isrpEnterprisePayable);
        return new PageImpl<>(this.isrpEnterprisePayableDao.queryAllByLimit(isrpEnterprisePayable, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpEnterprisePayable 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpEnterprisePayable insert(IsrpEnterprisePayable isrpEnterprisePayable) {
        this.isrpEnterprisePayableDao.insert(isrpEnterprisePayable);
        return isrpEnterprisePayable;
    }

    /**
     * 修改数据
     *
     * @param isrpEnterprisePayable 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpEnterprisePayable update(IsrpEnterprisePayable isrpEnterprisePayable) {
        this.isrpEnterprisePayableDao.update(isrpEnterprisePayable);
        return this.queryById(isrpEnterprisePayable.getTypeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer typeId) {
        return this.isrpEnterprisePayableDao.deleteById(typeId) > 0;
    }
}
