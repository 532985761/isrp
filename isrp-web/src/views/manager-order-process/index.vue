<template>
  <div>
    <manager-order-process-title />
    <el-alert
      title="请注意：删除和修改订单流程会对系统造成较大影响"
      type="error"
    />
    <manager-order-process-add />
    <manager-order-process-table />
  </div>
</template>
<script lang="ts" setup>
import managerOrderProcessTitle from "./components/managerOrderProcessTitle.vue";
import managerOrderProcessTable from "./components/managerOrderProcessTable.vue";
import managerOrderProcessAdd from "./components/managerOrderProcessAdd.vue";
</script>
