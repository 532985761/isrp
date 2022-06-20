package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpOrderStatus;
import com.grouptwo.isrp.service.IsrpOrderStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单状态表(IsrpOrderStatus)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpOrderStatus")
public class IsrpOrderStatusController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpOrderStatusService isrpOrderStatusService;

    /**
     * 分页查询
     *
     * @param isrpOrderStatus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpOrderStatus>> queryByPage(IsrpOrderStatus isrpOrderStatus, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpOrderStatusService.queryByPage(isrpOrderStatus, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpOrderStatus> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpOrderStatusService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpOrderStatus 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpOrderStatus> add(IsrpOrderStatus isrpOrderStatus) {
        return ResponseEntity.ok(this.isrpOrderStatusService.insert(isrpOrderStatus));
    }

    /**
     * 编辑数据
     *
     * @param isrpOrderStatus 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpOrderStatus> edit(IsrpOrderStatus isrpOrderStatus) {
        return ResponseEntity.ok(this.isrpOrderStatusService.update(isrpOrderStatus));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpOrderStatusService.deleteById(id));
    }

}

