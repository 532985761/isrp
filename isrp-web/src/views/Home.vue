<template>
  <el-row class="min-h-screen">
    <el-col
      :lg="16"
      class="flex items-center justify-center bg-sky-500 flex-col"
    >
      <div class="font-bold text-5xl text-light-50 mb-4 scale-in-ver-center">欢迎回来</div>
      <div class="text-gray-200 text-sm scale-in-ver-center">
        本系统是智能化共享租赁平台，在租赁前，请先登录本系统。
      </div>
    </el-col>
    <el-col
      :lg="8"
      class="flex items-center justify-center flex-col bg-light-50 scale-up-hor-center"
    >
      <h2 class="font-bold text-2xl text-gray-600">请选择您的角色</h2>
      <div class="flex items-center justify-center my-5">
        <span class="h-[1px] w-50 bg-gray-200"></span>
      </div>
      <div>
        <el-button
          @click="$router.push('/userLogin')"
          type="success"
          class="w-50 my-2"
          round
          >用户登录</el-button
        >
      </div>
      <div>
        <el-button
          @click="$router.push('/businessLogin')"
          type="primary"
          class="w-50 my-2"
          round
          >商家登录</el-button
        >
      </div>
      <div>
        <el-link @click="getChild">没有账号?点击注册</el-link>
      </div>
    </el-col>
  </el-row>
  <UserRegister ref="childRef"></UserRegister>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import UserRegister from "./UserRegister.vue";
const childRef = ref();
const getChild = () => {
  // 3. 调用子组件的方法或者变量，通过value
  childRef.value.showDialog(true);
};
</script>
<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
/* ----------------------------------------------
 * Generated by Animista on 2022-6-27 21:33:11
 * Licensed under FreeBSD License.
 * See http://animista.net/license for more info. 
 * w: http://animista.net, t: @cssanimista
 * ---------------------------------------------- */

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
.scale-in-hor-left {
  animation: scale-in-hor-left 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}
/* ----------------------------------------------
 * Generated by Animista on 2022-6-27 21:41:32
 * Licensed under FreeBSD License.
 * See http://animista.net/license for more info. 
 * w: http://animista.net, t: @cssanimista
 * ---------------------------------------------- */

@keyframes scale-in-ver-center {
  0% {
    transform: scaleY(0);
    opacity: 1;
  }
  100% {
    transform: scaleY(1);
    opacity: 1;
  }
}
.scale-in-ver-center {
  animation: scale-in-ver-center 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}
/* ----------------------------------------------
 * Generated by Animista on 2022-6-27 21:42:41
 * Licensed under FreeBSD License.
 * See http://animista.net/license for more info. 
 * w: http://animista.net, t: @cssanimista
 * ---------------------------------------------- */

@keyframes scale-up-hor-center {
  0% {
    transform: scaleX(0.4);
  }
  100% {
    transform: scaleX(1);
  }
}
.scale-up-hor-center {
  animation: scale-up-hor-center 0.4s cubic-bezier(0.39, 0.575, 0.565, 1) both;
}
</style>
