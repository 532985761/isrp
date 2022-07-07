package com.grouptwo.isrp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.client.OrderClient;
import com.grouptwo.isrp.client.UserClient;
import com.grouptwo.isrp.entity.*;
import com.grouptwo.isrp.dao.IsrpGoodsDao;
import com.grouptwo.isrp.pojo.AddGoodsPO;
import com.grouptwo.isrp.service.IsrpGoodsCategoryFirstService;
import com.grouptwo.isrp.service.IsrpGoodsCategorySecondService;
import com.grouptwo.isrp.service.IsrpGoodsService;
import com.grouptwo.isrp.utils.UploadImages;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品表(IsrpGoods)表服务实现类
 *
 * @author makejava
 * @since 2022-06-22 10:05:16
 */
@Service("isrpGoodsService")
public class IsrpGoodsServiceImpl implements IsrpGoodsService {
    @Resource
    private IsrpGoodsDao isrpGoodsDao;
    @Resource
    private IsrpGoodsCategorySecondService isrpGoodsCategorySecondService;

    @Resource
    private UserClient userClient;
    @Resource
    private OrderClient orderClient;
    @Resource
    private UploadImages uploadImages;
    @Resource
    private IsrpGoodsService isrpGoodsService;

    @Resource
    private IsrpGoodsCategoryFirstService isrpGoodsCategoryFirstService;

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    @Override
    public IsrpGoods queryById(Long goodsId) {
        return this.isrpGoodsDao.queryById(goodsId);
    }

    /**
     * 分页查询
     *
     * @param isrpGoods   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpGoods> queryByPage(IsrpGoods isrpGoods, PageRequest pageRequest) {
        long total = this.isrpGoodsDao.count(isrpGoods);
        return new PageImpl<>(this.isrpGoodsDao.queryAllByLimit(isrpGoods, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpGoods 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoods insert(IsrpGoods isrpGoods) {
        this.isrpGoodsDao.insert(isrpGoods);
        return isrpGoods;
    }

    /**
     * 修改数据
     *
     * @param isrpGoods 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpGoods update(IsrpGoods isrpGoods) {
        this.isrpGoodsDao.update(isrpGoods);
        return this.queryById(isrpGoods.getGoodsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long goodsId) {
        return this.isrpGoodsDao.deleteById(goodsId) > 0;
    }

    /**
     * 查询商品信息
     *
     * @return
     */

    @Override
    public List<IsrpGoods> queryAllGoods() {
        return this.isrpGoodsDao.queryAllGoods();
    }

    /**
     * 查询所有商品信息
     *
     * @return
     */
    @Override
    public List<IsrpGoods> selectGoods() {
        return isrpGoodsDao.queryAllGoods();
    }

    /**
     * 通过userId查询商品信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<IsrpGoods> selectGoodsByUserId(String userId) {
        return isrpGoodsDao.selectGoodsByUserId(userId);
    }

    @Override
    public Map<String, Object> getGoodsInfo(int firstId, int secondId) {
        Map<String, Object> map = new HashMap<>();
        //获取一二级分类信息
        String secondName = isrpGoodsCategorySecondService.queryById(secondId).getGoodsCategorySecondName();
        map.put("goodsCategorySecond", secondName);
        String firstName = isrpGoodsCategoryFirstService.queryById(firstId).getGoodsCategoryFirstName();
        map.put("goodsCategoryFirst", firstName);
        //根据一级分类查二级分类
        List<IsrpGoodsCategorySecond> goodsSecondList = isrpGoodsCategorySecondService.getGoodsCategorySecondByFirstId(firstId);
        map.put("secondList", goodsSecondList);
        //找到二级分类下的商品信息
        IsrpGoods goods = new IsrpGoods();
        goods.setGoodsCategorySecondId(secondId);
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<IsrpGoods> page = isrpGoodsService.queryByPage(goods, pageRequest);
        //根据商品ID查询用户及商品模式
        List<Long> goodsId = page.stream().map(IsrpGoods::getGoodsId).collect(Collectors.toList());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Long id : goodsId) {
            Map<String, Object> goodsDetail = new HashMap<>();
            goodsDetail.put("goods", isrpGoodsService.queryById(id));
            goodsDetail.put("user", JSON.parseObject(JSON.toJSONString(userClient.queryUserById(isrpGoodsService.queryById(id).getUserId())), IsrpUser.class));
            goodsDetail.put("goodsModal", JSON.parseObject(JSON.toJSONString(orderClient.queryIsrpOrderModelById(isrpGoodsService.queryById(id).getOrderModelId())), IsrpOrderModel.class));
            list.add(goodsDetail);
        }

        map.put("goodsInfo", list);
        return map;
    }

    /**
     * 搜索商品信息
     *
     * @param goodsName
     * @return 商品信息列表
     */
    @Override
    public List<IsrpGoods> selectGoodsByGoodsName(String goodsName) {
        return isrpGoodsDao.selectGoodsByGoodsName(goodsName);
    }

    @Override
    public Map<String, Object> getGoodsDetailsByGoodsId(Long id) {
        Map<String, Object> map = new HashMap<>();
        IsrpGoodsCategorySecond isrpGoodsCategorySecond = isrpGoodsCategorySecondService.queryById(isrpGoodsService.queryById(id)
                .getGoodsCategorySecondId());
        map.put("goodsCategorySecond", isrpGoodsCategorySecond.getGoodsCategorySecondName());
        IsrpGoodsCategoryFirst isrpGoodsCategoryFirst = isrpGoodsCategoryFirstService.queryById(isrpGoodsCategorySecond.getGoodsCategoryFirstId());
        map.put("goodsCategoryFirst", isrpGoodsCategoryFirst.getGoodsCategoryFirstName());
        map.put("user", userClient.queryUserById(isrpGoodsService.queryById(id).getUserId()));
        map.put("goods", isrpGoodsService.queryById(id));
        map.put("model", orderClient.queryIsrpOrderModelById(isrpGoodsService.queryById(id).getOrderModelId()));
        return map;
    }

    @Override
    public void updateGoodsById(Long goodsId,int status) {
        isrpGoodsDao.updateGoodsById(goodsId,status);
    }

    /**
     * 上传商品
     * @param goodsPO
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public IsrpGoods insertGoods(AddGoodsPO goodsPO, HttpServletRequest request) throws Exception {

        IsrpGoods isrpGoods = new IsrpGoods();
        BeanUtil.copyProperties(goodsPO,isrpGoods);
        isrpGoods.setGoodsImg(uploadImages.uploadImages(goodsPO.getGoodsImg(),request));
        isrpGoods.setCreateTime(LocalDateTime.now());
        return isrpGoodsService.insert(isrpGoods);
    }

    /**
     * 修改商品
     * @param goodsPO
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public IsrpGoods updateGoods(AddGoodsPO goodsPO, HttpServletRequest request) throws Exception {
        IsrpGoods isrpGoods = new IsrpGoods();
        BeanUtil.copyProperties(goodsPO,isrpGoods);
        isrpGoods.setGoodsImg(uploadImages.uploadImages(goodsPO.getGoodsImg(),request));
        return isrpGoodsService.update(isrpGoods);
    }
}
