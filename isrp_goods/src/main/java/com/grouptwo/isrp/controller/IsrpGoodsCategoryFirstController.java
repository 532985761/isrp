package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.service.IsrpGoodsCategoryFirstService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品一级分类表(IsrpGoodsCategoryFirst)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@RestController
@RequestMapping("isrpGoodsCategoryFirst")
public class IsrpGoodsCategoryFirstController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpGoodsCategoryFirstService isrpGoodsCategoryFirstService;

    /**
     * 分页查询
     *
     * @param isrpGoodsCategoryFirst 筛选条件
     * @return 查询结果
     */
    @GetMapping("/queryByPageGetGoodsCategoryFirst")
    public ResponseEntity<Page<IsrpGoodsCategoryFirst>> queryByPage(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst,int page,int size) {
      PageRequest pageRequest = PageRequest.of(page-1,size);
        return ResponseEntity.ok(this.isrpGoodsCategoryFirstService.queryByPage(isrpGoodsCategoryFirst, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpGoodsCategoryFirst> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpGoodsCategoryFirstService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpGoodsCategoryFirst 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpGoodsCategoryFirst> add(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst) {
        return ResponseEntity.ok(this.isrpGoodsCategoryFirstService.insert(isrpGoodsCategoryFirst));
    }

    /**
     * 编辑数据
     *
     * @param isrpGoodsCategoryFirst 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpGoodsCategoryFirst> edit(IsrpGoodsCategoryFirst isrpGoodsCategoryFirst) {
        return ResponseEntity.ok(this.isrpGoodsCategoryFirstService.update(isrpGoodsCategoryFirst));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpGoodsCategoryFirstService.deleteById(id));
    }

}

