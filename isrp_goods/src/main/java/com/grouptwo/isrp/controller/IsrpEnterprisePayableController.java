package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpEnterprisePayable;
import com.grouptwo.isrp.service.IsrpEnterprisePayableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商家收款信息表(IsrpEnterprisePayable)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:28
 */
@RestController
@RequestMapping("isrpEnterprisePayable")
public class IsrpEnterprisePayableController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpEnterprisePayableService isrpEnterprisePayableService;

    /**
     * 分页查询
     *
     * @param isrpEnterprisePayable 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpEnterprisePayable>> queryByPage(IsrpEnterprisePayable isrpEnterprisePayable, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpEnterprisePayableService.queryByPage(isrpEnterprisePayable, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpEnterprisePayable> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpEnterprisePayableService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpEnterprisePayable 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpEnterprisePayable> add(IsrpEnterprisePayable isrpEnterprisePayable) {
        return ResponseEntity.ok(this.isrpEnterprisePayableService.insert(isrpEnterprisePayable));
    }

    /**
     * 编辑数据
     *
     * @param isrpEnterprisePayable 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpEnterprisePayable> edit(IsrpEnterprisePayable isrpEnterprisePayable) {
        return ResponseEntity.ok(this.isrpEnterprisePayableService.update(isrpEnterprisePayable));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpEnterprisePayableService.deleteById(id));
    }

}

