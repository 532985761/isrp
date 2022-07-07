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
      <template #default="scope">
        <el-tag type="warning" v-if="scope.row.confirmStatus == 1"
          >进行中</el-tag
        >
        <el-tag type="danger" v-if="scope.row.confirmStatus == 0"
          >等待发货</el-tag
        >
        <el-tag type="success" v-if="scope.row.confirmStatus == 3"
          >已完成</el-tag
        >
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="220">
      <template #default="scope">
        <!-- <el-button link type="danger" size="small" v-if="">退租</el-button> -->
        <el-button
          link
          type="warning"
          size="small"
          @click="detail(scope.row.orderId)"
          >查看详情</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-dialog
    v-model="centerDialogVisible"
    width="80%"
    center
    :close-on-click-modal="false"
  >
    <el-row>
      <el-col :lg="24">
        <span class="font-bold">订单号：{{ orderDetail.order.orderId }}</span>
        <span class="text-red-600 text-3xl ml-240px font-bold" v-if="orderDetail.order.confirmStatus == 3">
            
            该订单已经完成啦！！！</span
            ><span class="text-yellow-600 text-3xl ml-240px font-bold" v-else>
            
            该订单正在进行中！！！</span
            >
        <el-card class="mt-2"
          ><div class="font-bold text-2xl">
            订单模式:
            <span class="text-red-500 text-xl ml-2">
              {{ orderDetail.model.orderModelName }}</span
            >
            
            
          </div>
          <el-steps
            :space="200"
            :active="orderDetail.nowId + 1"
            finish-status="success"
          >
            <el-step
              class="mt-5"
              v-for="(i, index) in orderDetail.process"
              :key="index"
              :title="i.orderProcessName"
            /> </el-steps
        ></el-card>
        <!-- 租用信息 -->
        <el-card class="mt-3">
          <div class="font-bold text-2xl">租用详情</div>
          <el-divider></el-divider>
          <div class="text-lg font-serif">
            <span>租用时长:</span
            ><span class="text-red-600 ml-3">{{
              orderDetail.order.rentDays
            }}</span>
            天 <span class="ml-8">租用总价:</span
            ><span class="text-red-600 ml-3">{{
              orderDetail.order.goodsPayReal
            }}</span>
            元
            <br />
            <span class="mt-5"
              >收货地址信息：<span class="text-pink-600"
                >{{ orderDetail.order.receiverProvince }}-{{
                  orderDetail.order.receiverCity
                }}-{{ orderDetail.order.receiverArea }}-{{
                  orderDetail.order.receiverStreet
                }}-{{ orderDetail.order.receiverDetailAddress }}</span
              ></span
            ><span class="ml-10"
              >收货人电话：<span class="text-rose-500">{{
                orderDetail.order.receiverPhone
              }}</span>
            </span>
            <span class="ml-10"
              >收货人姓名：<span class="text-orange-500">{{
                orderDetail.order.receiverName
              }}</span>
            </span>
          </div>
        </el-card>
        <!-- 商品详情卡片 -->
        <el-card class="mt-4">
          <div class="font-bold text-2xl">商品详情</div>
          <el-divider></el-divider>
          <el-row
            ><el-col :lg="8">
              <!-- <el-image class="h-220px"
                :src="orderDetail.goods.goodsImg.toString().split(',')[0]"
              ></el-image> -->
              <el-carousel indicator-position="outside " height="200px">
                <el-carousel-item
                  v-for="item in orderDetail.goods.goodsImg
                    .toString()
                    .split(',')"
                  :key="item"
                >
                  <el-image :src="item"></el-image>
                </el-carousel-item>
              </el-carousel>
            </el-col>
            <el-col :lg="1"></el-col>
            <el-col :lg="15">
              <div style="font-weight: bold">
                <span> 商品名称：{{ orderDetail.goods.goodsName }}</span>
              </div>
              <div class="mt-4">
                <span> 商品描述：{{ orderDetail.goods.goodsDesc }}</span>
              </div>
              <el-descriptions border align="center" class="mt-3">
                <el-descriptions-item label="商户姓名" align="center">{{
                  orderDetail.user.nickname
                }}</el-descriptions-item>

                <el-descriptions-item label="地区" align="center">{{
                  orderDetail.user.addressCity
                }}</el-descriptions-item>
                <el-descriptions-item label="租赁方式" align="center">
                  <el-tag size="small">{{
                    orderDetail.model.orderModelName
                  }}</el-tag>
                </el-descriptions-item>

                <el-descriptions-item label="租赁价格" align="center"
                  >{{
                    orderDetail.goods.rentPricePerDay
                  }}元/天</el-descriptions-item
                >
                <!-- 以租代售 -->
                <el-descriptions-item
                  label="商品总价"
                  align="center"
                  v-if="orderDetail.model.orderModelId !== 13"
                >
                  <el-tag type="warning"
                    >{{ orderDetail.goods.goodsPrice }}元</el-tag
                  >
                </el-descriptions-item>

                <el-descriptions-item
                  label="租赁时间上限"
                  align="center"
                  v-if="orderDetail.model.orderModelId !== 13"
                >
                  <el-tag type="danger" size="large"
                    >{{ orderDetail.goods.rentLimitDays }}天</el-tag
                  >
                </el-descriptions-item>
              </el-descriptions>
            </el-col></el-row
          >
        </el-card>
        <el-card class="mt-3"
          ><div class="font-bold text-2xl">用户操作</div>
          <!-- <div class="mt-3" style="text-align: center">
            本订单为--<span class="text-red-500 font-bold">{{
              orderDetail.model.orderModelName
            }}</span
            >--模式 , 现在正处于--<span class="text-red-500 font-bold">{{
              orderDetail.nowProcessName.orderProcessName
            }}</span
            >--阶段 , 下一阶段--<span class="text-red-500 font-bold">{{
              orderDetail.nextProcessName
            }}</span
            >--
          </div> -->
          <div
            style="text-align: center"
            class="mt-5"
            v-if="orderDetail.model.orderModelId == 1"
          >
            <el-button
              type="danger"
              @click="exitOrderFun(orderDetail.order.orderId)"
              >我想退租</el-button
            >
            <el-button type="warning">我想购买此商品</el-button>
          </div>
          <div
            style="text-align: center"
            class="mt-5"
            v-if="orderDetail.model.orderModelId == 12"
          >
            <el-button type="danger">我想退租</el-button>
          </div>
          <div
            style="text-align: center"
            class="mt-5"
            v-if="orderDetail.model.orderModelId == 13"
          >
            <el-button type="warning">我想续租</el-button>
            <el-button type="danger">我想退租</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script lang="ts" setup>
import {
  waitPayOrder,
  hasPayOrder,
  getOrderDetail,
  exitOrder,
} from "@/api/order";
import { onBeforeMount } from "@vue/runtime-core";
import { ref } from "vue";
import { ElNotification } from "element-plus";

const centerDialogVisible = ref(false);
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
  hasPayOrder().then((res) => {
    waitOrderInfo.value = res.data;
  });
};
//支付订单
const payOrderFun = (id) => {};
//订单详情
const orderDetail = ref({
  nowProcessName: {
    orderProcessId: 16,
    orderModelId: 12,
    orderProcessName: "已支付",
  },
  user: {
    userId: "431188825079808",
    nickname: "wbw",
    headerImg:
      "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
    password: null,
    role: 1,
    phone: "未知",
    email: "wbw@163.com",
    idCardNum: "未知",
    sex: 2,
    status: 1,
    addressCity: "唐山",
    birth: null,
    createTime: "2022-06-25 10:04:19",
    sign: "无",
  },
  process: [
    { orderProcessId: 12, orderModelId: 12, orderProcessName: "发布商品" },
    { orderProcessId: 13, orderModelId: 12, orderProcessName: "待购买" },
    { orderProcessId: 14, orderModelId: 12, orderProcessName: "下单" },
    { orderProcessId: 15, orderModelId: 12, orderProcessName: "待支付" },
    { orderProcessId: 16, orderModelId: 12, orderProcessName: "已支付" },
    { orderProcessId: 17, orderModelId: 12, orderProcessName: "商家发货" },
    { orderProcessId: 18, orderModelId: 12, orderProcessName: "配送" },
    { orderProcessId: 19, orderModelId: 12, orderProcessName: "租赁中" },
    { orderProcessId: 20, orderModelId: 12, orderProcessName: "订单结束" },
  ],
  goods: {
    goodsId: 4,
    goodsCategorySecondId: 2,
    userId: "431188825079808",
    goodsName: "p30111",
    goodsDesc: "我再做测试3",
    goodsImg:
      "http://192.168.1.103:9527/isrpGoods/file/images/2bcb454d-2518-43c2-8078-655c7e500a1d.jpg,http://192.168.1.103:9527/isrpGoods/file/images/6c9ce2dd-0914-40b9-b920-39f6913286c8.jpg",
    goodsPrice: 5000,
    goodsSaleCount: 54,
    orderModelId: 12,
    goodsStatus: 0,
    createTime: "2022-06-22 09:58:58",
    rentLimitDays: 20,
    rentPricePerDay: 45,
  },
  nowId: 2,
  model: { orderModelId: 12, orderModelName: "以租代售" },
  processNowId: 16,
  order: {
    orderId: "3125704757608448",
    goodsId: 4,
    userId: "430818627420160",
    typeId: 1,
    logisticsCompanyId: 2,
    createTime: "2022-07-06 10:02:27",
    orderStatus: 1,
    payTime: null,
    goodsTotalPrice: 168,
    goodsPayReal: 168,
    receiverName: "123",
    receiverPhone: "123",
    receiverEmail: "",
    receiverProvince: "北京市",
    receiverCity: "市辖区",
    receiverArea: "东城区",
    receiverStreet: "123",
    receiverDetailAddress: "123",
    confirmStatus: 0,
    deliveryTime: null,
    receiveTime: null,
    modifyTime: null,
    rentDays: 3,
    rentRealDays: null,
    shopUserId: "431188825079808",
  },
});
const detail = (id) => {
  getOrderDetail(id).then((res) => {
    orderDetail.value = res.data;
    centerDialogVisible.value = true;
    console.log(orderDetail.value);
  });
};
//退租
const exitOrderFun = (orderId) => {
  exitOrder(orderId).then((res) => {
    ElNotification({
      title: "提示消息",
      message: "恭喜您退租成功！",
      type: "success",
    });
    getOrderDetail(orderId).then((res) => {
      orderDetail.value = res.data;
      centerDialogVisible.value = true;
      console.log(orderDetail.value);
    });
  });
};
</script>
