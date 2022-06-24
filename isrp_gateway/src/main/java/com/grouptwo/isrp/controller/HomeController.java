package com.grouptwo.isrp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张洪志
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "网站正在建设中";
    }

}
