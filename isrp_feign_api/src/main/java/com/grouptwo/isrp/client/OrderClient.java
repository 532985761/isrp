package com.grouptwo.isrp.client;

import com.grouptwo.isrp.entity.IsrpOrderModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author 张洪志
 */
@Component
@FeignClient("isrp-order")
public interface OrderClient {

    /**
     * 获取订单模式信息
     * @param id
     * @return
     */
    @GetMapping("/isrpOrder/isrpOrderModel/{id}")
    Map queryIsrpOrderModelById(@PathVariable("id") Integer id);
}
