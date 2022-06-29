package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.annotation.RolesAuthorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/t")
public class TestController {


    @RolesAuthorization(value = {"user", "manager"})
    @GetMapping("/test")
    public String test(){
        return "ok";
    }


}
