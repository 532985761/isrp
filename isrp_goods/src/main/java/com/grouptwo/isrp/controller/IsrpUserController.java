package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.service.IsrpUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(IsrpUser)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpUser")
public class IsrpUserController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpUserService isrpUserService;

    /**
     * 分页查询
     *
     * @param isrpUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpUser>> queryByPage(IsrpUser isrpUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpUserService.queryByPage(isrpUser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpUser> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.isrpUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpUser> add(IsrpUser isrpUser) {
        return ResponseEntity.ok(this.isrpUserService.insert(isrpUser));
    }

    /**
     * 编辑数据
     *
     * @param isrpUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpUser> edit(IsrpUser isrpUser) {
        return ResponseEntity.ok(this.isrpUserService.update(isrpUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.isrpUserService.deleteById(id));
    }

}

