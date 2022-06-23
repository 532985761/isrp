package com.grouptwo.isrp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author 张洪志
 */
@Component
@FeignClient("isrp-order")
public interface TestClient {

    @GetMapping("/isrpOrder/t/test/{id}")
    String test(@PathVariable("id") int id);
}
