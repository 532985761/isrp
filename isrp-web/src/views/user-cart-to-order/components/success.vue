<template>
  <el-row>
    <el-col :lg="4"></el-col>
    <el-col :lg="16"
      ><el-card style="text-align: center" class="text-red-500 text-2xl mt-8">
        恭喜您！您的订单已经成功生成，请及时付款!  <br />
        <router-link to="/isrpUser/userCenter/waitPay">   <el-button type="primary">查看我的订单</el-button></router-link>
     </el-card
      ></el-col 
    >
  </el-row>
</template>
<script lang="ts" setup></script>
