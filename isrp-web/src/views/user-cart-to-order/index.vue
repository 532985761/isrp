<template>
  <!-- 面包屑导航 -->
  <el-row :gutter="10">
    <el-col :lg="2"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-4">
      <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item :to="{ path: '/isrpUser/index' }">
          首页</el-breadcrumb-item
        >
        <el-breadcrumb-item>租赁中心</el-breadcrumb-item>
        <el-breadcrumb-item>我的购物车</el-breadcrumb-item>
        <el-breadcrumb-item>商品下单</el-breadcrumb-item
        ><el-breadcrumb-item>核对订单信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
  </el-row>
  <el-row>
    <el-col :lg="2"></el-col>
    <el-col :lg="20" class="mt-4">
     <div class="w-800px ml-626px -mt-40px">
          <el-steps :space="200" :active="2" finish-status="success">
            <el-step title="选取商品" />
            <el-step title="购物车筛选" />
            <el-step title="下单" />
            <el-step title="付款" />
          </el-steps>
        </div>
      <el-card>
        <div>
          <span style="font-weight: bold; font-size: 15px">收货人信息</span>
          <el-button type="primary" style="float: right"
            >新增收货地址</el-button
          >
        </div>
        <div></div>
        <el-divider></el-divider>

        <div>
          <span style="font-weight: bold; font-size: 15px">付款方式</span>
        </div>
        <div class="mt-4">
          <el-button :type="payType">支付宝</el-button>
          <el-button>微信</el-button>
          <el-button>白条</el-button>
        </div>
        <el-divider></el-divider>

        <div>
          <span style="font-weight: bold; font-size: 15px">详细租用清单</span>
        </div>
        <el-row>
          <el-col :lg="8">
            <el-card class="bg-gray-200 mt-3">
              <div style="font-weight: bold; font-size: 13px">
                <span>租用方式：</span>以租代售
              </div>
              <el-divider></el-divider>
              <div style="font-weight: bold; font-size: 13px">
                <span>配送方式：</span>
                <el-select
                  v-model="value"
                  class="m-2"
                  placeholder="Select"
                  size="small"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </div>
            </el-card>
          </el-col>
          <el-col :lg="16"><el-card class="bg-blue-100 mt-3"></el-card></el-col>
        </el-row>

        <el-divider></el-divider>
        <div>
        <el-button style="float:right" class="w-200px h-50px -mt-25px" type="danger">提交订单</el-button>
        </div>
        </el-card
    ></el-col>
  </el-row>
</template>
<script lang="ts" setup>
import { ArrowRight } from "@element-plus/icons-vue";
</script>

<style scoped></style>
