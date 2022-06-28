<template>
  <div>
    <el-form-item label="订单搜索" style="margin-left:80px">
      <el-input
        placeholder="请输入你要搜索的订单编号..."
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
    </el-form-item>
  </div>
  <el-table :data="tableData" style="width: 100%;text-align: center;">
    <el-table-column fixed prop="date" label="下单时间" width="150" />
    <el-table-column label="订单编号" width="120" />
    <el-table-column label="商品名称" width="120" />
    <el-table-column label="商品图片" width="120" />
    <el-table-column label="商品价格" width="120" />
    <el-table-column label="订单状态" width="120" />
    <el-table-column label="实际付款" width="120" />
    <el-table-column label="支付方式编号" width="120" />
    <el-table-column label="物流公司编号" width="120" />
    <el-table-column label="收货人姓名" width="120" />
    <el-table-column label="收货人电话" width="120" />
    <el-table-column label="收货人邮箱" width="120" />
    <el-table-column label="收货人详细地址" width="250" />
    <el-table-column label="发货时间" width="120" />
    <el-table-column label="收货时间" width="120" />
    <el-table-column label="评论时间" width="120" />
    <el-table-column fixed="right" label="操作" width="200">
      <template #default>
        <el-button link type="warning" size="small">删除订单</el-button>
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