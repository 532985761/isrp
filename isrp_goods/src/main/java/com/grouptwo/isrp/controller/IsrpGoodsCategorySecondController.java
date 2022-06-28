package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.entity.IsrpGoodsCategorySecond;
import com.grouptwo.isrp.service.IsrpGoodsCategorySecondService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品二级分类表(IsrpGoodsCategorySecond)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@RestController
@RequestMapping("isrpGoodsCategorySecond")
public class IsrpGoodsCategorySecondController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpGoodsCategorySecondService isrpGoodsCategorySecondService;

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryByPageGoodsCategorySecond")
    public ResponseEntity<Page<IsrpGoodsCategorySecond>> queryByPage(int index,int page,int size) {
        IsrpGoodsCategorySecond isrpGoodsCategorySecond = new IsrpGoodsCategorySecond();
        isrpGoodsCategorySecond.setGoodsCategoryFirstId(index);
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return ResponseEntity.ok(this.isrpGoodsCategorySecondService.queryByPage(isrpGoodsCategorySecond, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IsrpGoodsCategorySecond> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpGoodsCategorySecondService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param isrpGoodsCategorySecond 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IsrpGoodsCategorySecond> add(IsrpGoodsCategorySecond isrpGoodsCategorySecond) {
        return ResponseEntity.ok(this.isrpGoodsCategorySecondService.insert(isrpGoodsCategorySecond));
    }

    /**
     * 编辑数据
     *
     * @param isrpGoodsCategorySecond 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpGoodsCategorySecond> edit(IsrpGoodsCategorySecond isrpGoodsCategorySecond) {
        return ResponseEntity.ok(this.isrpGoodsCategorySecondService.update(isrpGoodsCategorySecond));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.isrpGoodsCategorySecondService.deleteById(id));
    }

}

