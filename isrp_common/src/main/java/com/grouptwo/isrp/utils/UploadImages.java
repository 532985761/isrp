package com.grouptwo.isrp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @program: common
 * @description: 上传图片
 * @author: Wilburn
 * @create: 2022-06-30 20:27
 **/
@Component
public class UploadImages {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${spring.application.name}")
    private String applicationName;

    public UploadImages() throws Exception {
    }

    public String uploadImages(MultipartFile[] files, HttpServletRequest request) throws Exception {
        String realPath = (new GetFilePath().getClassPath().substring(0, new GetFilePath().getClassPath().indexOf("isrp_common")) + applicationName.replaceAll("-", "_") + "/src/main/resources/static/images/").replaceAll("/", "\\\\\\\\");
        String path = "";
        File folder = new File(realPath);
        for (MultipartFile file : files) {
            //获取上传文件的源文件名称
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            //判断目录是否存在，不存在则创建目录
            if (!folder.exists()) {
                folder.mkdirs();
            }
            file.transferTo(new File(folder, newName));
            path = path + "http://" + request.getLocalAddr() + ":9527" + contextPath + "/file/images/" + newName;
            path += ",";
        }
        path = path.substring(0, path.length() - 1);
        return path;
    }


}
