<template>
  <el-dialog
    v-model="centerDialogVisible"
    title="欢迎来到智租网注册中心"
    width="60%"
    center
    :close-on-click-modal="false"
  >
    <el-form
      v-loading="loading"
      element-loading-text="正在为您注册账号，请耐心等候！"
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email" placeholder="请输入邮箱" />
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input
          v-model="ruleForm.pass"
          type="password"
          autocomplete="off"
          placeholder="请输入密码"
        />
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input
          v-model="ruleForm.checkPass"
          type="password"
          placeholder="请确认密码"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="身份选择"
        ><el-select
          v-model="identity"
          class="m-2 -ml-0.5 -mt-0.5"
          placeholder="请选择身份"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          /> </el-select
      ></el-form-item>
      <el-form-item label="城市选择">
        <el-cascader
          placeholder="请选择城市"
          class="m-2 -ml-0.5 -mt-0.5"
          :options="options1"
          v-model="selectedOptions"
          @change="selectCity"
        >
        </el-cascader
      ></el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          @click="centerDialogVisible = false"
          class="w-60"
          type="danger"
          >取消</el-button
        >
        <el-button
          type="primary"
          class="w-60"
          @click="confirmRegister"
          v-loading="loading"
          >确认注册</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElButton, ElDialog } from "element-plus";
import { CircleCloseFilled } from "@element-plus/icons-vue";
import { testGoodsApi } from "@/api/goods";
import { getOneUserInfo, registerUser } from "@/api/user";
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
import { regionData, CodeToText } from "element-china-area-data"; //引入
let loading = ref(false);

let city = ref("");
let identity = ref("");

const options1 = regionData;
let selectedOptions: [];
//注册表单开始
const ruleForm = reactive({
  checkPass: "",
  pass: "",
  email: "",
});
const selectCity = (res: any) => {
  city.value = CodeToText[res[2]];
};
let centerDialogVisible = ref(false);
const ruleFormRef = ref<FormInstance>();
const showDialog = (data: any) => {
  centerDialogVisible.value = data;
};

defineExpose({
  showDialog,
});
const checkEmail = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error("请输入邮箱"));
  }
  const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  if (!regEmail.test(value)) {
    // 合法的邮箱
    return callback(new Error("请输入正确的邮箱"));
  }
};

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请输入密码"));
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请再次输入密码"));
  } else if (value !== ruleForm.pass) {
    callback(new Error("两次密码不匹配"));
  } else {
    callback();
  }
};

const rules = reactive({
  pass: [{ validator: validatePass, trigger: "blur" }],
  checkPass: [{ validator: validatePass2, trigger: "blur" }],
  email: [{ validator: checkEmail, trigger: "blur" }],
});

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      console.log("submit!");
    } else {
      console.log("error submit!");
      return false;
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
const value = ref("");

const options = [
  {
    value: "1",
    label: "商家",
  },
  {
    value: "0",
    label: "普通用户",
  },
];

//注册表单结束

//确认注册
let data = ref({});
async function confirmRegister() {
  loading.value = true;
  let userForm = {
    email: ruleForm.email,
    password: ruleForm.pass,
    role: identity.value,
    addressCity: city.value,
  };

  await registerUser(userForm)
    .then((res: any) => {
      if (res.status == 200) {
        ElMessage({
          message: res.data,
          type: "success",
        });
      }
    })
    .then((res: any) => {
      loading.value = false;
    });
}
</script>

<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
