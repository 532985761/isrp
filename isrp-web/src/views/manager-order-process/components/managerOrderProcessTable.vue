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
            }}<el-button
              type="primary"
              size="small"
              @click="
                ElMessage({
                  message: '没写呢，点啥点',
                  type: 'error',
                  duration: 2000,
                })
              "
            >
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
  </div>
</template>
<script lang="ts" setup>
import { StarFilled } from "@element-plus/icons-vue";
import { queryModelAndProcess, deleteProcessByModelId } from "@/api/order";
import { onMounted, ref } from "vue";
import VueEvent from "@/utils/event";
import { ElMessage, ElMessageBox } from "element-plus";

const options: any = ref([{}]);
const stepData: any = ref([]);
const loading: any = ref(true);

onMounted(() => {
  initData();
});

VueEvent.on("addProcessBatchSuccess", (value) => {
  initData();
});

const initData = () => {
  loading.value = true;
  queryModelAndProcess().then((res) => {
    if (res.status == 200) {
      const dataArray = res.data;
      stepData.value = dataArray;
      options.value.splice(0, options.value.length);
      dataArray.forEach((element) => {
        if (element.orderProcessList.length == 0) {
          options.value.push({
            label: element.orderModelName,
            value: {
              orderModelId: element.orderModelId,
              orderModelName: element.orderModelName,
            },
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
</script>
