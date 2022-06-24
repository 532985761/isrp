<template>
  <el-row :gutter="10" class="bg-slate-200 h-800px">
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"
      ><div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1">
      <div style="border: px solid #ccc;text-align:center" class="h-300px w-190px bg-stone-300">
        <div
          v-for="i in 4"
          :key="i"
          style="display: inline-block; vertical-align: top;border:1px solid black"
          class="ml-2 mt-1 w-66px h-26px"
        >
          <span class="font-serif;font-bold" style="color:black;font-size:12px">商品名称</span> 
        </div>
      </div>
    </el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11"></el-col>
  </el-row>
</template>

<script lang="ts" setup>
import { ref } from "vue";
</script>

<style>
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
