<template>
  <el-table :data="waitOrderInfo" style="width: 100%">
    <el-table-column fixed prop="orderId" label="订单号" width="260" />
    <el-table-column prop="name" label="收货人" width="120">
      <template #default="scope">
        {{ scope.row.receiverName }}
      </template>
    </el-table-column>
      <el-table-column prop="name" label="收货电话" width="120">
      <template #default="scope">
        {{ scope.row.receiverPhone }}
      </template>
    </el-table-column>
    <el-table-column prop="name" label="收货地址" width="380">
      <template #default="scope">
        {{ scope.row.receiverProvince }}-{{ scope.row.receiverCity }}-{{
          scope.row.receiverArea
        }}- {{ scope.row.receiverStreet }}-{{ scope.row.receiverDetailAddress }}
      </template>
    </el-table-column>
    <el-table-column prop="name" label="当前订单状态" width="420">
      <template #default>
        <el-tag type="danger">未支付</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="220">
      <template #default="scope">
        <el-popconfirm title="确认支付此订单吗？" @confirm="payOrderFun(scope.row.orderId)">
          <template #reference>
            <el-button link type="primary" size="small" 
              >支付</el-button
            >
          </template>
        </el-popconfirm>
        <el-button link type="danger" size="small">取消订单</el-button>
        <el-button link type="warning" size="small">查看详情</el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- {{ waitOrderInfo }} -->
</template>

<script lang="ts" setup>
import { waitPayOrder, payOrder } from "@/api/order";
import { onBeforeMount } from "@vue/runtime-core";
import { ref } from "vue";
import { ElNotification } from "element-plus";

const handleClick = () => {
  console.log("click");
};
const tableData = [];
onBeforeMount(() => {
  waitPayOrderFun();
});
//获取未支付订单信息
const waitOrderInfo = ref([
  {
    orderId: "3116172027363328",
    goodsId: 45,
    userId: "430818627420160",
    typeId: 1,
    logisticsCompanyId: 1,
    createTime: "2022-07-06 09:24:34",
    orderStatus: 0,
    payTime: null,
    goodsTotalPrice: 168,
    goodsPayReal: 168,
    receiverName: "郜承弼",
    receiverPhone: "1111",
    receiverEmail: "",
    receiverProvince: "内蒙古自治区",
    receiverCity: "包头市",
    receiverArea: "东河区",
    receiverStreet: "111",
    receiverDetailAddress: "111",
    confirmStatus: 0,
    deliveryTime: null,
    receiveTime: null,
    modifyTime: null,
    rentDays: 3,
    rentRealDays: null,
    shopUserId: "431188825079808",
  },
]);
const waitPayOrderFun = () => {
  waitPayOrder().then((res) => {
    waitOrderInfo.value = res.data;
  });
};
//支付订单
const payOrderFun = ((id)=>{
  payOrder(id).then((res) => {
       ElNotification({
        title: "提示消息",
        message: "恭喜您成功支付！订单已生效！",
        type: "success",
      });
      waitPayOrderFun();
  })
})
</script>
