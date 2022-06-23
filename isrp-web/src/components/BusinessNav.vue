<template>
  <div class="common-layout font-mono">
    <el-container>
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
          <el-menu-item index="0">智能化共享租赁平台</el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="1">平台首页</el-menu-item>
          <el-menu-item index="2">个人中心</el-menu-item>
          <el-menu-item index="3">退出登录</el-menu-item>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-col :span="24" style="margin-top: 20px">
            <h5 class="mb-2">欢迎尊贵的张三用户！</h5>
            <el-menu default-active="1" class="el-menu-vertical-demo">
              <el-menu-item index="1" @click="$router.push('/business/index')">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>主页</span>
                </template>
              </el-menu-item>
              <el-menu-item index="2" @click="$router.push('/business/info')">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>个人资料</span>
                </template>
              </el-menu-item>
              <el-menu-item
                index="3"
                @click="$router.push('/business/goodsinfo')"
              >
                <el-icon><icon-menu /></el-icon>
                <span>管理商品</span>
              </el-menu-item>
              <el-sub-menu index="4">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>管理订单</span>
                </template>
                <el-menu-item-group title="待支付">
                  <el-menu-item index="4-1" @click="$router.push('/business/orderinfo')">待支付订单</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="已完成">
                  <el-menu-item index="4-2" @click="$router.push('/business/orderfinish')">已完成订单</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="等待租赁">
                  <el-menu-item index="4-3">等待租赁订单</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>退出登录</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-aside>
        <el-main><router-view /></el-main>
      </el-container>
    </el-container>
  </div>
</template>