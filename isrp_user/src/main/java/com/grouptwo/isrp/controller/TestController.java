package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.client.TestClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/t")
public class TestController {



    @Resource
    TestClient testClient;

    @GetMapping("/test")
    public String test(){

        return "ok";
    }
    @GetMapping("/testfeign/{id}")
    public String testFeign(@PathVariable("id") int id){
         return    testClient.test(id);
    }
}
