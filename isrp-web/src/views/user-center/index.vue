<template>
  <el-row class="tac">
    <el-col :span="3">
      <el-menu 
        default-active="2"
        class="el-menu-vertical-demo"
        :router="true"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>订单信息</span>
          </template>
          <el-menu-item-group >
            <el-menu-item index="/isrpUser/userCenter/waitPay">待支付</el-menu-item>
            <el-menu-item index="1-2">已付款</el-menu-item>
            <el-menu-item index="1-3">已完成</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>个人信息</span>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon><document /></el-icon>
          <span>地址管理</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="21"><router-view></router-view></el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>
