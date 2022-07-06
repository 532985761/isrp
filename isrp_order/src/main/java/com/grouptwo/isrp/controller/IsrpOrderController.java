package com.grouptwo.isrp.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.annotation.RolesAuthorization;
import com.grouptwo.isrp.entity.IsrpOrder;
import com.grouptwo.isrp.service.IsrpOrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 商品订单表(IsrpOrder)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:32
 */
@RestController
@RequestMapping("isrpOrder")
public class IsrpOrderController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpOrderService isrpOrderService;

    /**
     * 分页查询
     *
     * @param isrpOrder   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IsrpOrder>> queryByPage(IsrpOrder isrpOrder, PageRequest pageRequest) {
        return ResponseEntity.ok(this.isrpOrderService.queryByPage(isrpOrder, pageRequest));
    }

    /**
     * 通过orderId搜索订单
     *
     * @param orderId 主键
     * @return 单条数据
     */
    @GetMapping("/selectOrderById/{orderId}")
    public ResponseEntity<IsrpOrder> selectOrderById(@PathVariable("orderId") String orderId) {
        System.out.println();
        return ResponseEntity.ok(this.isrpOrderService.selectOrderById(orderId));
    }

    /**
     * 新增数据
     *
     * @param isrpOrder 实体
     * @return 新增结果
     */
    @PostMapping("/insertOrder")
    public ResponseEntity<IsrpOrder> insertOrder(@RequestBody IsrpOrder isrpOrder) {
        return ResponseEntity.ok(this.isrpOrderService.insertOrder(isrpOrder));
    }

    /**
     * 编辑订单数据（确认发货）
     *
     * @param orderId 实体
     * @return 编辑结果
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping("/updateOrder/{orderId}")
    public ResponseEntity<IsrpOrder> updateOrder(@PathVariable("orderId") String orderId) {
        IsrpOrder isrpOrder = new IsrpOrder();
        isrpOrder.setOrderId(orderId);
        return ResponseEntity.ok(this.isrpOrderService.updateOrder(isrpOrder));
    }

    /**
     * 删除订单数据
     *
     * @param orderId 主键
     * @return 删除是否成功
     */
    @PostMapping("/deleteOrder/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(this.isrpOrderService.deleteById(orderId));
    }

    /**
     * 查询所有订单信息
     */
    @GetMapping("/selectAllOrders")
    public ResponseEntity selectAllOrders() {
        return ResponseEntity.ok(this.isrpOrderService.selectAllOrders());
    }

    /**
     * 用户下单
     */
    @PostMapping("/userToOrder")
    public ResponseEntity userToOrder(Integer goodsId) {
        Map<String, Object> map = isrpOrderService.insertOrderByGoodsId(goodsId);
        if (map == null || map.isEmpty()) {
            return new ResponseEntity("下单成功", HttpStatus.OK);
        } else {
            return new ResponseEntity(map.get("msg"), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    /**
     * 通过商家id查询待支付订单
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping("/selectOrderIndexByShopUserId/{shopUserId}")
    public ResponseEntity selectOrderIndexByShopUserId(@PathVariable("shopUserId") String shopUserId) {
        return ResponseEntity.ok(this.isrpOrderService.selectOrderIndexByShopUserId(shopUserId));
    }

    /**
     * 通过商家id 查询已完成订单
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping("/selectOrderFinishByShopUserId/{shopUserId}")
    public ResponseEntity selectOrderFinishByShopUserId(@PathVariable("shopUserId") String shopUserId) {
        return ResponseEntity.ok(this.isrpOrderService.selectOrderFinishByShopUserId(shopUserId));
    }

    /**
     * 通过商家id查询所有订单
     */
    @RolesAuthorization(value = {"business"})
    @GetMapping("/selectOrderAllByShopUserId/{shopUserId}")
    public ResponseEntity selectOrderAllByShopUserId(@PathVariable("shopUserId") String shopUserId) {
        return ResponseEntity.ok(this.isrpOrderService.selectOrderAllByShopUserId(shopUserId));
    }

    /**
     * 加入购物车
     */
    @RolesAuthorization
    @GetMapping("/addToCart/{goodsId}/{days}")
    public ResponseEntity addToCart(@PathVariable("goodsId") Integer goodsId, @PathVariable("days") BigDecimal days) {


        return new ResponseEntity(isrpOrderService.addToCart(goodsId, days), HttpStatus.OK);
    }

    /**
     * 提取购物车商品
     */
    @RolesAuthorization
    @PostMapping("/getCart")
    public ResponseEntity getCart() {

        return new ResponseEntity(isrpOrderService.getCart(), HttpStatus.OK);
    }

    /**
     * 删除购物车项
     */
    @RolesAuthorization
    @GetMapping("/deleteCartByGoodsId/{goodsId}")
    public ResponseEntity deleteCartByGoodsId(@PathVariable("goodsId") Integer goodsId) {

        return new ResponseEntity(isrpOrderService.deleteCartByGoodsId(goodsId), HttpStatus.OK);
    }

    /**
     * 得到订单项信息
     *
     * @param goodsId
     * @return 订单页信息
     */
    @RolesAuthorization
    @GetMapping("/getPreorderInfo/{goodsId}")
    public ResponseEntity getPreorderInfo(@PathVariable("goodsId") Integer goodsId) {
        return new ResponseEntity(isrpOrderService.getPreorderInfo(goodsId), HttpStatus.OK);
    }

    /**
     * 订单生成
     *
     * @param order
     * @return
     */
    @RolesAuthorization
    @PostMapping("/makeOrder")
    public ResponseEntity makeOrder(@RequestBody IsrpOrder order) {
        isrpOrderService.insertOrder(order);
        return new ResponseEntity(order, HttpStatus.OK);
    }

    /**
     * 未支付订单
     */
    @RolesAuthorization
    @GetMapping("/waitPayOrder")
    public ResponseEntity waitPayOrder() {
        System.out.println(getUserInfo().get("userId"));
        return new ResponseEntity(isrpOrderService.waitPayOrder((String) getUserInfo().get("userId"), 0), HttpStatus.OK);
    }

    /**
     * 已支付订单，正在进行中订单
     *
     * @return
     */
    @RolesAuthorization
    @GetMapping("/hasPayOrder")
    public ResponseEntity payOrder() {
        return new ResponseEntity(isrpOrderService.hasPayOrder((String) getUserInfo().get("userId"), 1), HttpStatus.OK);

    }

    /**
     * 订单详情-订单流程当前进度
     *
     * @return
     */
    @RolesAuthorization
    @GetMapping("/getOrderDetail/{orderId}")
    public ResponseEntity getOrderDetail(@PathVariable("orderId") String orderId) {
        return new ResponseEntity(isrpOrderService.getOrderDetail(orderId), HttpStatus.OK);
    }

    /**
     *
     * 续租
     * @return
     */
    @RolesAuthorization
    @GetMapping("/continueOrder/{orderId}")
    public ResponseEntity continueOrder(@PathVariable("orderId") String orderId) {
        return new ResponseEntity(isrpOrderService.continueOrder(orderId), HttpStatus.OK);
    }


    /**
     * 支付订单
     * @return
     */
    @RolesAuthorization
    @GetMapping("/payOrder/{orderId}")
    public ResponseEntity payOrder(@PathVariable("orderId") String orderId){
        return new ResponseEntity(isrpOrderService.payOrder(orderId),HttpStatus.OK);
    }

    /**
     * 退租
     * @return
     */
    @RolesAuthorization
    @GetMapping("/exitOrder/{orderId}")
    public ResponseEntity exitOrder(@PathVariable("orderId") String orderId){
        return new ResponseEntity(isrpOrderService.exitOrder(orderId),HttpStatus.OK);
    }

    private static JSONObject getUserInfo() {
        JSONObject user = JSONObject.parseObject(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return user;
    }
}

