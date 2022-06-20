package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpOrderProcess;
import com.grouptwo.isrp.service.IsrpOrderProcessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单流程表(IsrpOrderProcess)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpOrderProcess")
public class IsrpOrderProcessController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpOrderProcessService isrpOrderProcessService;

    /**
     * 分页查询
     *
     * @param isrpOrderProcess 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpOrderProcess>> queryByPage(IsrpOrderProcess isrpOrderProcess, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpOrderProcessService.queryByPage(isrpOrderProcess, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpOrderProcess> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpOrderProcessService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpOrderProcess 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpOrderProcess> add(IsrpOrderProcess isrpOrderProcess) {
        return ResponseEntity.ok(this.isrpOrderProcessService.insert(isrpOrderProcess));
    }

    /**
     * 编辑数据
     *
     * @param isrpOrderProcess 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpOrderProcess> edit(IsrpOrderProcess isrpOrderProcess) {
        return ResponseEntity.ok(this.isrpOrderProcessService.update(isrpOrderProcess));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpOrderProcessService.deleteById(id));
    }

}

