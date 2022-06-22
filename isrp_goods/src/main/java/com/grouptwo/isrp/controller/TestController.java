package com.grouptwo.isrp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/t")
public class TestController {


    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}
