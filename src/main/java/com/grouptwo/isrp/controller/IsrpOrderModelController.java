package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpOrderModel;
import com.grouptwo.isrp.service.IsrpOrderModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单模式表(IsrpOrderModel)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpOrderModel")
public class IsrpOrderModelController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpOrderModelService isrpOrderModelService;

    /**
     * 分页查询
     *
     * @param isrpOrderModel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpOrderModel>> queryByPage(IsrpOrderModel isrpOrderModel, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpOrderModelService.queryByPage(isrpOrderModel, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpOrderModel> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpOrderModelService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpOrderModel 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpOrderModel> add(IsrpOrderModel isrpOrderModel) {
        return ResponseEntity.ok(this.isrpOrderModelService.insert(isrpOrderModel));
    }

    /**
     * 编辑数据
     *
     * @param isrpOrderModel 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpOrderModel> edit(IsrpOrderModel isrpOrderModel) {
        return ResponseEntity.ok(this.isrpOrderModelService.update(isrpOrderModel));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpOrderModelService.deleteById(id));
    }

}

