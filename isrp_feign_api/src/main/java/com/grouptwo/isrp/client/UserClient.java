package com.grouptwo.isrp.client;

import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.entity.IsrpUserProp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @program: isrp
 * @description: 用户客户端
 * @author: Wilburn
 * @create: 2022-06-22 21:29
 **/
@Component
@FeignClient("isrp-user")
public interface UserClient {

    /**
     * 根据email查询用户信息接口
     *
     * @param email
     * @return
     */
    @GetMapping("/isrpUser/isrpUser/emailInner/{email}")
    Map queryByEmail(@PathVariable("email") String email);

    /**
     * 根据用户ID获取信息
     * @param id
     * @return
     */
    @GetMapping("/isrpUser/isrpUser/{id}")
    Map queryUserById(@PathVariable("id") String id);

    /**
     * 获取用户地址配置信息
     * @param isrpUserProp
     * @return
     */
    @GetMapping("/isrpUser/isrpUserProp/queryByPageGetUserProp")
     Map queryByPage(@RequestBody IsrpUserProp isrpUserProp);
}
