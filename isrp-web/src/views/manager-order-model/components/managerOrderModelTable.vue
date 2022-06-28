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
</template>
<script lang="ts" setup>
import { onMounted, watch, ref, toRef } from "vue";
import { queryOrderModelByPage, deleteOrderModel } from "@/api/order";
import { ElMessage, ElMessageBox } from "element-plus";

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
</script>
