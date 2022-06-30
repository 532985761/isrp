package com.grouptwo.isrp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.grouptwo.isrp.dao.IsrpOrderProcessDao;
import com.grouptwo.isrp.entity.IsrpOrderProcess;
import com.grouptwo.isrp.pojo.IsrpOrderProcessPojo;
import com.grouptwo.isrp.service.IsrpOrderProcessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单流程表(IsrpOrderProcess)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpOrderProcessService")
public class IsrpOrderProcessServiceImpl implements IsrpOrderProcessService {
    @Resource
    private IsrpOrderProcessDao isrpOrderProcessDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderProcessId 主键
     * @return 实例对象
     */
    @Override
    public IsrpOrderProcess queryById(Integer orderProcessId) {
        return this.isrpOrderProcessDao.queryById(orderProcessId);
    }

    /**
     * 分页查询
     *
     * @param isrpOrderProcess 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpOrderProcess> queryByPage(IsrpOrderProcess isrpOrderProcess, PageRequest pageRequest) {
        long total = this.isrpOrderProcessDao.count(isrpOrderProcess);
        return new PageImpl<>(this.isrpOrderProcessDao.queryAllByLimit(isrpOrderProcess, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpOrderProcess 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrderProcess insert(IsrpOrderProcess isrpOrderProcess) {
        this.isrpOrderProcessDao.insert(isrpOrderProcess);
        return isrpOrderProcess;
    }

    /**
     * 批量更新数据
     *
     * @param isrpOrderProcessPojoList 实例对象
     * @return
     */
    @Override
    public Integer insertBatch(List<IsrpOrderProcessPojo> isrpOrderProcessPojoList) {
        List<IsrpOrderProcess> isrpOrderProcessList = new ArrayList<>();
        for (IsrpOrderProcessPojo isrpOrderProcessPojo:isrpOrderProcessPojoList) {
            IsrpOrderProcess isrpOrderProcess = new IsrpOrderProcess();
            BeanUtil.copyProperties(isrpOrderProcessPojo, isrpOrderProcess);
            isrpOrderProcess.setOrderProcessId(null);
            isrpOrderProcessList.add(isrpOrderProcess);
        }
        return this.isrpOrderProcessDao.insertBatch(isrpOrderProcessList);
    }

    /**
     * 修改数据
     *
     * @param isrpOrderProcess 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrderProcess update(IsrpOrderProcess isrpOrderProcess) {
        this.isrpOrderProcessDao.update(isrpOrderProcess);
        return this.queryById(isrpOrderProcess.getOrderProcessId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderProcessId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer orderProcessId) {
        return this.isrpOrderProcessDao.deleteById(orderProcessId) > 0;
    }

    @Override
    public Integer deleteByModelId(Integer orderModelId) {
        return this.isrpOrderProcessDao.deleteByModelId(orderModelId);
    }
}
