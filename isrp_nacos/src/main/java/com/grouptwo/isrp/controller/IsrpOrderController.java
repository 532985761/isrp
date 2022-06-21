package com.grouptwo.isrp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsrpOrderController {
    @Value("${server.port")
    private String serverPort;

    @GetMapping("/order/nacos/{id}")
    public String getOrder(@PathVariable("id") Integer id){
        return serverPort+"\t id" + id;
    }
}
