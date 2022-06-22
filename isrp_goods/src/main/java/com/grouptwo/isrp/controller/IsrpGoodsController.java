package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoods;
import com.grouptwo.isrp.service.IsrpGoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品表(IsrpGoods)表控制层
 *
 * @author makejava
 * @since 2022-06-22 09:50:07
 */
@RestController
@RequestMapping("isrpGoods")
public class IsrpGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpGoodsService isrpGoodsService;

    /**
     * 分页查询
     *
     * @param isrpGoods 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpGoods>> queryByPage(IsrpGoods isrpGoods, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpGoodsService.queryByPage(isrpGoods, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpGoods> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.isrpGoodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpGoods 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpGoods> add(IsrpGoods isrpGoods) {
        return ResponseEntity.ok(this.isrpGoodsService.insert(isrpGoods));
    }

    /**
     * 编辑数据
     *
     * @param isrpGoods 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpGoods> edit(IsrpGoods isrpGoods) {
        return ResponseEntity.ok(this.isrpGoodsService.update(isrpGoods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.isrpGoodsService.deleteById(id));
    }

}

