<template>
  <div v-if="loading == true">
    <div
      v-loading="loading"
      style="width: 100%; margin-top: 50px"
      element-loading-text="Loading..."
    ></div>
  </div>
  <div v-else>
    <div v-if="total == 0">
      <el-empty style="width: 100%" />
    </div>
    <div v-else>
      <el-table :data="tableData" style="width: 100%" height="350">
        <el-table-column prop="orderModelId" label="模式编号" width="550" />
        <el-table-column
          fixed
          prop="orderModelName"
          label="模式名称"
          width="100"
        />
        <el-table-column label="操作" width="550">
          <template #default="scope">
            <el-button
              link
              type="primary"
              size="small"
              @click.prevent="changeEditOrderModel(scope.row)"
            >
              修改
            </el-button>
            <el-button
              link
              type="danger"
              size="small"
              @click.prevent="deleteRow(scope.row)"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="flex justify-center m-1">
        <el-dropdown size="small" @command="changePageSize">
          <el-button size="small">
            页大小: {{ pageSize
            }}<el-icon class="el-icon--right"><arrow-down /></el-icon>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                v-for="(item, index) in pageSizeArray"
                :key="index"
                :command="item"
                >{{ item }}</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
      <div class="flex justify-center">
        <el-pagination
          :currentPage="currentPage"
          :page-size="pageSize"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, prev, pager, next"
          :total="total"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </div>
  <el-drawer v-model="drawer" :direction="direction">
    <template #title>
      <h4>修改订单模式</h4>
    </template>
    <template #default>
      <div>
        <el-form
          label-position="top"
          ref="formRef"
          :model="validateForm"
          label-width="100px"
          class="demo-ruleForm"
          size="large"
        >
          <el-form-item
            label="模式编号"
            prop="orderModelId"
            :rules="[{ required: true, message: '模式编号不能为空' }]"
          >
            <el-input
              disabled
              v-model.number="validateForm.orderModelId"
              type="text"
              autocomplete="off"
            />
          </el-form-item>
          <el-form-item
            label="模式名称"
            prop="orderModelName"
            :rules="[{ required: true, message: '模式名称不能为空' }]"
          >
            <el-input
              v-model.string="validateForm.orderModelName"
              type="text"
              autocomplete="off"
            />
          </el-form-item>
        </el-form>
      </div>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button type="primary" @click="editRow()">确定</el-button>
        <el-button @click="cancelClick(formRef)">取消</el-button>
      </div>
    </template>
  </el-drawer>
</template>
<script lang="ts" setup>
import { onMounted, watch, ref, toRef } from "vue";
import {
  queryOrderModelByPage,
  deleteOrderModel,
  editOrderModel,
} from "@/api/order";
import { ElMessage, ElMessageBox, FormInstance } from "element-plus";

const props = defineProps({
  timestamp: String,
});
let timeStamp = toRef(props, "timestamp");
const tableData = ref([]);
const small = ref(false);
const disabled = ref(false);
const background = ref(false);
const currentPage = ref(1);
const pageSize = ref(5);
const total = ref(0);
const loading = ref(true);
const pageSizeArray = [
  5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
];
const drawer = ref(false);
const direction = ref("rtl");
const formRef = ref<FormInstance>();
const originForm = ref({
  orderModelId: "",
  orderModelName: "",
});
const validateForm = ref({
  orderModelId: "",
  orderModelName: "",
});

onMounted(() => {
  initData();
});

watch(timeStamp, () => {
  initData();
});

const initData = () => {
  loading.value = true;
  queryOrderModelByPage(currentPage.value, pageSize.value).then((res) => {
    if (res.status == 200) {
      tableData.value = res.data.content;
      total.value = res.data.totalElements;
      currentPage.value = res.data.pageable.pageNumber + 1;
      pageSize.value = res.data.pageable.pageSize;
    }
    loading.value = false;
  });
};
const changePageSize = (command: any) => {
  pageSize.value = command;
  initData();
};
const handleCurrentChange = (val: number) => {
  currentPage.value = val;
  initData();
};
const changeEditOrderModel = (scope: any) => {
  originForm.value = JSON.parse(JSON.stringify(scope));
  validateForm.value = JSON.parse(JSON.stringify(originForm.value));
  drawer.value = true;
};

const editRow = () => {
  ElMessageBox.confirm(
    `您确定修改订单模式 ${originForm.value.orderModelName} 为 ${validateForm.value.orderModelName} ?`,
    "提示",
    {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
    }
  )
    .then(() => {
      editOrderModel(validateForm.value).then((res) => {
        if (res.status == 200) {
          initData();
          ElMessage({
            message: "修改成功",
            type: "success",
            duration: 2000,
          });
        }
        drawer.value = false;
      });
    })
    .catch(() => {
      // catch error
    });
};
const deleteRow = (scope: any) => {
  ElMessageBox.confirm(
    `您确定删除订单模式： ${scope.orderModelName} ?`,
    "提示",
    {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
    }
  )
    .then(() => {
      deleteOrderModel(scope.orderModelId).then((res) => {
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
const cancelClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  drawer.value = false;
};
</script>
