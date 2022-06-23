package com.grouptwo.isrp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author 张洪志
 */
@Component
@FeignClient("isrp-goods")
public interface IsrpGoodsClient {
}
