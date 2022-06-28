<template>
  <!-- 面包屑导航 -->
  <el-row :gutter="10">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-4">
      <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item
          :to="{ path: '/isrpUser/index' }"
          @click="selectRouterLine"
        >
          首页</el-breadcrumb-item
        >
        <el-breadcrumb-item>租赁中心</el-breadcrumb-item>
        <el-breadcrumb-item>{{
          info.goodsCategoryFirst.goodsCategoryFirstName
        }}</el-breadcrumb-item>
        <el-breadcrumb-item>{{
          info.goodsCategorySecond.goodsCategorySecondName
        }}</el-breadcrumb-item>
      </el-breadcrumb></el-col
    >
  </el-row>
  <el-row :gutter="10">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11">
      <el-card class="box-card mt-3 bg-gray-100">
        <div
          class="text item font-mono text-lg font-semibold"
          style="font-weight: bold"
        >
          {{ info.goodsCategorySecond.goodsCategorySecondName }}
        </div>
        <el-divider style="margin-top: 5px" content-position="left"
          ><span class=""></span>
        </el-divider>

        <div class="text item font-mono text-lg font-semibold">
          网站商品分类：
          <el-divider direction="vertical" border-style="dashed" />
          <el-button
            round
            v-for="(i, index) in goodsCategoryFirst"
            :type="
              i.goodsCategoryFirstName ==
              info.goodsCategoryFirst.goodsCategoryFirstName
                ? 'warning'
                : ''
            "
            :key="index"
            @click="
              changeGoodsInfo(
                i.goodsCategoryFirstId,
                info.goodsCategorySecond.goodsCategorySecondId
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
            v-for="(i, index) in info.allSecondGoods.content"
            :key="index"
            :type="
              i.goodsCategorySecondName ==
              info.goodsCategorySecond.goodsCategorySecondName
                ? 'warning'
                : ''
            "
            @click="
              changeGoodsInfo(
                info.goodsCategoryFirst.goodsCategoryFirstId,
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
  <el-divider>
    <el-icon><star-filled /></el-icon>
  </el-divider>
  <el-row :gutter="10">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11"
      ><el-card>
        <el-descriptions class="margin-top mt-5" title="商品名称" :column="2" border v-for="(i,index) in info.allSecondGoods.content" :key="index">
          <template #extra>
            <el-button type="primary">查看待租商品详情</el-button>
            <el-button type="danger">立即下单租用</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <user />
                </el-icon>
                出租人
              </div>
            </template>
            kooriookami
          </el-descriptions-item>
          <el-descriptions-item>
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
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <location />
                </el-icon>
                所在位置
              </div>
            </template>
            Suzhou
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <tickets />
                </el-icon>
                租赁方式
              </div>
            </template>
            <el-tag size="small">以租代售</el-tag>
          </el-descriptions-item>
          <el-descriptions-item width="200px">
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <office-building />
                </el-icon>
                商品租用价格
              </div>
            </template>
            100
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <office-building />
                </el-icon>
                商品图片
              </div>
            </template>
            <el-image class="h-100px w-198px" src="/src/assets/znzzlogo.png" />
          </el-descriptions-item>  </el-descriptions><el-divider>
    <el-icon><CaretBottom /></el-icon>
  </el-divider>
          </el-card
    ></el-col>
  </el-row>

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

let info: any = ref({
  goodsCategoryFirst: {
    goodsCategoryFirstId: 3,
    goodsCategoryFirstName: "房产",
  },
  goodsCategorySecond: {
    goodsCategorySecondId: 9,
    goodsCategoryFirstId: 3,
    goodsCategorySecondName: "汤臣一品豪居",
  },
  allSecondGoods: {
    content: [
      {
        goodsCategorySecondId: 9,
        goodsCategoryFirstId: 3,
        goodsCategorySecondName: "汤臣一品豪居",
      },
    ],
    pageable: {
      sort: { sorted: false, unsorted: true, empty: true },
      offset: 0,
      pageSize: 500,
      pageNumber: 0,
      paged: true,
      unpaged: false,
    },
    last: true,
    totalElements: 1,
    totalPages: 1,
    size: 500,
    number: 0,
    sort: { sorted: false, unsorted: true, empty: true },
    first: true,
    numberOfElements: 1,
    empty: false,
  },
  page: {
    content: [],
    pageable: {
      sort: { sorted: false, unsorted: true, empty: true },
      offset: 0,
      pageSize: 10,
      pageNumber: 0,
      paged: true,
      unpaged: false,
    },
    last: true,
    totalElements: 0,
    totalPages: 0,
    size: 10,
    number: 0,
    sort: { sorted: false, unsorted: true, empty: true },
    first: true,
    numberOfElements: 0,
    empty: true,
  },
});
const activeName = ref("6");
onBeforeMount(() => {
  getRentCenterInfoFromGoodsCategoryId(
    router.currentRoute.value.params.firstId,
    router.currentRoute.value.params.secondId
  ).then((res) => {
    // 获取二级分类名
    info.value = res.data;
  });
});
//激活导航条
VueEvent.emit("tomsg", { data: "6" });
const selectRouterLine = () => {
  VueEvent.emit("tomsg", { data: "5" });
};

const goodsCategoryFirst = ref();
onMounted(async () => {
  await queryByPageGetGoodsCategoryFirst().then((res) => {
    goodsCategoryFirst.value = res.data.content;
  });
});
//改变激活状态
const changeGoodsInfo = async (one, two) => {
  await router.push("/isrpUser/rentCenter/" + one + "/" + two);

  await getRentCenterInfoFromGoodsCategoryId(
    router.currentRoute.value.params.firstId,
    router.currentRoute.value.params.secondId
  ).then((res) => {
    // 获取所有后端信息
    info.value = res.data;
  });
};
</script>
<style></style>
