package com.grouptwo.isrp.client;

import com.grouptwo.isrp.pojo.LoginFormPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: isrp
 * @description: 授权客户端
 * @author: Wilburn
 * @create: 2022-06-23 16:59
 **/
@Component
@FeignClient("isrp-auth")
public interface AuthClient {

    /**
     * 授权接口
     *
     * @param loginForm
     * @return
     */
    @PostMapping("isrpAuth/isrpAuth/authentication")
    Map authentication(@RequestBody LoginFormPojo loginForm);

}
