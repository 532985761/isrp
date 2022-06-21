package com.example.isrp_nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConrtroller {
    @Value("${server.port}")
    String port;
    @GetMapping("/nacos/{id}")
    public String getPort(@PathVariable("id") int id){
        return port+"\t"+id;

    }
}
