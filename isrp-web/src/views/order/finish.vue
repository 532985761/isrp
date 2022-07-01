<template>
  <div>
    <el-form-item label="订单搜索" style="margin-left:80px">
      <el-input
        placeholder="请输入你要搜索的订单编号..."
        clearable
        type="text"
        class="w-200"
        v-model="orderIdFun"
      />
      <el-button
        type="success"
        :icon="Search"
        round
        class="w-25"
        style="margin-left:20px"
        @click="searchOrderByOrderId(orderIdFun)"
      >搜索</el-button>
    </el-form-item>
  </div>
  <el-table :data="tableData" style="width: 100%;text-align: center;" height="595">
    <el-table-column fixed prop="createTime" label="下单时间" width="150" />
    <el-table-column prop="orderId" label="订单编号" width="120" />
    <el-table-column prop="orderStatus" label="订单状态" width="120" >
      <template #default="scope">
            <el-tag type="warning" effect="dark" v-if="scope.row.orderStatus==0">未付款</el-tag>
            <el-tag type="success" effect="dark" v-if="scope.row.orderStatus==1">已付款</el-tag>
        </template>
    </el-table-column>
    <el-table-column prop="payTime" label="付款时间" width="120"/>
    <el-table-column prop="goodsTotalPrice" label="商品总价" width="120" />
    <el-table-column prop="goodsPayReal" label="实际付款" width="120" />
    <el-table-column prop="confirmStatus" label="确认订单状态" width="120">
      <template #default="scope">
            <el-tag type="danger" effect="dark" v-if="scope.row.confirmStatus==0">未发货</el-tag>
            <el-tag type="warning" effect="dark" v-if="scope.row.confirmStatus==1">已发货</el-tag>
            <el-tag type="" effect="dark" v-if="scope.row.confirmStatus==2">已收货</el-tag>
            <el-tag type="success" effect="dark" v-if="scope.row.confirmStatus==3">已完成</el-tag>
        </template>
    </el-table-column>
    <el-table-column prop="receiverName" label="收货人姓名" width="120" />
    <el-table-column prop="receiverPhone" label="收货人电话" width="120" />
    <el-table-column prop="receiverEmail" label="收货人邮箱" width="120" />
    <el-table-column prop="receiverDetailAddress" label="收货人详细地址" width="250" />
    <el-table-column prop="rentDays" label="租用时间" width="120" />
    <el-table-column prop="rentRealDays" label="实际租用时间" width="120" />
    <el-table-column fixed="right" label="操作" width="200">
      <template #default="scope">
        <el-button link type="danger" size="small" @click="deleteOrder(scope.row.orderId)" disabled>删除订单</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
<script lang="ts" setup>
import { Search } from "@element-plus/icons-vue";
import { userStore } from "@/store/user";
import { onMounted, reactive, ref } from "vue";
import {selectOrderFinishByShopUserId,selectOrder } from "@/api/order"
import { deleteOrder } from "@/api/order";
import { ElMessage } from "element-plus";
const userstore = userStore();
let tableData: any = ref([]);
const getAllOrderFun = () => {
  selectOrderFinishByShopUserId(userstore.info.userId).then((res: any) => {
    tableData.value = res.data;
  });
};
onMounted(async () => {
  tableData.value = await selectOrderFinishByShopUserId(userstore.info.userId).then(
    (res: any) => res.data
  );
});
const deleteOrderByOrderId = (orderId)=>{
  deleteOrder(orderId).then(()=>{
    getAllOrderFun();
    ElMessage({
      type: 'success',
      message: '删除成功'
    })
  })
}
const orderIdFun = ref();
const searchOrderByOrderId = (orderIdFun) =>{
   if(orderIdFun == '' || orderIdFun == undefined || orderIdFun == null){
      selectOrderFinishByShopUserId(userstore.info.userId).then((res: any) => {
        tableData.value = res.data;
      });
    }else{
      selectOrder(orderIdFun).then((res)=>{
      if(res.data.length == 0 || res.data == null){
        ElMessage({
        type: 'warning',
        message: '抱歉没有此订单'
      })
      }else{
        ElMessage({
          type: 'success',
          message: '查询成功'
        })
        tableData.value = new Array(res.data) ;
      }
   })
  }
}
</script>