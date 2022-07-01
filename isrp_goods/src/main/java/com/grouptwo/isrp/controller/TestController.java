package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.utils.UploadImages;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("/t")
public class TestController {

    @Resource
    private UploadImages uploadImages;

    @GetMapping("/test")
    public String test(){
        return "ok";
    }

    @PostMapping("/testImage")
    public String testImage(@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {

        return uploadImages.uploadImages(files, request);
    }

}
