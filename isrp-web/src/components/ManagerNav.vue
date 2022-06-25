<template>
  <div>
    <el-container>
      <el-header class="text-center m-5 text-3xl font-bold"
        >智能化共享租赁平台系统 <span class="text-sm">管理平台v1.0</span>
      </el-header>
      <el-container>
        <el-aside :width="navWidth">
          <div class="text-center">
            <div v-if="isCollapse == false">
              <el-button
                :loading="loading"
                :icon="CaretLeft"
                circle
                @click="(isCollapse = true), (loading = true)"
              />
            </div>
            <div v-if="isCollapse == true">
              <el-button
                :loading="loading"
                :icon="CaretRight"
                circle
                @click="(isCollapse = false), (loading = true)"
              />
            </div>
          </div>

          <el-menu
            default-active="1"
            :collapse="isCollapse"
            @open="handleOpen"
            router
          >
            <el-menu-item index="1" disabled>
              <el-icon><avatar /></el-icon>
              <template #title>
                欢迎您, {{ userStore().info.nickname }}</template
              >
            </el-menu-item>
            <el-menu-item index="/manager/orderModel">
              <el-icon><tickets /></el-icon>
              <template #title>订单模式管理</template>
            </el-menu-item>
            <el-menu-item index="/manager/orderProcess">
              <el-icon><van /></el-icon>
              <template #title>订单流程管理</template>
            </el-menu-item>
            <el-menu-item index="/manager/user">
              <el-icon><user /></el-icon>
              <template #title>用户管理</template>
            </el-menu-item>
            <el-menu-item index="5" class="text-red-600">
              <el-icon><close /></el-icon>
              <template #title>退出</template>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main class="h-[76vh]"></el-main>
          <el-footer class="text-center text-sm"
            >Copyright 2022 By 2组 All Rights Reserved
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref, watch } from "vue";
import {
  User,
  Tickets,
  Van,
  Close,
  CaretLeft,
  CaretRight,
  Avatar,
} from "@element-plus/icons-vue";
import { userStore } from "@/store/user";

const loading = ref(false);
const isCollapse = ref(false);
const navWidth = ref("200px");
const handleOpen = (key: string, keyPath: string[]) => {
  navWidth.value = "200px";
  loading.value = false;
};
// 回调函数异常;
// const handleClose = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath, "close");
//   navWidth.value = "100px";
//   loading.value = false;
// };
watch(isCollapse, (newValue) => {
  if (newValue == true) {
    setTimeout(() => {
      navWidth.value = "70px";
      loading.value = false;
    }, 400);
  }
});
</script>

<style scoped>
.el-menu-item.is-disabled {
  opacity: 1;
  color: #0ea5e9;
}
</style>
