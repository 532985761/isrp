<template>
  <el-button class="mt-4" style="width: 100%" @click="onAddItem"
    >添加订单流程</el-button
  >
  <el-drawer v-model="drawer" :direction="direction">
    <template #title>
      <h4>添加订单流程</h4>
    </template>
    <template #default>
      <div v-if="options.length != 0">
        <div>
          <el-form
            ref="formRef"
            :model="dynamicValidateForm"
            label-width="120px"
            class="demo-dynamic"
          >
            <el-form-item
              label="订单模式"
              prop="orderModel"
              :rules="[
                {
                  required: true,
                  message: '请选择订单模式',
                  trigger: 'blur',
                },
              ]"
            >
              <el-select
                v-model="dynamicValidateForm.orderModel"
                placeholder="请选择订单模式"
              >
                <el-option
                  v-for="(item, index) in optionsForm"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item
              v-for="(domain, index) in dynamicValidateForm.domains"
              :key="domain.key"
              :label="'流程' + (index + 1)"
              :prop="'domains.' + index + '.value'"
              :rules="{
                required: true,
                message: '流程名称不能为空',
                trigger: 'blur',
              }"
            >
              <el-input v-model="domain.value" />
              <el-button
                type="danger"
                class="mt-2"
                @click.prevent="removeDomain(domain)"
                >删除</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div v-else><el-empty description="暂无可添加数据" /></div>
    </template>
    <template #footer>
      <div style="flex: auto">
        <span v-if="options.length != 0"
          ><el-button type="primary" @click="submitForm(formRef)"
            >确定</el-button
          >
          <el-button type="info" @click="addDomain">添加新流程</el-button></span
        >
        <el-button class="ml-3" @click="cancelClick">取消</el-button>
      </div>
    </template>
  </el-drawer>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted } from "vue";
import { ElMessage, ElMessageBox, FormInstance } from "element-plus";
import VueEvent from "@/utils/event";
import { addProcessBatch } from "@/api/order";

const drawer = ref(false);
const direction = ref("rtl");
const formRef = ref<FormInstance>();
const options: any = ref([]);
const optionsForm: any = ref([]);
const dynamicValidateForm = reactive<{
  domains: DomainItem[];
  orderModel: any;
}>({
  domains: [
    {
      key: 1,
      value: "",
    },
  ],
  orderModel: "",
});

interface DomainItem {
  key: number;
  value: string;
}

VueEvent.on("orderModelOptions", (value) => {
  options.value = value;
  optionsForm.value = [];
  options.value.forEach((item: any, index: any) => {
    optionsForm.value.push({
      label: item.label,
      value: index,
    });
  });
});
const removeDomain = (item: DomainItem) => {
  const index = dynamicValidateForm.domains.indexOf(item);
  if (index !== -1) {
    dynamicValidateForm.domains.splice(index, 1);
  }
};

const addDomain = () => {
  dynamicValidateForm.domains.push({
    key: Date.now(),
    value: "",
  });
};

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      ElMessageBox.confirm(
        `您确定添加模式 ${
          options.value[dynamicValidateForm.orderModel].value.orderModelName
        } 的流程吗 ?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }
      )
        .then(() => {
          let form: any = [];
          let model =
            options.value[dynamicValidateForm.orderModel].value.orderModelId;
          dynamicValidateForm.domains.forEach((item) => {
            form.push({
              orderModelId: model,
              orderProcessName: item.value,
            });
          });
          addProcessBatch(form).then((res) => {
            if (res.status == 200) {
              VueEvent.emit(
                "addProcessBatchSuccess",
                new Date().getTime().toString()
              );
              drawer.value = false;
              ElMessage({
                message: "添加成功",
                type: "success",
                duration: 2000,
              });
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
const onAddItem = () => {
  drawer.value = true;
};
const cancelClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  drawer.value = false;
};
</script>
