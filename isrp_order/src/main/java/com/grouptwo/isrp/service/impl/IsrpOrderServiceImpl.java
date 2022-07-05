package com.grouptwo.isrp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.client.GoodsClient;
import com.grouptwo.isrp.client.OrderClient;
import com.grouptwo.isrp.client.UserClient;
import com.grouptwo.isrp.dao.IsrpOrderDao;
import com.grouptwo.isrp.entity.*;
import com.grouptwo.isrp.pojo.CartVO;
import com.grouptwo.isrp.pojo.SelectVO;
import com.grouptwo.isrp.service.IsrpLogisticsCompanyService;
import com.grouptwo.isrp.service.IsrpOrderService;
import com.grouptwo.isrp.service.IsrpPaymentTypeService;
import com.grouptwo.isrp.utils.SnowflakeIdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品订单表(IsrpOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpOrderService")
public class IsrpOrderServiceImpl implements IsrpOrderService {
    private final String CART_PREFIX = "isrp:cart:";

    @Resource
    private IsrpOrderDao isrpOrderDao;
    @Resource
    private GoodsClient goodsClient;

    @Resource
    private OrderClient orderClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private IsrpPaymentTypeService isrpPaymentTypeService;
    @Resource
    private UserClient userClient;

    @Resource
    private IsrpLogisticsCompanyService isrpLogisticsCompanyService;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public IsrpOrder selectOrderById(String orderId) {
        return this.isrpOrderDao.selectOrderById(orderId);
    }

    /**
     * 分页查询
     *
     * @param isrpOrder   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpOrder> queryByPage(IsrpOrder isrpOrder, PageRequest pageRequest) {
        long total = this.isrpOrderDao.count(isrpOrder);
        return new PageImpl<>(this.isrpOrderDao.queryAllByLimit(isrpOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrder insertOrder(IsrpOrder isrpOrder) {
        //设置订单表信息
        isrpOrder.setOrderId(String.valueOf(new SnowflakeIdWorker(0L, 0L).nextId()));
        isrpOrder.setCreateTime(LocalDateTime.now());
        //删除购物车项
        deleteCartByGoodsId(Math.toIntExact(isrpOrder.getGoodsId()));
        //改变goods商品状态为不可租
        goodsClient.updateGoodsById(isrpOrder.getGoodsId(),0);
        this.isrpOrderDao.insertOrder(isrpOrder);
        return isrpOrder;
    }

    /**
     * 修改数据（确认发货）
     *
     * @param isrpOrder 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpOrder updateOrder(IsrpOrder isrpOrder) {
        this.isrpOrderDao.updateOrder(isrpOrder);
        IsrpOrder isrpOrder1 = new IsrpOrder();
        return isrpOrder1;
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String orderId) {
        return this.isrpOrderDao.deleteById(orderId) > 0;
    }

    /**
     * 查询所有订单信息
     *
     * @return 查询结果
     */
    @Override
    public List<IsrpOrder> selectAllOrders() {
        return this.isrpOrderDao.selectAllOrders();
    }

    @Override
    public Map<String, Object> insertOrderByGoodsId(Integer goodsId) {
        Map goods = goodsClient.queryByGoodsId(goodsId);

        Map<String, Object> map = new HashMap<>();
        //查询商品是否可以租
//        if (map.get("goods") == 0) {
//            map.put("msg", "商品已被租赁，请选择其他商品");
//            return map;
//        }
        return map;
    }

    /**
     * 通过商家id查询待支付订单
     *
     * @param shopUserId
     * @return
     */
    @Override
    public List<IsrpOrder> selectOrderIndexByShopUserId(String shopUserId) {
        return isrpOrderDao.selectOrderIndexByShopUserId(shopUserId);
    }

    /**
     * 通过商家id查询已完成订单
     *
     * @param shopUserId
     * @return
     */
    @Override
    public List<IsrpOrder> selectOrderFinishByShopUserId(String shopUserId) {
        return isrpOrderDao.selectOrderFinishByShopUserId(shopUserId);
    }

    /**
     * 通过商家id查询所有订单
     *
     * @param shopUserId
     * @return
     */
    @Override
    public List<IsrpOrder> selectOrderAllByShopUserId(String shopUserId) {
        return isrpOrderDao.selectOrderAllByShopUserId(shopUserId);
    }

    @Override
    public Map<String, Object> addToCart(Integer goodsId, BigDecimal days) {
        Map<String, Object> map = new HashMap<>();
        BoundHashOperations<String, Object, Object> operations = getCart(CART_PREFIX);
        //查询商品信息，订单模式信息
        Map goodsMap = goodsClient.queryByGoodsId(goodsId);
        IsrpGoods goods = JSONObject.parseObject(JSONObject.toJSONString(goodsMap), IsrpGoods.class);
        Map model = orderClient.queryIsrpOrderModelById(goods.getOrderModelId());

        JSON.parseObject(JSONObject.toJSONString(model), IsrpOrderModel.class);
        CartVO cartVO = new CartVO();
        //从redis中得到购物车字符串
        String res = (String) operations.get(goodsId.toString());
        //判断当前用户购物车是否存在此件商品，若不存在商品
        if (StringUtils.isEmpty(res)) {
            cartVO.setGoodsId(goodsId);
            cartVO.setGoodsName(goods.getGoodsName());
            cartVO.setRentDays(days);
            cartVO.setRentLimit(goods.getRentLimitDays());
            cartVO.setTotal(days.multiply(goods.getRentPricePerDay()));
            cartVO.setGoodsDesc(goods.getGoodsDesc());
            cartVO.setModal((String) model.get("orderModelName"));
            cartVO.setRentPrice(goods.getRentPricePerDay());
            cartVO.setGoodsImg(goods.getGoodsImg());
            cartVO.setGoodsPrice(goods.getGoodsPrice());
            //存入redis
            operations.put(goodsId.toString(), JSONObject.toJSONString(cartVO));
        } else {
            //购物车存在商品，修改对应的总价和使用天数
            cartVO = JSON.parseObject(res, CartVO.class);
            cartVO.setTotal(days.multiply(goods.getRentPricePerDay()));
            cartVO.setRentDays(days);
            operations.put(goodsId.toString(), JSON.toJSONString(cartVO));

        }
        //获取购物车中所有商品的信息
        String cartInfoKey = CART_PREFIX + getUserInfo().get("userId").toString();
        BoundHashOperations<String, Object, Object> operationsList = stringRedisTemplate.boundHashOps(cartInfoKey);
        List<Object> values = operationsList.values();
        if (values != null) {
            List<CartVO> list = values.stream().map((obj) -> {
                String str = (String) obj;
                return JSON.parseObject(str, CartVO.class);
            }).collect(Collectors.toList());
            map.put("cart", list);
        }
        return map;
    }

    @Override
    public Map<String, Object> getCart() {
        Map<String, Object> map = new HashMap<>();
        String cartInfoKey = CART_PREFIX + getUserInfo().get("userId").toString();
        BoundHashOperations<String, Object, Object> operationsList = stringRedisTemplate.boundHashOps(cartInfoKey);
        List<Object> values = operationsList.values();
        if (values != null) {
            List<CartVO> list = values.stream().map((obj) -> {
                String str = (String) obj;
                return JSON.parseObject(str, CartVO.class);
            }).collect(Collectors.toList());
            map.put("cart", list);
        }
        return map;
    }

    @Override
    public boolean deleteCartByGoodsId(Integer goodsId) {
        String cartInfoKey = CART_PREFIX + getUserInfo().get("userId").toString();
        BoundHashOperations<String, Object, Object> operations = getCart(CART_PREFIX);
        operations.delete(goodsId.toString());
        return true;
    }

    @Override
    public Map<String, Object> getPreorderInfo(Integer goodsId) {
        //获取商品信息
        Map goodsMap = goodsClient.queryByGoodsId(goodsId);
        IsrpGoods goods = JSONObject.parseObject(JSONObject.toJSONString(goodsMap), IsrpGoods.class);
        //获取订单模式信息
        Map modelMap = orderClient.queryIsrpOrderModelById(goods.getOrderModelId());
        IsrpOrderModel model = JSON.parseObject(JSONObject.toJSONString(modelMap), IsrpOrderModel.class);
        //获取支付方式信息
        IsrpPaymentType isrpPaymentType = new IsrpPaymentType();
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<IsrpPaymentType> paymentTypes = isrpPaymentTypeService.queryByPage(isrpPaymentType, pageRequest);
        //获取商品对应商户信息
        Map userMap = userClient.queryUserById(goods.getUserId());
        IsrpUser user = JSONObject.parseObject(JSONObject.toJSONString(userMap), IsrpUser.class);
        //获取收获地址信息
        IsrpUserProp userProp = new IsrpUserProp();
        userProp.setUserId((String) getUserInfo().get("userId"));
        PageRequest pageRequestUserProp = PageRequest.of(0, 100);
        Map userPropMap = userClient.queryByPage(userProp);
        //配送方式
        List<SelectVO> selectVOList = isrpLogisticsCompanyService.getSelectVO();
        //通过goodsId和用户ID获取购物车信息
        BoundHashOperations<String, Object, Object> operations = getCart(CART_PREFIX);
        CartVO cartVO = JSON.parseObject((String) operations.get(goodsId.toString()), CartVO.class);
        System.out.println(cartVO);
        //返回到map
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("goods", goods);
        map.put("model", model);
        map.put("payType", paymentTypes);
        map.put("userProp", userPropMap.get("content"));
        map.put("selectVO", selectVOList);
        map.put("cart", cartVO);

        return map;
    }

    /**
     * 获取购物车
     *
     * @param CART_PREFIX
     * @return
     */
    private BoundHashOperations<String, Object, Object> getCart(String CART_PREFIX) {
        String cartKey = "";
        String userId = getUserInfo().get("userId").toString();
        cartKey = CART_PREFIX + userId;
        //redis操作购物车
        BoundHashOperations<String, Object, Object> operations = stringRedisTemplate.boundHashOps(cartKey);
        return operations;
    }

    private static JSONObject getUserInfo() {
        JSONObject user = JSONObject.parseObject(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));

        return user;
    }

//    @Override
//    public Map<String, Object> getUserAndOrderInfo(String userId) {
//        //查询所有订单后提取goodsId
//
//        //通过订单表goodsId查询goods表，保证唯一性，提升查询速度
//        List<Long> goodsIds = new ArrayList<>();
//        goodsIds.add(1L);
//        goodsIds.add(2L);
//        goodsClient.queryByGoodsIds(goodsIds);
//        //得到上述goods表所有数据，进行数据帅选，筛选条件为--传入的`userId` 和goods表中userId相等--
//
//        //筛选之后得到list列表，此时list数据就是和当前用户相关的订单及订单对应商品信息
//
////         goodsClient.queryByUserId(userId);
//        return new HashMap<>();
//    }
}
