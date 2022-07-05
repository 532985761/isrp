package com.grouptwo.isrp.client;

import com.grouptwo.isrp.entity.IsrpGoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author 张洪志
 */
@Component
@FeignClient("isrp-goods")
public interface GoodsClient {
    @GetMapping("/isrpGoods/isrpGoods/{goodsId}")
    Map queryByGoodsId(@PathVariable("goodsId") Integer goodsId);

    @GetMapping("/isrpGoods/isrpGoods/updateGoodsById/{goodsId}/{status}")
    void updateGoodsById(@PathVariable("goodsId") Long goodsId,@PathVariable("status") int status);

}
