package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.annotation.RolesAuthorization;
import com.grouptwo.isrp.client.OrderClient;
import com.grouptwo.isrp.client.UserClient;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private IsrpGoodsCategorySecondService isrpGoodsCategorySecondService;

    @Resource
    private UserClient userClient;
    @Resource
    private OrderClient orderClient;

    @Resource
    private IsrpGoodsCategoryFirstService isrpGoodsCategoryFirstService;

    /**
     * 分页查询
     *
     * @param isrpGoods 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping
    public ResponseEntity<Page<IsrpGoods>> queryByPage(IsrpGoods isrpGoods, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpGoodsService.queryByPage(isrpGoods, pageRequest));
    }
    /**
     * 查询所有商品
     *
     * @return 查询结果
     */
    @RolesAuthorization(value = {"business"})
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
    @RolesAuthorization(value = {"business"})
    @GetMapping("{id}")
    public ResponseEntity<IsrpGoods> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.isrpGoodsService.queryById(id));
    }
//    @GetMapping("/getGoods/{goodsIds}")
//    public ResponseEntity getGoods(@PathVariable("goodsIds") List<Long> goodsIds){
//        System.out.println(goodsIds);
//        return new ResponseEntity("ok",HttpStatus.OK);
//    }
    /**
     * 新增商品
     *
     * @param isrpGoods 实体
     * @return 新增结果
     */
    @RolesAuthorization(value = {"business"})
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
    @RolesAuthorization(value = {"business"})
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
    @RolesAuthorization(value = {"business"})
    @GetMapping("/deleteGoodsById/{goodsId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("goodsId") Long goodsId) {
        return ResponseEntity.ok(this.isrpGoodsService.deleteById(goodsId));
    }

    /**
     * 获取所有商品信息
     *
     */
    @RolesAuthorization(value = {"business"})
    @PostMapping("/getGoodsInfo")
    public ResponseEntity getGoodsInfo(){
        System.out.println(isrpGoodsService.queryAllGoods());
        return ResponseEntity.ok(isrpGoodsService.queryAllGoods());
    }

    /**
     * 通过userId查询商品信息
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping("/selectGoodsByUserId/{userId}")
    public ResponseEntity selectGoodsByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.isrpGoodsService.selectGoodsByUserId(userId));
    }
    @RolesAuthorization
    @GetMapping("/getRentCenterInfoFromGoodsCategoryId")
    public ResponseEntity getRentCenterInfoFromGoodsCategoryId(int firstId,int secondId){
        Map<String,Object> list = isrpGoodsService.getGoodsInfo(firstId,secondId);

        return  new ResponseEntity(list, HttpStatus.OK);
    }
    /**
     * 搜索商品列表
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping("/selectGoodsByGoodsName/{goodsName}")
    public ResponseEntity selectGoodsByGoodsName(@PathVariable( "goodsName" ) String goodsName){
        return ResponseEntity.ok(this.isrpGoodsService.selectGoodsByGoodsName(goodsName));
    }
}

