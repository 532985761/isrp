package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.service.IsrpUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class MailController {
    @Resource
    private IsrpUserService isrpUserService;
    @GetMapping("/activation/{userId}")
    public String activation(@PathVariable("userId") String userId, Model model){
       int status = isrpUserService.getUserStatus(userId);
       if (status == 1){
           model.addAttribute("msg","不允许重复激活！！！");
           model.addAttribute("href","http://localhost:3000/");
       }else {
           isrpUserService.changeUserStatus(userId);
           model.addAttribute("msg","账户已经激活成功");
           model.addAttribute("href","http://localhost:3000/");
       }
        return "/mail/operate-result";
    }
}
