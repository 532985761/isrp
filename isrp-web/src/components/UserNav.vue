<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    background-color="#545c64"
    text-color="#fff"
    
  >
    <el-menu-item index="0">
      <el-image
        style="width: 150px; height: 50px; border-radius: 15px"
        src="/src/assets/zzlogo.png"
        class="mt-0.5"
      />
      <span class="ml-3"> 欢迎来到智租网平台</span></el-menu-item
    >
    <div class="flex-grow" />
    <el-menu-item index="1">我的购物车</el-menu-item>
    <el-menu-item index="3"> </el-menu-item>

    <el-sub-menu index="2">
      <template #title>
        <el-avatar
          class="mt-2"
          :size="40"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        /><span class="ml-3">欢迎你：{{ userstore.info.nickname }}</span>
      </template>
      <el-menu-item index="2-1">个人信息</el-menu-item>
      <el-menu-item index="2-2">我的订单</el-menu-item>
    </el-sub-menu>
  </el-menu>
    <el-row>
    <el-col :span="24" class="bg-light-600">
      <el-row :gutter="10">
        <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"></el-col>
        <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-6">
          <div>
            <el-input
              v-model="keyWord"
              class="w-150"
              size="large"
              placeholder="在此搜索你想租赁的商品"
              :suffix-icon="Search"
            />
            <el-button type="primary" class="h-10">点击搜索</el-button>
          </div>

          <!-- <div style="float: right;border: 1px solid black" class="mt-4 -ml-30 border-gray-500" >
            <el-icon ><ShoppingCartFull /></el-icon><span class="ml-4 border-red-500">查看购物车</span>
          </div> -->
          <el-row
            ><el-col :span="4" class="mt-5"
              ><div
                class="h-10 text-center text-1.5xl"
                style="
                  background-color: #fc3737;
                  color: white;
                  font-family: 'Lucida Calligraphy', cursive, serif, sans-serif;
                "
              >
                <el-icon><HotWater /></el-icon>
                <span style="line-height: 40px">热门推荐商品</span>
                &nbsp;<el-icon :size="13"
                  ><ArrowRightBold
                /></el-icon></div
            ></el-col>
            <el-col :span="14" class="mt-5"
              ><el-tabs
                v-model="activeName"
                class="demo-tabs ml-10"
                @tab-click="handleClick"
              >
                <el-tab-pane label="首页" name="5">
                </el-tab-pane>
                <el-tab-pane label="秒杀活动" name="1"></el-tab-pane>
                <el-tab-pane label="共享租赁" name="2"></el-tab-pane>
                <el-tab-pane label="先租后买" name="3"></el-tab-pane>
                <el-tab-pane
                  label="已租代售"
                  name="4"
                ></el-tab-pane> 
                 <el-tab-pane
                  label="智慧租赁招聘中心"
                  name="6"
                ></el-tab-pane> </el-tabs></el-col
          ></el-row>
        </el-col> </el-row
    ></el-col>
  </el-row>
  <router-view />
</template>

<script lang="ts" setup>
import index from "@/views/user/index.vue";
import { ref } from "vue";
import { userStore } from "@/store/user";
import { Search } from '@element-plus/icons-vue'
import type { TabsPaneContext } from "element-plus";
import router from "@/router";

let keyWord = ref("")
const userstore = userStore();


const activeName = ref("2");

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab.props, event);
  if(tab.props.name == 5){
    console.log("23232323");
    
    router.push("/user/index")
  
  }
  if(tab.props.name == 3){
     router.push("/user/goodsdetail")

  }
};

</script>

<style>
.flex-grow {
  flex-grow: 1;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
