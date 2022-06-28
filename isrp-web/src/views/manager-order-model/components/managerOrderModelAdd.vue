<template>
  <el-button class="mt-4 w-screen" style="width: 100%" @click="onAddItem"
    >添加订单模式</el-button
  >
  <el-drawer v-model="drawer" :direction="direction">
    <template #title>
      <h4>添加订单模式</h4>
    </template>
    <template #default>
      <div>
        <el-form
          ref="formRef"
          :model="validateForm"
          label-width="100px"
          class="demo-ruleForm"
          size="large"
        >
          <el-form-item
            label="模式名称"
            prop="orderModelName"
            :rules="[{ required: true, message: '模式名称不能为空' }]"
          >
            <el-input
              v-model.number="validateForm.orderModelName"
              type="text"
              autocomplete="off"
            />
          </el-form-item>
        </el-form>
      </div>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button type="primary" @click="confirmClick(formRef)"
          >确定</el-button
        >
        <el-button @click="cancelClick(formRef)">取消</el-button>
      </div>
    </template>
  </el-drawer>
</template>
<script lang="ts" setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import type { FormInstance } from "element-plus";
import { addOrderModel } from "@/api/order";

const drawer = ref(false);
const direction = ref("rtl");
const formRef = ref<FormInstance>();
const validateForm = reactive({
  orderModelName: "",
});
const emits = defineEmits(["getTimeStamp"]);

const confirmClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      ElMessageBox.confirm(
        `您确定添加订单模式： ${validateForm.orderModelName} ?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }
      )
        .then(() => {
          addOrderModel(validateForm.orderModelName).then((res) => {
            if (res.status == 200) {
              emits("getTimeStamp", new Date().getTime().toString());
              ElMessage({
                message: "添加成功",
                type: "success",
                duration: 2000,
              });
              drawer.value = false;
            }
          });
        })
        .catch(() => {
          // catch error
        });
    } else {
      console.log("error submit!");
      return false;
    }
  });
};

const cancelClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  drawer.value = false;
};

const onAddItem = () => {
  drawer.value = true;
};
</script>
