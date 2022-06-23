package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
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



    @GetMapping("/test")
    public String  test(){
        return "ok";
    }


    @GetMapping("/test/{id}")
    public String  testClient(@PathVariable("id") int id){
        return "当前访问的ID是\t"+id;
    }



}
