package com.grouptwo.isrp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: package-lock.json
 * @description: 添加IsrpOrder实体
 * @author: Wilburn
 * @create: 2022-06-29 21:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsrpOrderProcessPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单模式ID
     */
    private Integer orderModelId;
    /**
     * 订单流程名称
     */
    private String orderProcessName;
}
