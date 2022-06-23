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
 * @since 2022-06-22 10:05:10
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
     * 查询所有商品
     *
     * @return 查询结果
     */
    @GetMapping("/selectAllGoods")
    public ResponseEntity selectGoods() {
        System.out.println(isrpGoodsService.selectGoods());
        return ResponseEntity.ok(this.isrpGoodsService.selectGoods());
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
     * 新增商品
     *
     * @param isrpGoods 实体
     * @return 新增结果
     */
    @PostMapping("/insertGoods")
    public ResponseEntity<IsrpGoods> add(@RequestBody IsrpGoods isrpGoods) {
        return ResponseEntity.ok(this.isrpGoodsService.insert(isrpGoods));
    }

    /**
     * 修改商品数据
     *
     * @param isrpGoods 实体
     * @return 编辑结果
     */
    @PostMapping("/editGoods")
    public ResponseEntity<IsrpGoods> edit(@RequestBody IsrpGoods isrpGoods) {
        System.out.println(isrpGoods);
        return ResponseEntity.ok(this.isrpGoodsService.update(isrpGoods));
    }

    /**
     * 通过商品id删除商品
     *
     * @param goodsId 主键
     * @return 删除是否成功
     */
    @GetMapping("/deleteGoodsById/{goodsId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("goodsId") Long goodsId) {
        return ResponseEntity.ok(this.isrpGoodsService.deleteById(goodsId));
    }

}

