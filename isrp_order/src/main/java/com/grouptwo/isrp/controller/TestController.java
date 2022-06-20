package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.service.IsrpGoodsCategoryFirstService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/t")
public class TestController {

    @Resource
    IsrpGoodsCategoryFirstService isrpGoodsCategoryFirstService;
    @GetMapping("/test")
    public ResponseEntity<IsrpGoodsCategoryFirst> test(){

        return ResponseEntity.ok(this.isrpGoodsCategoryFirstService.queryById(1));
    }
}
