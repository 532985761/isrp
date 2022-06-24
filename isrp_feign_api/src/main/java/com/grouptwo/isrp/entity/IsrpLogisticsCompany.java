package com.grouptwo.isrp.entity;

import java.io.Serializable;

/**
 * 物流公司(IsrpLogisticsCompany)实体类
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
public class IsrpLogisticsCompany implements Serializable {
    private static final long serialVersionUID = 567101322145591717L;
    /**
     * 物流公司编号
     */
    private Integer logisticsCompanyId;
    /**
     * 物流公司名称
     */
    private String logisticsCompanyName;


    public Integer getLogisticsCompanyId() {
        return logisticsCompanyId;
    }

    public void setLogisticsCompanyId(Integer logisticsCompanyId) {
        this.logisticsCompanyId = logisticsCompanyId;
    }

    public String getLogisticsCompanyName() {
        return logisticsCompanyName;
    }

    public void setLogisticsCompanyName(String logisticsCompanyName) {
        this.logisticsCompanyName = logisticsCompanyName;
    }

}

