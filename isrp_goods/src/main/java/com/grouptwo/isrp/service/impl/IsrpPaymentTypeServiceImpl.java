package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.dao.IsrpPaymentTypeDao;
import com.grouptwo.isrp.entity.IsrpPaymentType;
import com.grouptwo.isrp.service.IsrpPaymentTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 支付方式表(IsrpPaymentType)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpPaymentTypeService")
public class IsrpPaymentTypeServiceImpl implements IsrpPaymentTypeService {
    @Resource
    private IsrpPaymentTypeDao isrpPaymentTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    @Override
    public IsrpPaymentType queryById(Integer typeId) {
        return this.isrpPaymentTypeDao.queryById(typeId);
    }

    /**
     * 分页查询
     *
     * @param isrpPaymentType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpPaymentType> queryByPage(IsrpPaymentType isrpPaymentType, PageRequest pageRequest) {
        long total = this.isrpPaymentTypeDao.count(isrpPaymentType);
        return new PageImpl<>(this.isrpPaymentTypeDao.queryAllByLimit(isrpPaymentType, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpPaymentType 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpPaymentType insert(IsrpPaymentType isrpPaymentType) {
        this.isrpPaymentTypeDao.insert(isrpPaymentType);
        return isrpPaymentType;
    }

    /**
     * 修改数据
     *
     * @param isrpPaymentType 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpPaymentType update(IsrpPaymentType isrpPaymentType) {
        this.isrpPaymentTypeDao.update(isrpPaymentType);
        return this.queryById(isrpPaymentType.getTypeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer typeId) {
        return this.isrpPaymentTypeDao.deleteById(typeId) > 0;
    }
}
