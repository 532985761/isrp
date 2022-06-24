<template>
  <el-row class="min-h-screen-sm m-5 shadow-lg shadow-dark-500 bg-slate-500">
    <el-col :lg="12" class="flex items-center justify-center flex-col">
      <div class="font-bold text-5xl text-light-50 mb-4 m-3">
        欢迎来到管理系统
      </div>
      <div class="text-gray-200 text-sm m-3">
        本系统是智能化共享租赁平台管理系统，请保存好本系统网址以及用户名和账号方便进行登录和管理。
      </div>
    </el-col>
    <el-col :lg="12" class="bg-light-700">
      <div class="mt-30">
        <div class="text-2xl text-dark-50 font-bold m-10">请登录</div>
        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          label-width="120px"
          class="mr-20"
          :size="formSize"
          status-icon
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              type="password"
              show-password
              v-model="loginForm.password"
            />
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-input v-model="loginForm.code" />
            <span
              class="absolute right-0 top-0 cursor-pointer"
              @click="refreshCode"
            >
              <Captcha :timestamp="timestamp" />
            </span>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm(loginFormRef)"
              >登录</el-button
            >
            <el-button @click="resetForm(loginFormRef)">重填</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </el-row>
</template>
<script setup lang="ts">
import Captcha from "@/components/Captcha.vue";
import { reactive, ref } from "vue";
import { ElMessage, FormInstance, FormRules } from "element-plus";
import { login } from "@/api/user";
import { userStore } from "@/store/user";
import router from "@/router";
const formSize = ref("large");
const timestamp = ref(new Date().getTime().toString());
const loginFormRef = ref<FormInstance>();
const loginForm = reactive({
  username: "",
  password: "",
  code: "",
});
const rules = reactive<FormRules>({
  username: [
    { required: true, message: "请输入账号", trigger: "blur" },
    { min: 8, max: 40, message: "账号长度8-30位", trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 2, max: 20, message: "密码长度为2-20位", trigger: "blur" },
  ],
  code: [
    { required: true, message: "请输入验证码", trigger: "blur" },
    { min: 4, max: 4, message: "验证码长度为4位", trigger: "blur" },
  ],
});
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await login({
    email: loginForm.username,
    password: loginForm.password,
    role: 2,
    code: loginForm.code,
  }).then((res) => {
    if (res.status == 200) {
      const userstore = userStore();
      const data = res.data;
      userstore.setInfo(data.info);
      userstore.setToken(data.tokenHeader, data.token);
      router.push("/manager");
      ElMessage({
        message: "登陆成功",
        type: "success",
        duration: 5 * 1000,
      });
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};

const refreshCode = () => {
  timestamp.value = new Date().getTime().toString();
};
</script>
<style></style>
