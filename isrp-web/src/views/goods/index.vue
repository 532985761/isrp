<template>
  <div>
    <el-form-item label="商品搜索" style="margin-left:80px">
      <el-input
        placeholder="请输入你要搜索的商品名称..."
        clearable
        type="text"
        class="w-200"
      />
      <el-button
        type="success"
        :icon="Search"
        round
        class="w-25"
        style="margin-left:20px"
      >搜索</el-button>
      <el-button
        type="primary"
        round
        class="w-25"
      ><el-icon><Upload /></el-icon>发布商品</el-button>
    </el-form-item>
  </div>
  <el-table :data="tableData" style="width: 100%;text-align: center;">
    <el-table-column fixed prop="goosId" label="商品序号" width="150" />
    <el-table-column prop="goodsNa" label="商品名称" width="120" />
    <el-table-column label="商品图片" width="120" />
    <el-table-column label="商品价格" width="120" />
    <el-table-column label="商品描述" width="120" />
    <el-table-column label="商品状态" width="120" />
    <el-table-column label="销量" width="120" />
    <el-table-column label="发布时间" width="120" />
    <el-table-column fixed="right" label="操作" width="200">
      <template #default>
        <el-button link type="primary" size="small">修改商品</el-button>
        <el-button link type="warning" size="small">删除商品</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
<script lang="ts" setup>
import { Search } from "@element-plus/icons-vue";
const tableData = [
  {
    date: "2016-05-03",
    name: "Tom",
    state: "California",
    city: "Los Angeles",
    address: "No. 189, Grove St, Los Angeles",
    zip: "CA 90036",
    tag: "Home",
  },
  {
    date: "2016-05-02",
    name: "Tom",
    state: "California",
    city: "Los Angeles",
    address: "No. 189, Grove St, Los Angeles",
    zip: "CA 90036",
    tag: "Office",
  },
  {
    date: "2016-05-04",
    name: "Tom",
    state: "California",
    city: "Los Angeles",
    address: "No. 189, Grove St, Los Angeles",
    zip: "CA 90036",
    tag: "Home",
  },
  {
    date: "2016-05-01",
    name: "Tom",
    state: "California",
    city: "Los Angeles",
    address: "No. 189, Grove St, Los Angeles",
    zip: "CA 90036",
    tag: "Office",
  },
];
</script>