<template>
  <!-- 面包屑导航 -->
  <el-row :gutter="10">
    <el-col :lg="2"></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-4">
      <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item :to="{ path: '/isrpUser/index' }">
          首页</el-breadcrumb-item
        >
        <el-breadcrumb-item>租赁中心</el-breadcrumb-item>
        <el-breadcrumb-item>商品详情</el-breadcrumb-item>

        <el-breadcrumb-item>{{ goods.goodsCategoryFirst }}</el-breadcrumb-item>
        <el-breadcrumb-item>{{ goods.goodsCategorySecond }}</el-breadcrumb-item>
        <el-breadcrumb-item>{{ goods.goods.goodsName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
  </el-row>

  <el-row :gutter="10">
    <el-col :lg="2"></el-col>
    <el-col :lg="20" v-loading="loading">
      <!-- 商品详情卡片 -->
      <el-card class="mt-4 h-350px">
        <el-row
          ><el-col :lg="8">
            <el-carousel
              indicator-position="outside "
              height="413px"
              width="550px"
            >
              <el-carousel-item
                v-for="item in goods.goods.goodsImg.toString().split(',')"
                :key="item"
              >
                <el-image :src="item"></el-image>
              </el-carousel-item>
            </el-carousel>
          </el-col>
          <el-col :lg="1"></el-col>
          <el-col :lg="15">
            <div style="font-weight: bold">
              <span> 商品名称：{{ goods.goods.goodsName }}</span>
              <el-button
                @click="$router.go(-1)"
                style="float: right"
                type="info"
                >返回上一页</el-button
              >
            </div>
            <div class="mt-4">
              <span> 商品描述：{{ goods.goods.goodsDesc }}</span>
            </div>
            <el-descriptions border align="center" class="mt-3">
              <el-descriptions-item label="商户姓名" align="center">{{
                goods.user.nickname
              }}</el-descriptions-item>
              <el-descriptions-item label="商家联系方式" align="center"
                ><el-button type="primary"
                  >发送私信</el-button
                ></el-descriptions-item
              >
              <el-descriptions-item label="地区" align="center">{{
                goods.user.addressCity
              }}</el-descriptions-item>
              <el-descriptions-item label="租赁方式" align="center">
                <el-tag size="small">{{ goods.model.orderModelName }}</el-tag>
              </el-descriptions-item>

              <el-descriptions-item label="租赁价格" align="center"
                >{{ goods.goods.rentPricePerDay }}元/天</el-descriptions-item
              >
              <!-- 以租代售 -->
              <el-descriptions-item
                label="商品总价"
                align="center"
                v-if="goods.model.orderModelId !== 13"
              >
                <el-tag type="warning">{{ goods.goods.goodsPrice }}元</el-tag>
              </el-descriptions-item>

              <el-descriptions-item
                label="租赁时间上限"
                align="center"
                v-if="goods.model.orderModelId !== 13"
              >
                <el-tag type="danger" size="large"
                  >{{ goods.goods.rentLimitDays }}天</el-tag
                >
              </el-descriptions-item>
            </el-descriptions>

            <div class="mt-4" style="text-align: center">
              <el-button
                class="w-350px h-50px"
                type="warning"
                v-show="!showButton"
                @click="addToCartClick(goods.goods.goodsId, 1)"
                v-if="goods.goods.goodsStatus == 1"
                >加入购物车</el-button
              >
              <el-button
                class="w-350px h-50px"
                type="info"
                v-show="!showButton"
                disabled
                v-if="goods.goods.goodsStatus == 0"
                >商品不可租,正在出租中</el-button
              >
              <el-button
                v-show="showButton"
                class="w-350px h-50px"
                type="info"
                disabled="disabled"
                @click="addToCartClick(goods.goods.goodsId, 1)"
                >商品已加入购物车</el-button
              >
              <!-- <el-button
                class="w-350px h-50px"
                type="danger"
                v-show="!showButton"
                >立即下单</el-button
              > -->

              <router-link to="/isrpUser/userCart">
                <el-button
                  class="w-350px h-50px bg-sky-400 ml-20px"
                  v-show="showButton"
                  >前往购物车</el-button
                ></router-link
              >
            </div>
          </el-col></el-row
        >
      </el-card>
    </el-col>
  </el-row>
  <el-row>
    <el-col :lg="2"></el-col>
    <el-col :lg="20"
      ><el-card class="mt-4">
        <el-tabs type="card" class="demo-tabs">
          <el-tab-pane
            label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品介绍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
            name="first"
            >商品介绍</el-tab-pane
          >
          <el-tab-pane
            label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;租用评价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
            name="second"
            class="ml-4"
            >租用评价</el-tab-pane
          >
        </el-tabs></el-card
      ></el-col
    >
  </el-row>
</template>
<script lang="ts" setup>
import { ArrowRight } from "@element-plus/icons-vue";
import router from "@/router";
import { onBeforeMount, ref } from "vue";
import { getGoodsDetailsByGoodsId } from "@/api/goods";
import { changeCartInfo, getCartInfo } from "@/api/order";
import { ElNotification } from "element-plus";

const src =
  "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg";
const loading = ref(true);
const showButton = ref(false);

const goods: any = ref({
  goodsCategoryFirst: "数码产品",
  goodsCategorySecond: "对讲机",
  goods: {
    goodsId: 1,
    goodsCategorySecondId: 2,
    userId: "431188825079808",
    goodsName: "p30",
    goodsDesc: "比基尼",
    goodsImg: "未知",
    goodsPrice: 5000,
    goodsSaleCount: 20,
    orderModelId: 1,
    goodsStatus: 1,
    createTime: "2022-06-22 09:58:58",
    rentLimitDays: null,
    rentPricePerDay: null,
  },
  model: { orderModelId: 1, orderModelName: "先租后买" },
  user: {
    userId: "431188825079808",
    nickname: "wbw",
    headerImg:
      "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
    password: null,
    role: 1,
    phone: "未知",
    email: "wbw@163.com",
    idCardNum: "未知",
    sex: 2,
    status: 1,
    addressCity: "唐山",
    birth: null,
    createTime: "2022-06-25 10:04:19",
    sign: "无",
  },
});
onBeforeMount(async () => {
  await getGoodsDetailsByGoodsId(Number(router.currentRoute.value.params.id))
    .then((res) => {
      goods.value = res.data;
    })
    .then(() => {
      loading.value = false;
      getCartInfo().then((res) => {
        res.data.cart.filter((c) => {
          if (c.goodsId == goods.value.goods.goodsId) {
            showButton.value = true;
          }
        });
      });
    });
});
//将商品添加购物车
const addToCartClick = (goodsId, days) => {
  changeCartInfo(goodsId, days)
    .then((res) => {
      res.data.cart.filter((c) => {
        if (c.id == goods.value.goodsId) {
          showButton.value = true;
        }
      });
    })
    .then(() => {
      ElNotification({
        title: "提示消息",
        message: "商品成功添加到购物车",
        type: "success",
      });
    });
};
</script>

<style scoped></style>
