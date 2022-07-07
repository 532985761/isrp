<template>
  <MyHeader msg="6"></MyHeader>
  <!-- 面包屑导航 -->
  <el-row :gutter="10">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-4">
      <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item :to="{ path: '/isrpUser/index' }">
          首页</el-breadcrumb-item
        >
        <el-breadcrumb-item>租赁中心</el-breadcrumb-item>
        <el-breadcrumb-item>{{ info.goodsCategoryFirst }}</el-breadcrumb-item>
        <el-breadcrumb-item>{{ info.goodsCategorySecond }}</el-breadcrumb-item>
      </el-breadcrumb></el-col
    >
  </el-row>
  <el-row :gutter="10" v-loading="loading">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11">
      <el-card class="box-card mt-3 bg-gray-100">
        <div
          class="text item font-mono text-lg font-semibold"
          style="font-weight: bold"
        >
          <!-- 本二级分类名称 -->
          <!-- {{ info.secondList }} -->
        </div>

        <div class="text item font-mono text-lg font-semibold">
          网站商品分类：
          <el-divider direction="vertical" border-style="dashed" />
          <el-button
            round
            v-for="(i, index) in goodsCategoryFirst"
            :type="
              i.goodsCategoryFirstId == router.currentRoute.value.params.firstId
                ? 'warning'
                : ''
            "
            :key="index"
            @click="
              changeGoodsInfo(
                i.goodsCategoryFirstId,
                router.currentRoute.value.params.secondId
              )
            "
          >
            {{ i.goodsCategoryFirstName }}</el-button
          >
        </div>
        <el-divider content-position="left"><span class=""></span> </el-divider>

        <div class="text item font-mono text-lg font-semibold">
          商品选项： <el-divider direction="vertical" border-style="dashed" />
          <el-button
            round
            v-for="(i, index) in info.secondList"
            :key="index"
            :type="
              i.goodsCategorySecondId ==
              router.currentRoute.value.params.secondId
                ? 'warning'
                : ''
            "
            @click="
              changeGoodsInfo(
                router.currentRoute.value.params.firstId,
                i.goodsCategorySecondId
              )
            "
          >
            {{ i.goodsCategorySecondName }}</el-button
          >
        </div>
      </el-card>
    </el-col>
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple-light"
    /></el-col>
  </el-row>

  <!-- 正文内容 -->
  <el-divider> 商品分类专区 </el-divider>
  <el-row :gutter="10" v-loading="loading">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11">
      <el-card v-for="(i, index) in goodsDetail" :key="index" class="mt-5">
        <el-descriptions
          class="margin-top mt-5 "
          :column="2"
          :title="'商品名称：' + i.goods.goodsName"
          border
         
        >
          <template #extra>
            <el-button type="primary" @click="lookGoodsDetail(i.goods.goodsId)"
              >查看待租商品详情</el-button
            >

            <el-button
              type="danger"
              v-if="i.goods.goodsStatus == 1"
              @click="addToCartClick(i.goods.goodsId, 1)"
              >立即下单租用</el-button
            >
            <el-button
              type="info"
              disabled
              v-if="i.goods.goodsStatus == 0"
              @click="addToCartClick(i.goods.goodsId, 1)"
              >正在出租中</el-button
            >
          </template>
          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <user />
                </el-icon>
                出租人
              </div>
            </template>
            {{ i.user.nickname }}
          </el-descriptions-item>
          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <iphone />
                </el-icon>
                商家联系方式
              </div>
            </template>
            给商家发消息
          </el-descriptions-item>
          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <location />
                </el-icon>
                所在位置
              </div>
            </template>
            {{ i.user.addressCity }}
          </el-descriptions-item>
          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <tickets />
                </el-icon>
                租赁方式
              </div>
            </template>
            <el-tag size="small">{{ i.goodsModal.orderModelName }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item width="200px" align="center">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <office-building />
                </el-icon>
                商品租用价格
              </div>
            </template>
            <el-tag size="large" type="danger">
              {{ i.goods.goodsPrice }}元</el-tag
            >
          </el-descriptions-item>
          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <office-building />
                </el-icon>
                商品图片
              </div>
            </template>

            <el-carousel indicator-position="outside " height="200px">
              <el-carousel-item v-for="item in i.goods.goodsImg.toString().split(',')" :key="item">
                <el-image :src="item"></el-image>
              </el-carousel-item>
            </el-carousel>
          </el-descriptions-item> </el-descriptions
        ><el-divider> </el-divider> </el-card
    ></el-col>
  </el-row>
  <!-- 模态框 -->
  <el-dialog v-model="centerDialogVisible" title="提示信息" width="30%" center>
    <span>确认加入购物车吗？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="centerDialogVisible = false"
          >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { ArrowRight } from "@element-plus/icons-vue";
import router from "@/router";
import { onMounted, ref, onBeforeMount, watch } from "vue";
import {
  getRentCenterInfoFromGoodsCategoryId,
  queryByPageGetGoodsCategoryFirst,
  queryByPageGetGoodsCategorySecond,
} from "@/api/goods";
import VueEvent from "@/utils/event";
import MyHeader from "@/views/user/components/header.vue";
import { ElNotification } from "element-plus";
import { changeCartInfo } from "@/api/order";

let info: any = ref({});
const activeName = ref("6");
const goodsCategoryFirst = ref();
const centerDialogVisible = ref(false);
const loading = ref(true);
const loadingSecond = ref(true);
onBeforeMount(() => {
  getRentCenterInfoFromGoodsCategoryId(
    router.currentRoute.value.params.firstId.toString(),
    router.currentRoute.value.params.secondId.toString()
  )
    .then((res) => {
      // 获取初始信息

      info.value = res.data;
      goodsDetail.value = res.data.goodsInfo;
    })
    .then(() => {
      loading.value = false;
    });
  //查询所有一级分类
  queryByPageGetGoodsCategoryFirst(1, 100).then((res) => {
    goodsCategoryFirst.value = res.data.content;
  });
});
//激活导航条

const goodsDetail = ref([
  {
    goods: {
      goodsId: 32,
      goodsName: "aaa",
      goodsDesc: "发布商品时间测试",
      goodsImg: "比基尼",
      goodsPrice: 5000,
      goodsSaleCount: 0,
      goodsStatus: 1,
      createTime: "2022-06-29 15:08:30",
      goodsCategorySecondId: 7,
      userId: "431188825079808",
      orderModelId: 1,
    },
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
    goodsModal: { orderModelId: 1, orderModelName: "先租后买" },
  },
]);
//改变激活状态
const changeGoodsInfo = async (one, two) => {
  loading.value = true;
  await router.push("/isrpUser/rentCenter/" + one + "/" + two);

  getRentCenterInfoFromGoodsCategoryId(
    router.currentRoute.value.params.firstId.toString(),
    router.currentRoute.value.params.secondId.toString()
  )
    .then((res) => {
      // 获取所有后端信息
      info.value = res.data;
      goodsDetail.value = res.data.goodsInfo;
    })
    .then(() => {
      loading.value = false;
    });
};
//将商品添加购物车
const addToCartClick = (goodsId, days) => {
  changeCartInfo(goodsId, days)
    .then((res) => {})
    .then(() => {
      ElNotification({
        title: "提示消息",
        message: "商品成功添加到购物车",
        type: "success",
      });
    });
};
//查看商品详情
const lookGoodsDetail = (id) => {
  router.push("/isrpUser/goodsdetail/" + id);
};
</script>
<style></style>
