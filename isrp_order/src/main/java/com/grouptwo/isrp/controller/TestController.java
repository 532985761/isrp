package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.entity.IsrpOrder;
import com.grouptwo.isrp.service.IsrpOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @author 张洪志
 */
@RestController
@RequestMapping("/t")
public class TestController {

    @Resource
    private IsrpOrderService isrpOrderService;

    @GetMapping("/test")
    public String  test(){
        return "ok";
    }


    @GetMapping("/test/{id}")
    public ResponseEntity  testClient(@PathVariable("id") int id){
        System.out.println(id);
        System.out.println(isrpOrderService.selectOrderById(String.valueOf(id)));
        return ResponseEntity.ok(isrpOrderService.selectOrderById(String.valueOf(id)));
    }



}
