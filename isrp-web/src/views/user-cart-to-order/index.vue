<template>
  <!-- 面包屑导航 -->
  <el-row :gutter="10">
    <el-col :lg="2"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-4">
      <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item :to="{ path: '/isrpUser/index' }">
          首页</el-breadcrumb-item
        >
        <el-breadcrumb-item>租赁中心</el-breadcrumb-item>
        <el-breadcrumb-item to="/isrpUser/userCart"
          >我的购物车</el-breadcrumb-item
        >
        <el-breadcrumb-item>商品下单</el-breadcrumb-item
        ><el-breadcrumb-item>核对订单信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
  </el-row>
  <el-row>
    <el-col :lg="2"></el-col>
    <el-col :lg="20" class="mt-4">
      <div class="w-800px ml-626px -mt-40px">
        <el-steps :space="200" :active="2" finish-status="success">
          <el-step title="选取商品" />
          <el-step title="购物车筛选" />
          <el-step title="下单" />
          <el-step title="付款" />
        </el-steps>
      </div>
      <el-card
        v-loading="loading"
        element-loading-text="正在为您生成订单，请耐心等待"
      >
        <div>
          <span style="font-weight: bold; font-size: 15px">收货人信息</span>
          <div style="font-weight: bold">
            <el-button @click="$router.go(-1)" style="float: right" type="info"
              >返回上一页</el-button
            >
            <el-button
              type="primary"
              style="float: right"
              class="mr-7"
              @click="addDialog = true"
              >新增收货地址</el-button
            >
          </div>

          <div
            class="mb-2 flex items-center text-sm -mt-3"
            v-for="(i, index) in info.userProp"
            :key="index"
          >
            <el-radio-group
              v-model="selectRadio"
              class="ml-4"
              @change="radioValue(i)"
            >
              <el-radio :label="i.propId" size="large">
                收货地址{{ index + 1 }}： {{ i.addressProvince }}
                {{ i.addressCity }} {{ i.addressArea }} {{ i.addressStreet }}
                {{ i.addressDetail }} 收货人姓名：{{
                  i.receiveName
                }}
                收货人电话：{{ i.receivePhone }}</el-radio
              ><br /><br /><br />
            </el-radio-group>
            <el-link type="danger" @click="deleteAddress(i.propId)"
              >删除</el-link
            >
          </div>
        </div>
        <div></div>
        <el-divider></el-divider>
        <!-- 付款方式 -->
        <div>
          <span style="font-weight: bold; font-size: 15px">付款方式</span>
        </div>
        <div class="mt-4 ml-4">
          <el-radio
            v-for="(i, index) in info.payType.content"
            :key="index"
            :label="i.typeId"
            v-model="payType"
            @change="typeName"
            >{{ i.typeName }}</el-radio
          >
        </div>
        <el-divider></el-divider>
        <!-- 租用清单详情 -->
        <div>
          <span style="font-weight: bold; font-size: 15px">详细租用清单</span>
        </div>
        <el-row>
          <el-col :lg="8">
            <el-card class="bg-gray-200 mt-3 h-220px">
              <div style="font-weight: bold; font-size: 13px">
                <span>租用方式：</span>{{ info.model.orderModelName }}
              </div>
              <el-divider></el-divider>
              <div style="font-weight: bold; font-size: 13px">
                <span>配送方式：</span>
                <el-select
                  v-model="logCompany"
                  class="m-2"
                  placeholder="Select"
                  size="small"
                  @change="selectlogCompany"
                >
                  <el-option
                    v-for="item in info.selectVO"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled"
                  />
                </el-select>
              </div>
              <el-divider></el-divider>

              <div style="font-weight: bold; font-size: 13px">
                <span
                  >租用时长：<span class="text-blue-400 text-2xl">{{
                    info.cart.rentDays
                  }}</span>
                  天</span
                >
                <span class="text-blue-500 ml-8">共计：</span>
                <span class="text-red-500 text-2xl">{{ info.cart.total }} </span
                ><span class="text-blue-500"> 元</span>
              </div>
            </el-card>
          </el-col>

          <el-col :lg="16">
            <!-- 先租后买卡片 -->
            <el-card class="mt-3">
              <el-row>
                <el-col :lg="5">
                  <el-carousel indicator-position="outside" height="150px">
                    <el-carousel-item v-for="(i, index) in 3" :key="index">
                      <el-image src="i.goodsImg" class="h-200px"></el-image>
                    </el-carousel-item>
                  </el-carousel>
                </el-col>
                <el-col :lg="1"></el-col>
                <el-col :lg="18">
                  <div class="mt-4">
                    <span> 商品描述：{{ info.goods.goodsDesc }}</span>
                  </div>
                  <el-descriptions border align="center" class="mt-3">
                    <el-descriptions-item label="商户姓名" align="center">{{
                      info.user.nickname
                    }}</el-descriptions-item>

                    <el-descriptions-item label="地区" align="center">{{
                      info.user.addressCity
                    }}</el-descriptions-item>
                    <el-descriptions-item label="租赁方式" align="center">
                      <el-tag size="small">{{
                        info.model.orderModelName
                      }}</el-tag>
                    </el-descriptions-item>

                    <el-descriptions-item label="租赁价格" align="center"
                      >{{
                        info.goods.rentPricePerDay
                      }}元/天</el-descriptions-item
                    >
                    <!-- 以租代售 -->
                    <el-descriptions-item
                      label="商品总价"
                      align="center"
                      v-if="info.model.orderModelId !== 13"
                    >
                      <el-tag type="warning"
                        >{{ info.goods.goodsPrice }}元</el-tag
                      >
                    </el-descriptions-item>

                    <el-descriptions-item
                      label="租赁时间上限"
                      align="center"
                      v-if="info.model.orderModelId !== 13"
                    >
                      <el-tag type="danger" size="large"
                        >{{ info.goods.rentLimitDays }}天</el-tag
                      >
                    </el-descriptions-item>
                  </el-descriptions>
                </el-col></el-row
              >
            </el-card></el-col
          >
        </el-row>

        <el-divider></el-divider>
        <div style="text-align: center">
          <el-button
            style="float: right"
            class="w-200px h-50px -mt-25px"
            type="danger"
            @click="confirmOrderCheck"
            >提交订单</el-button
          >
        </div>
      </el-card></el-col
    >
  </el-row>
  <!-- dialog -->
  <el-dialog
    v-model="centerDialogVisible"
    title="下单提示信息"
    width="30%"
    center
    v-loading="loading"
  >
    <span>确认要生成订单吗？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmOrder">确认</el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 新增地址 -->
  <el-dialog v-model="addDialog" title="Warning" width="50%" center>
    <el-form :model="addressForm" label-width="120px">
      <el-form-item label="请选择地址">
        <el-cascader
          placeholder="请选择城市"
          class="m-2 -ml-0.5 -mt-0.5"
          :options="options1"
          v-model="selectedOptions"
          @change="selectCity"
        >
        </el-cascader>
      </el-form-item>
      <el-form-item label="请输入街道">
        <el-input v-model="addressForm.addressStreet" />
      </el-form-item>
      <el-form-item label="详细地址">
        <el-input v-model="addressForm.addressDetail" /> </el-form-item
      ><el-form-item label="收货人姓名">
        <el-input v-model="addressForm.receiveName" /> </el-form-item
      ><el-form-item label="收货人电话">
        <el-input v-model="addressForm.receivePhone" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialog = false">Cancel</el-button>
        <el-button type="primary" @click="consfirAddAddress">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { ArrowRight } from "@element-plus/icons-vue";
import router from "@/router";
import { onBeforeMount, ref, reactive } from "vue";
import { getPreorderInfo, makeOrder } from "@/api/order";
import { ElMessage } from "element-plus";
import { regionData, CodeToText } from "element-china-area-data"; //引入
import { addAddress,toDeleteAddress } from "@/api/user";
import { ElNotification } from "element-plus";

const city = ref();
const centerDialogVisible = ref(false);
const addDialog = ref(false);
const options1 = regionData;
let selectedOptions: [];
const selectCity = (res: any) => {
  addressForm.value.addressArea = CodeToText[res[2]];
  addressForm.value.addressProvince = CodeToText[res[0]];
  addressForm.value.addressCity = CodeToText[res[1]];
  console.log(addressForm.value);
};
const addressForm = ref({
  addressStreet: "",
  addressDetail: "",
  receiveName: "",
  receivePhone: "",
  addressProvince: "",
  addressCity: "",
  addressArea: "",
});
const consfirAddAddress = () => {
  addAddress(addressForm.value)
    .then(() => {})
    .then(() => {
      ElNotification({
        title: "提示信息",
        message: "地址添加成功",
        type: "success",
      });
      addDialog.value = false;
      getPreorderInfo(router.currentRoute.value.params.id).then((res) => {
        info.value = res.data;
        info.value.selectVO.filter((e) => {
          if (info.value.model.orderModelId == 13) {
            console.log(e);
            if (e.label != "自提") {
              e.disabled = true;
            }
          }
        });
      });
    });
};
const info = ref({
  selectVO: [{ value: "1", label: "京东", disabled: false }],
  userProp: [
    {
      propId: 2,
      userId: "430818627420160",
      addressProvince: "2",
      addressCity: "2",
      addressArea: "2",
      addressStreet: "2",
      addressDetail: "2",
      receiveName: "2",
      receivePhone: "2",
    },
  ],
  payType: {
    content: [{ typeId: 1, typeName: "支付宝" }],
  },
  cart: {
    goodsId: 5,
    goodsImg: "比基尼",
    goodsName: "p30111",
    goodsDesc: "我再做测试3",
    rentDays: 3,
    rentPrice: 56,
    modal: "共享租赁",
    rentLimit: 150,
    total: 168,
    goodsPrice: 5000,
  },
  goods: {
    goodsId: 1,
    goodsCategorySecondId: 2,
    userId: "431188825079808",
    goodsName: "p30",
    goodsDesc: "比基尼",
    goodsImg: "未知",
    goodsPrice: 5000,
    goodsSaleCount: 20,
    orderModelId: 1,
    goodsStatus: 1,
    createTime: "2022-06-22 09:58:58",
    rentLimitDays: 10.23,
    rentPricePerDay: 63,
  },
  model: { orderModelId: 1, orderModelName: "先租后买" },
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
});
onBeforeMount(() => {
  getPreorderInfo(router.currentRoute.value.params.id).then((res) => {
    info.value = res.data;
    info.value.selectVO.filter((e) => {
      if (info.value.model.orderModelId == 13) {
        console.log(e);
        if (e.label != "自提") {
          e.disabled = true;
        }
      }
    });
  });
});

const selectRadio = ref("1");
const payType = ref();
const logCompany = ref();
const loading = ref(false);
const radioValue = (a) => {
  order.receiverName = a.receiveName;
  order.receiverPhone = a.receivePhone;
  order.receiverProvince = a.addressProvince;
  order.receiverCity = a.addressCity;
  order.receiverArea = a.addressArea;
  order.receiverStreet = a.addressStreet;
  order.receiverDetailAddress = a.addressDetail;
};
const selectlogCompany = (a) => {
  order.logisticsCompanyId = a;
};
const typeName = (a) => {
  order.typeId = a;
};
//提交订单
const order: any = reactive({
  orderId: "",
  goodsId: router.currentRoute.value.params.id,
  userId: info.value.user.userId,
  typeId: payType.value,
  logisticsCompanyId: logCompany.value,
  goodsTotalPrice: info.value.cart.total,
  goodsPayReal: info.value.cart.total,
  receiverEmail: "",
  receiverProvince: "",
  shopUserId: info.value.user.userId,
  rentDays: info.value.cart.rentDays,
  confirmStatus: 0,
  orderStatus: 0,
});
//检查下单信息
const confirmOrderCheck = () => {
  if (!order.typeId) {
    ElMessage({
      message: "请选择支付方式",
      type: "warning",
    });
    return;
  }
  if (!order.receiverArea) {
    ElMessage({
      message: "请选择收货人信息",
      type: "warning",
    });
    return;
  }

  if (!logCompany.value) {
    ElMessage({
      message: "请选择配送方式",
      type: "warning",
    });
    return;
  }

  centerDialogVisible.value = true;
};
const confirmOrder = () => {
  centerDialogVisible.value = false;
  loading.value = true;
  console.log(order);
  
  makeOrder(order)
    .then((res) => {})
    .then(() => {
      loading.value = false;
      router.push("/isrpUser/success");
    });
};
const deleteAddress = (id) => {
  toDeleteAddress(id)
    .then(() => {})
    .then(() => {
      getPreorderInfo(router.currentRoute.value.params.id).then((res) => {
        info.value = res.data;
        info.value.selectVO.filter((e) => {
          if (info.value.model.orderModelId == 13) {
            console.log(e);
            if (e.label != "自提") {
              e.disabled = true;
            }
          }
        }); ElNotification({
        title: "提示信息",
        message: "地址删除成功",
        type: "success",
      });
      });
      
    });
};
</script>

<style scoped></style>
