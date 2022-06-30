<template>
  <div>
    <manager-order-model-title />
    <el-alert
      title="请注意：删除和修改订单模式会对系统造成较大影响"
      type="error"
    />
    <manager-order-model-add @get-time-stamp="getTimeStamp" />
    <manager-order-model-table :timestamp="timestamp" />
  </div>
</template>
<script lang="ts" setup>
import managerOrderModelTitle from "./components/managerOrderModelTitle.vue";
import managerOrderModelAdd from "./components/managerOrderModelAdd.vue";
import managerOrderModelTable from "./components/managerOrderModelTable.vue";
import { ref } from "vue";

const timestamp = ref("");

const getTimeStamp = (nowTime: string) => {
  timestamp.value = nowTime;
};
</script>
