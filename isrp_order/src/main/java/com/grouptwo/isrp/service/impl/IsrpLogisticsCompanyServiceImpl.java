package com.grouptwo.isrp.service.impl;

import com.grouptwo.isrp.dao.IsrpLogisticsCompanyDao;
import com.grouptwo.isrp.entity.IsrpLogisticsCompany;
import com.grouptwo.isrp.pojo.SelectVO;
import com.grouptwo.isrp.service.IsrpLogisticsCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物流公司(IsrpLogisticsCompany)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@Service("isrpLogisticsCompanyService")
public class IsrpLogisticsCompanyServiceImpl implements IsrpLogisticsCompanyService {
    @Resource
    private IsrpLogisticsCompanyDao isrpLogisticsCompanyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param logisticsCompanyId 主键
     * @return 实例对象
     */
    @Override
    public IsrpLogisticsCompany queryById(Integer logisticsCompanyId) {
        return this.isrpLogisticsCompanyDao.queryById(logisticsCompanyId);
    }

    /**
     * 分页查询
     *
     * @param isrpLogisticsCompany 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpLogisticsCompany> queryByPage(IsrpLogisticsCompany isrpLogisticsCompany, PageRequest pageRequest) {
        long total = this.isrpLogisticsCompanyDao.count(isrpLogisticsCompany);
        return new PageImpl<>(this.isrpLogisticsCompanyDao.queryAllByLimit(isrpLogisticsCompany, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpLogisticsCompany 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpLogisticsCompany insert(IsrpLogisticsCompany isrpLogisticsCompany) {
        this.isrpLogisticsCompanyDao.insert(isrpLogisticsCompany);
        return isrpLogisticsCompany;
    }

    /**
     * 修改数据
     *
     * @param isrpLogisticsCompany 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpLogisticsCompany update(IsrpLogisticsCompany isrpLogisticsCompany) {
        this.isrpLogisticsCompanyDao.update(isrpLogisticsCompany);
        return this.queryById(isrpLogisticsCompany.getLogisticsCompanyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logisticsCompanyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer logisticsCompanyId) {
        return this.isrpLogisticsCompanyDao.deleteById(logisticsCompanyId) > 0;
    }

    /**
     * 前端select列表
     * @return
     */
    @Override
    public List<SelectVO> getSelectVO() {
        return isrpLogisticsCompanyDao.getSelectVO();
    }
}
