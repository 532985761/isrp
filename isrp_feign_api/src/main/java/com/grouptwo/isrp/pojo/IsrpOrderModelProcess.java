package com.grouptwo.isrp.pojo;

import com.grouptwo.isrp.entity.IsrpOrderProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: package-lock.json
 * @description: 订单模式流程
 * @author: Wilburn
 * @create: 2022-06-29 09:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsrpOrderModelProcess implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单模式ID
     */
    private Integer orderModelId;
    /**
     * 订单模式名称
     */
    private String orderModelName;
    /**
     * 流程分页查询
     */
    private List<IsrpOrderProcess> orderProcessList;
}
