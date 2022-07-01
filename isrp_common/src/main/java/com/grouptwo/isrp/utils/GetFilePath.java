package com.grouptwo.isrp.utils;

import java.net.URL;

/**
 * @program: common
 * @description: 获取文件在磁盘中的位置
 * @author: Wilburn
 * @create: 2022-07-01 17:02
 **/
public class GetFilePath {
    public String getClassPath() throws Exception {
        try {
            String strClassName = getClass().getName();
            String strPackageName = "";
            if (getClass().getPackage() != null) {
                strPackageName = getClass().getPackage().getName();
            }
            String strclassfilename = "";
            if (!"".equals(strPackageName)) {
                strclassfilename = strClassName.substring(strPackageName.length() + 1,
                        strClassName.length());
            } else {
                strclassfilename = strClassName;
            }
            URL url = null;
            url = getClass().getResource(strclassfilename + ".class");
            String strurl = url.toString();
            strurl = strurl.substring(strurl.indexOf('/') + 1, strurl
                    .lastIndexOf('/'));
            //返回当前类的路径，并且处理路径中的空格，因为在路径中出现的空格如果不处理的话，
            //在访问时就会从空格处断开，那么也就取不到完整的信息了，这个问题在web开发中尤其要注意
            return strurl.replaceAll("%20", " ");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
