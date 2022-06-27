<template>
  <div>
    <manager-order-model-title />
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
