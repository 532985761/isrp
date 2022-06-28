package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.annotation.RolesAuthorization;
import com.grouptwo.isrp.entity.IsrpGoods;
import com.grouptwo.isrp.entity.IsrpGoodsCategoryFirst;
import com.grouptwo.isrp.entity.IsrpGoodsCategorySecond;
import com.grouptwo.isrp.service.IsrpGoodsCategoryFirstService;
import com.grouptwo.isrp.service.IsrpGoodsCategorySecondService;
import com.grouptwo.isrp.service.IsrpGoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private IsrpGoodsService isrpGoodsService;

    @Resource
    private IsrpGoodsCategoryFirstService isrpGoodsCategoryFirstService;

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

    /**
     * 根据商品二级id获取信息
     */
    @RolesAuthorization
    @GetMapping("/getRentCenterInfoFromGoodsCategoryId")
    public ResponseEntity getRentCenterInfoFromGoodsCategoryId(int firstId,int secondId){
        Map<String,Object> map = new HashMap<>();
        //获取一二级分类信息
        IsrpGoodsCategorySecond isrpGoodsCategorySecond = isrpGoodsCategorySecondService.queryById(secondId);
        map.put("goodsCategorySecond",isrpGoodsCategorySecond);
        IsrpGoodsCategoryFirst isrpGoodsCategoryFirst = isrpGoodsCategoryFirstService.queryById(firstId);
        map.put("goodsCategoryFirst",isrpGoodsCategoryFirst);
        IsrpGoodsCategorySecond goodsFirst = new IsrpGoodsCategorySecond();
        goodsFirst.setGoodsCategoryFirstId(firstId);
        PageRequest pageRequestCategorySecond = PageRequest.of(0,500);
        map.put("allSecondGoods",isrpGoodsCategorySecondService.queryByPage(goodsFirst,pageRequestCategorySecond));
        //找到二级分类下的商品信息
        IsrpGoods goods = new IsrpGoods();
        goods.setGoodsCategorySecondId(secondId);
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<IsrpGoods> page =  isrpGoodsService.queryByPage(goods,pageRequest);
        map.put("page",page);
    return  new ResponseEntity(map, HttpStatus.OK);
    }

}

