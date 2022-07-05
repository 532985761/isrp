<template>
  <div v-if="loading == true">
    <div
      v-loading="loading"
      style="width: 100%; margin-top: 50px"
      element-loading-text="Loading..."
    ></div>
  </div>
  <div v-else>
    <div v-if="stepData.length == options.length">
      <el-empty style="width: 100%" />
    </div>
    <div v-else class="mt-5 text-medium text-slate-500 font-bold">
      <div v-for="(item, index) in stepData" :key="index">
        <div v-if="item.orderProcessList.length != 0">
          <div class="my-1">模式名称：{{ item.orderModelName }}</div>
          <div class="my-1">
            {{ "操\u3000\u3000作："
            }}<el-button type="primary" size="small" @click="onEditItem(item)">
              编辑 </el-button
            ><el-button type="danger" size="small" @click="deleteProcess(item)">
              删除
            </el-button>
          </div>
          <div class="my-1">模式流程：</div>
          <el-steps :space="200" :active="item.orderProcessList.length">
            <el-step
              v-for="(item2, index2) in item.orderProcessList"
              :key="index2"
              :title="'步骤 ' + (index2 + 1)"
              :description="item2.orderProcessName"
            />
          </el-steps>
          <el-divider>
            <el-icon><star-filled /></el-icon>
          </el-divider>
        </div>
      </div>
    </div>
    <el-drawer v-model="drawer" :direction="direction">
      <template #title>
        <h4>编辑订单流程</h4>
      </template>
      <template #default>
        <div>
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
                  disabled
                >
                  <el-option
                    :label="dynamicValidateForm.orderModel.orderModelName"
                    :value="dynamicValidateForm.orderModel.orderModelId"
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
      </template>
      <template #footer>
        <div style="flex: auto">
          <span v-if="options.length != 0"
            ><el-button type="primary" @click="submitForm(formRef)"
              >确定</el-button
            >
            <el-button type="info" @click="addDomain"
              >添加新流程</el-button
            ></span
          >
          <el-button class="ml-3" @click="cancelClick">取消</el-button>
        </div>
      </template>
    </el-drawer>
  </div>
</template>
<script lang="ts" setup>
import { StarFilled } from "@element-plus/icons-vue";
import {
  queryModelAndProcess,
  deleteProcessByModelId,
  editBatchProcess,
} from "@/api/order";
import { onMounted, ref, reactive } from "vue";
import VueEvent from "@/utils/event";
import { ElMessage, ElMessageBox, FormInstance } from "element-plus";
import { log } from "console";

const drawer = ref(false);
const direction = ref("rtl");
const formRef = ref<FormInstance>();
const optionsForm: any = ref([]);
const options: any = ref([{}]);
const stepData: any = ref([]);
const deleteData: any = ref([]);
const addData: any = ref([]);
const editData: any = ref([]);
const loading: any = ref(true);
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

onMounted(() => {
  initData();
});

VueEvent.on("addProcessBatchSuccess", () => {
  initData();
});

const initData = () => {
  loading.value = true;
  queryModelAndProcess().then((res) => {
    if (res.status == 200) {
      const dataArray = res.data;
      stepData.value = dataArray;
      options.value.splice(0, options.value.length);
      dataArray.forEach((element: any) => {
        if (element.orderProcessList.length == 0) {
          options.value.push({
            label: element.orderModelName,
            value: {
              orderModelId: element.orderModelId,
              orderModelName: element.orderModelName,
            },
          });
          optionsForm.value.splice(0, optionsForm.value.length);
          options.value.forEach((item: any, index: any) => {
            optionsForm.value.push({
              label: item.label,
              value: index,
            });
          });
        }
      });
      VueEvent.emit(
        "orderModelOptions",
        JSON.parse(JSON.stringify(options.value))
      );
    }
    loading.value = false;
  });
};
const deleteProcess = (item: any) => {
  ElMessageBox.confirm(
    `您确定删除订单模式： ${item.orderModelName} 的流程吗?`,
    "提示",
    {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
    }
  )
    .then(() => {
      deleteProcessByModelId(item.orderModelId).then((res) => {
        if (res.status == 200) {
          initData();
          ElMessage({
            message: "删除成功",
            type: "success",
            duration: 2000,
          });
        }
      });
    })
    .catch(() => {
      // catch error
    });
};
const onEditItem = (item: any) => {
  dynamicValidateForm.orderModel = item;
  dynamicValidateForm.domains.splice(0, dynamicValidateForm.domains.length);
  deleteData.value.splice(0, deleteData.value.length);
  addData.value.splice(0, addData.value.length);
  item.orderProcessList.forEach((item2: any) => {
    dynamicValidateForm.domains.push({
      key: item2.orderProcessId,
      value: item2.orderProcessName,
    });
    editData.value = dynamicValidateForm.domains;
  });

  drawer.value = true;
};
const cancelClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  drawer.value = false;
};
const addDomain = () => {
  let item = {
    key: Date.now(),
    value: "",
  };
  addData.value.push(item);
  dynamicValidateForm.domains.push(item);
};
const removeDomain = (item: DomainItem) => {
  const index = dynamicValidateForm.domains.indexOf(item);
  const index2 = addData.value.indexOf(item);
  if (index2 !== -1 && index != -1) {
    dynamicValidateForm.domains.splice(index, 1);
    addData.value.splice(index2, 1);
    return;
  }
  if (index !== -1) {
    deleteData.value.push(item);
    dynamicValidateForm.domains.splice(index, 1);
  }
};
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      ElMessageBox.confirm(
        `您确定修改模式 ${dynamicValidateForm.orderModel.orderModelName} 的流程吗 ?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }
      )
        .then(() => {
          let deleteItems: any = [];
          let addItems: any = [];
          let editItems: any = [];
          let model = dynamicValidateForm.orderModel.orderModelId;
          editData.value.forEach((item: any, index: any) => {
            if (!addData.value.find((item2: any) => item.key == item2.key)) {
              editItems.push({
                orderModelId: model,
                orderProcessName: item.value,
                orderProcessId: item.key,
              });
            }
          });
          deleteData.value.forEach((item: any) => {
            deleteItems.push({
              orderModelId: model,
              orderProcessName: item.value,
              orderProcessId: item.key,
            });
          });
          addData.value.forEach((item: any) => {
            addItems.push({
              orderModelId: model,
              orderProcessName: item.value,
            });
          });
          let form = {
            delOrderProcess: deleteItems,
            addOrderProcess: addItems,
            editOrderProcess: editItems,
          };
          editBatchProcess(form).then((res) => {
            if (res.status == 200) {
              initData();
              drawer.value = false;
              ElMessage({
                message: "修改成功",
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
</script>
