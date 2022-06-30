<template>
  <MyHeader msg="5"></MyHeader>
  <el-row :gutter="10" class="bg-slate-200 h-300px">
    <el-col :lg="3"></el-col>
    <el-col :lg="3">
      <div
        style="border: px solid #ccc; text-align: center"
        class="h-300px w-190px bg-blue-200"
      >
        <div
          v-for="i in goodsCategoryFirst"
          :key="i"
          style="
            display: inline-block;
            vertical-align: top;
            border: 1px solid black;
          "
          class="ml-2 mt-2 w-66px h-26px"
        >
          <span
            class="font-serif;font-bold"
            style="color: black; font-size: 12px"
            >{{ i.goodsCategoryFirstName }}</span
          >
        </div>

        <el-menu class="el-menu-vertical-demo mt-2" v-loading="loading">
          <el-menu-item
            class="bg-light-200"
            v-for="(i, index) in goodsCategoryFirst"
            :key="index"
            :index="index + ''"
            @click="
              selectMenu(i.goodsCategoryFirstName, i.goodsCategoryFirstId)
            "
          >
            <el-icon><Coin /></el-icon>
            <span
              >{{ i.goodsCategoryFirstName }}&nbsp;&nbsp;&nbsp;<el-icon
                ><CaretRight
              /></el-icon>
            </span>
          </el-menu-item>
        </el-menu>
      </div>
    </el-col>
    <!-- 商品左侧第二级分类 -->
    <el-col :lg="11">
      <el-card class="bg-dark-100" >
        <el-carousel :interval="4000" height="258px" v-if="showTab">
          <el-carousel-item v-for="item in 6" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
        <el-card class="h-258px scale-in-hor-left" v-loading="loading1" v-if="!showTab">
          <div style="text-align: center; font-weight: bold" class="font-sans">
            {{ tabName }}
          </div>
          <el-divider></el-divider>
          <div
            style="display: inline-block"
            class="ml-4 mt-2"
            v-for="(i, index) in goodsCategorySecond"
            :key="index"
          >
            <router-link
              :to="{
                name: 'rentCenter',
                params: { firstId: firstId, secondId: i.goodsCategorySecondId },
              }"
              ><el-button plain>
                {{ i.goodsCategorySecondName }}</el-button
              ></router-link
            >
          </div>
        </el-card>
      </el-card>
    </el-col>
    <!--右侧用户信息 -->
    <el-col :lg="4">
      <div
        class="bg-light-50 h-300px"
        style="border: px solid #ccc; text-align: center; padding-top: 1px"
      >
        <el-avatar
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        /><br />
        <el-tag> 欢迎你,{{ userInfo.info.nickname }}</el-tag>
        <el-divider>
          <el-icon><star-filled /></el-icon>
        </el-divider>
        <div>
          <el-tag type="warning" class="font-mono text-base"
            ><el-icon><Coin /></el-icon>&nbsp;&nbsp;账户余额：1000.0</el-tag
          >
        </div>
        <div class="inline-block mt-3">
          <el-button class="bg-red-300">我的购物车</el-button>
        </div>
        <div class="inline-block mt-3 ml-1">
          <el-button class="bg-sky-300">我的订单</el-button>
        </div>
        <el-divider> 网站公告 </el-divider>
        <el-link type="primary"
          ><el-icon><AlarmClock /></el-icon
          >&nbsp;&nbsp;&nbsp;关于秒杀活动的广播通知...</el-link
        ><br />
        <el-link type="success"
          ><el-icon><Bell /></el-icon
          >&nbsp;&nbsp;&nbsp;租赁产品上新啦！...</el-link
        >
      </div>
    </el-col>
  </el-row>
  <!-- 主页第二栏 -->
  <el-row :gutter="10" class="bg-slate-200 h-150px">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div
        class="grid-content ep-bg-purple-light h-115px w-full bg-dark-400 mt-4"
      >
        <el-carousel :interval="4000" height="115px">
          <el-carousel-item v-for="item in 3" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div
        class="grid-content ep-bg-purple h-30 w-full bg-dark-400 ml-11 mt-4"
      >
        <el-carousel :interval="4000" height="115px">
          <el-carousel-item v-for="item in 3" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div></el-col
    >
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div
        class="grid-content ep-bg-purple h-30 w-full bg-dark-400 ml-22 mt-4"
      >
        <el-carousel :interval="4000" height="115px">
          <el-carousel-item v-for="item in 3" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div></el-col
    >
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div
        class="grid-content ep-bg-purple h-30 w-full bg-dark-400 ml-32 mt-4"
      >
        <el-carousel :interval="4000" height="115px">
          <el-carousel-item v-for="item in 3" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div></el-col
    >
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple-light"
    /></el-col>
  </el-row>
  <el-row class="bg-white-200">
    <el-col :span="24"
      ><div class="grid-content ep-bg-purple-dark">
        <el-divider>
          <span style="font-weight: bold" class="italic text-2xl"
            >商品浏览区</span
          >
          <span
            style="font-weight: bold"
            class="italic text-xl text-yellow-500"
          >
            <el-icon><star-filled /></el-icon>选择热门租赁商品</span
          >
        </el-divider>
      </div></el-col
    >
  </el-row>
  <el-row :gutter="20" class="h-700px">
    <el-col :span="3"></el-col>
    <el-col :span="18" class="bg-light-400"
      ><div class="grid-content ep-bg-purple">
        <el-divider content-position="right"
          ><span class="font-serif text-2xl font-bold">热租商品</span>
        </el-divider>
        <el-card
          class="w-240px h-300px ml-9 mt-4"
          v-for="i in 8"
          :key="i"
          style="display: inline-block"
        ></el-card>
      </div>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
  <el-divider>
    <span style="font-weight: bold" class="italic text-2xl">商品浏览区</span>
    <span style="font-weight: bold" class="italic text-xl text-green-500">
      <el-icon><star-filled /></el-icon>选择您的随心租</span
    >
  </el-divider>
  <el-row :gutter="20" class="h-800px">
    <el-col :span="3"></el-col>
    <el-col :span="18" class="bg-light-400"
      ><div class="grid-content ep-bg-purple">
        <el-divider content-position="right"
          ><span class="font-serif text-2xl font-bold">随心租</span>
        </el-divider>
        <el-card
          class="w-240px h-300px ml-9 mt-4"
          v-for="i in 8"
          :key="i"
          style="display: inline-block"
        ></el-card>
      </div>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
</template>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import {
  queryByPageGetGoodsCategoryFirst,
  queryByPageGetGoodsCategorySecond,
} from "@/api/goods";
import { userStore } from "@/store/user";
import MyHeader from "./components/header.vue";
const dialogTableVisible = ref(false);
const shopname = ref("");
const showTab = ref(true);
const tabName = ref("");
const userInfo = userStore();
let loading = ref(true)
let loading1= ref(true)
//获取商品陪你二级分类信息
//获取第二栏左侧导航商品二级信息
let goodsCategorySecond: any = ref({});
const firstId = ref();
const selectMenu: any = (name, index) => {
  firstId.value = index;
  showTab.value = true;
  setTimeout(() => {
    showTab.value = false;
  }, 10);
  tabName.value = name;
loading1.value = true
  queryByPageGetGoodsCategorySecond(index, 1, 1000).then((res) => {
    goodsCategorySecond.value = res.data.content;
    loading1.value = false
  });
};

const showTabFalse = () => {
  showTab.value = false;
};
//获取第二栏左侧导航商品信息
let goodsCategoryFirst: any = ref({});

onMounted(async () => {
  await queryByPageGetGoodsCategoryFirst(1, 4).then((res) => {
    goodsCategoryFirst.value = res.data.content;
    loading.value = false
  });
});
</script>

<style>
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.scale-in-hor-left {
  animation: scale-in-hor-left 0.8s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}

@keyframes scale-in-hor-left {
  0% {
    transform: scaleX(0);
    transform-origin: 0 0;
    opacity: 1;
  }
  100% {
    transform: scaleX(1);
    transform-origin: 0 0;
    opacity: 1;
  }
}
</style>
