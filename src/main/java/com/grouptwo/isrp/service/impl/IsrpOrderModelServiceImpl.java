package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.entity.IsrpOrderModel;
import com.grouptwo.isrp.dao.IsrpOrderModelDao;
import com.grouptwo.isrp.service.IsrpOrderModelService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 订单模式表(IsrpOrderModel)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpOrderModelService")
public class IsrpOrderModelServiceImpl implements IsrpOrderModelService {
    @Resource
    private IsrpOrderModelDao isrpOrderModelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderModelId 主键
     * @return 实例对象
     */
    @Override
    public IsrpOrderModel queryById(Integer orderModelId) {
        return this.isrpOrderModelDao.queryById(orderModelId);
    }

    /**
     * 分页查询
     *
     * @param isrpOrderModel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpOrderModel> queryByPage(IsrpOrderModel isrpOrderModel, PageRequest pageRequest) {
        long total = this.isrpOrderModelDao.count(isrpOrderModel);
        return new PageImpl<>(this.isrpOrderModelDao.queryAllByLimit(isrpOrderModel, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpOrderModel 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrderModel insert(IsrpOrderModel isrpOrderModel) {
        this.isrpOrderModelDao.insert(isrpOrderModel);
        return isrpOrderModel;
    }

    /**
     * 修改数据
     *
     * @param isrpOrderModel 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrderModel update(IsrpOrderModel isrpOrderModel) {
        this.isrpOrderModelDao.update(isrpOrderModel);
        return this.queryById(isrpOrderModel.getOrderModelId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderModelId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer orderModelId) {
        return this.isrpOrderModelDao.deleteById(orderModelId) > 0;
    }
}
