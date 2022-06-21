package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.service.IsrpGoodsCategoryFirstService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
@RequestMapping("/t")
public class TestController {

    @Resource
    IsrpGoodsCategoryFirstService isrpGoodsCategoryFirstService;
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/test/{id}")
    public String  test(@PathVariable("id") int id){

        return restTemplate.getForObject("http://isrp-provider:9527/naco/nacos/"+id,String.class);

    }
}
