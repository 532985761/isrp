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
      <h2 class="font-bold text-2xl text-gray-600">欢迎尊贵商家回来</h2>
      <div class="flex items-center justify-center my-5">
        <span class="h-[1px] w-50 bg-gray-200"></span>
      </div>
      <div>
        <el-form style="max-width: 460px">
          <el-form-item label="账号">
            <el-input placeholder="请输入你的账号..." v-model="loginFrom.username"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input placeholder="请输入你的密码..." v-model="loginFrom.password"/>
          </el-form-item>
        </el-form>
      </div>
        <div>
          <el-button
            @click="$router.push('/business')"
            type="success"
            class="w-50 my-2"
            round
            >登录</el-button
          >
        </div>
        <div>
          <el-button
            @click="$router.go(-1)"
            type="primary"
            class="w-50 my-2"
            round
            >返回上一页</el-button
          >
        </div>
      
    </el-col>
  </el-row>
</template>
<script setup lang="ts">
import {onMounted, reactive} from "vue";
import { LoginApi } from "@/api/user";

const loginFrom = reactive({
    username:'',
    password:''
})
onMounted(()=>{
    loginFrom
})
const login = async() =>{
    LoginApi().then((res:any) =>{
        if(loginFrom.username == res.code){
            
        }
    })
}

</script>