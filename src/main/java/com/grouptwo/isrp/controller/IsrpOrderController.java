package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpOrder;
import com.grouptwo.isrp.service.IsrpOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品订单表(IsrpOrder)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@RestController
@RequestMapping("isrpOrder")
public class IsrpOrderController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpOrderService isrpOrderService;

    /**
     * 分页查询
     *
     * @param isrpOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpOrder>> queryByPage(IsrpOrder isrpOrder, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpOrderService.queryByPage(isrpOrder, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpOrder> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.isrpOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpOrder> add(IsrpOrder isrpOrder) {
        return ResponseEntity.ok(this.isrpOrderService.insert(isrpOrder));
    }

    /**
     * 编辑数据
     *
     * @param isrpOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpOrder> edit(IsrpOrder isrpOrder) {
        return ResponseEntity.ok(this.isrpOrderService.update(isrpOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.isrpOrderService.deleteById(id));
    }

}

