package com.grouptwo.isrp.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.annotation.RolesAuthorization;
import com.grouptwo.isrp.entity.IsrpUserProp;
import com.grouptwo.isrp.service.IsrpUserPropService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户收货配置表(IsrpUserProp)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpUserProp")
public class IsrpUserPropController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpUserPropService isrpUserPropService;

    /**
     * 分页查询
     *
     * @param isrpUserProp 筛选条件
     * @return 查询结果
     */
    @GetMapping("/queryByPageGetUserProp")
    public ResponseEntity<Page<IsrpUserProp>> queryByPage(IsrpUserProp isrpUserProp ) {
        PageRequest pageRequest = PageRequest.of(0,100);
        return ResponseEntity.ok(this.isrpUserPropService.queryByPage(isrpUserProp, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpUserProp> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.isrpUserPropService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpUserProp 实体
     * @return 新增结果
     */
    @RolesAuthorization
    @PostMapping("/addAddress")
    public ResponseEntity<IsrpUserProp> addAddress(@RequestBody IsrpUserProp isrpUserProp) {
        System.out.println(isrpUserProp);
        isrpUserProp.setUserId((String) getUserInfo().get("userId"));
        return ResponseEntity.ok(this.isrpUserPropService.insert(isrpUserProp));
    }

    /**
     * 编辑数据
     *
     * @param isrpUserProp 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpUserProp> edit(IsrpUserProp isrpUserProp) {
        return ResponseEntity.ok(this.isrpUserPropService.update(isrpUserProp));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteAddress")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.isrpUserPropService.deleteById(id));
    }
    private static JSONObject getUserInfo() {
        JSONObject user = JSONObject.parseObject(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));

        return user;
    }


}

