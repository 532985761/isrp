<template>
  <el-row class="min-h-screen bg-sky-500">
    <el-col :lg="16" class="flex items-center justify-center flex-col">
      <div class="font-bold text-5xl text-light-50 mb-4">欢迎回来</div>
      <div class="text-gray-200 text-sm">
        本系统是智能化共享租赁平台，在租赁前，请先登录本系统。
      </div>
    </el-col>
    <el-col
      :lg="8"
      class="flex items-center justify-center flex-col bg-light-50"
    >
      <h2 class="font-bold text-2xl text-gray-600">请选择您的角色</h2>
      <div class="flex items-center justify-center my-5">
        <span class="h-[1px] w-50 bg-gray-200"></span>
      </div>
      <div>
        <el-button
          @click="$router.push('/user')"
          type="success"
          class="w-50 my-2"
          round
          >用户登录</el-button
        >
      </div>
      <div>
        <el-button
          @click="$router.push('/login')"
          type="primary"
          class="w-50 my-2"
          round
          >商家登录</el-button
        >
      </div>
    </el-col>
  </el-row>
</template>
