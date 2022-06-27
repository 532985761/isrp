<template>
  <el-row :gutter="10" class="bg-slate-200 h-300px">
    <el-col :lg="3"></el-col>
    <el-col :lg="3">
      <div
        style="border: px solid #ccc; text-align: center"
        class="h-300px w-190px bg-blue-200"
      >
        <div
          v-for="i in 4"
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
            >商品名称</span
          >
        </div>

        <el-menu
          @select="selectMenu"
          class="el-menu-vertical-demo mt-2"
        >
          <el-menu-item
            class="bg-light-200"
            v-for="(i, index) in 4"
            :key="index"
            :index="index + ''"
            @click="showTabTrue"
          >
            <el-icon><Coin /></el-icon>
            <span
              >电器/手机&nbsp;&nbsp;&nbsp;<el-icon><CaretRight /></el-icon
            >


            </span>
          </el-menu-item>
        </el-menu>
      </div>
    </el-col>
    <el-col :lg="11">
      <el-card class="bg-dark-100">
        <el-carousel :interval="4000" height="258px" v-if="showTab">
          <el-carousel-item v-for="item in 6" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
        <el-card class="h-258px scale-in-hor-left" v-if="!showTab">
          <div style="text-align: center; font-weight: bold" class="font-sans">
            {{ tabName }}
          </div>
          <el-divider></el-divider>
        </el-card>
      </el-card>
    </el-col>
    <el-col :lg="4">
      <div
        class="bg-light-50 h-300px"
        style="border: px solid #ccc; text-align: center; padding-top: 1px"
      >
        <el-avatar
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        /><br />
        <el-tag> 欢迎你,zhz</el-tag>
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
    </el-col> </el-row
  ><br />

  <!-- 主页第二栏 -->
  <el-row :gutter="10" class="bg-slate-200 h-300px">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div class="grid-content ep-bg-purple-light h-30 w-full bg-dark-400"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div class="grid-content ep-bg-purple h-30 w-full bg-dark-400 ml-11"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div class="grid-content ep-bg-purple h-30 w-full bg-dark-400 ml-22"
    /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="4" :xl="11"
      ><div class="grid-content ep-bg-purple h-30 w-full bg-dark-400 ml-33"
    /></el-col>
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple-light"
    /></el-col>
  </el-row>

</template>

<script lang="ts" setup>
import { ref } from "vue";
const dialogTableVisible = ref(false);
const shopname = ref("");
const showTab = ref(true);
const tabName = ref("");
const selectMenu = (a) => {
  if (a == 1) {
    tabName.value = "1";
  }
  if (a == 2) {
    tabName.value = "2";

  }
  if (a == 3) {
    tabName.value = "3";
  }
  if (a == 0) {
    tabName.value = "0";


  }
};
const showTabTrue =()=>{
  showTab.value = true

    setTimeout(() => {showTab.value = false,console.log("55555");
    },10)

}
const showTabFalse =()=>{
  showTab.value = false
}
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
.scale-in-hor-left{animation:scale-in-hor-left .8s cubic-bezier(.25,.46,.45,.94) both}


@keyframes scale-in-hor-left{0%{transform:scaleX(0);transform-origin:0 0;opacity:1}100%{transform:scaleX(1);transform-origin:0 0;opacity:1}}
</style>
