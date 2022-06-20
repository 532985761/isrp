package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpPaymentType;
import com.grouptwo.isrp.service.IsrpPaymentTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 支付方式表(IsrpPaymentType)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpPaymentType")
public class IsrpPaymentTypeController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpPaymentTypeService isrpPaymentTypeService;

    /**
     * 分页查询
     *
     * @param isrpPaymentType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpPaymentType>> queryByPage(IsrpPaymentType isrpPaymentType, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpPaymentTypeService.queryByPage(isrpPaymentType, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpPaymentType> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpPaymentTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpPaymentType 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpPaymentType> add(IsrpPaymentType isrpPaymentType) {
        return ResponseEntity.ok(this.isrpPaymentTypeService.insert(isrpPaymentType));
    }

    /**
     * 编辑数据
     *
     * @param isrpPaymentType 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpPaymentType> edit(IsrpPaymentType isrpPaymentType) {
        return ResponseEntity.ok(this.isrpPaymentTypeService.update(isrpPaymentType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpPaymentTypeService.deleteById(id));
    }

}

