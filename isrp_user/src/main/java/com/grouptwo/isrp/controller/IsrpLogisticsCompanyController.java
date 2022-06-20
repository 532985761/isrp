package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpLogisticsCompany;
import com.grouptwo.isrp.service.IsrpLogisticsCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 物流公司(IsrpLogisticsCompany)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@RestController
@RequestMapping("isrpLogisticsCompany")
public class IsrpLogisticsCompanyController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpLogisticsCompanyService isrpLogisticsCompanyService;

    /**
     * 分页查询
     *
     * @param isrpLogisticsCompany 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpLogisticsCompany>> queryByPage(IsrpLogisticsCompany isrpLogisticsCompany, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpLogisticsCompanyService.queryByPage(isrpLogisticsCompany, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpLogisticsCompany> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpLogisticsCompanyService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpLogisticsCompany 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpLogisticsCompany> add(IsrpLogisticsCompany isrpLogisticsCompany) {
        return ResponseEntity.ok(this.isrpLogisticsCompanyService.insert(isrpLogisticsCompany));
    }

    /**
     * 编辑数据
     *
     * @param isrpLogisticsCompany 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpLogisticsCompany> edit(IsrpLogisticsCompany isrpLogisticsCompany) {
        return ResponseEntity.ok(this.isrpLogisticsCompanyService.update(isrpLogisticsCompany));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpLogisticsCompanyService.deleteById(id));
    }

}

