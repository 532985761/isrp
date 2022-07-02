<template>
  <el-row :gutter="20">
    <el-col :span="1"></el-col>
    <el-col :span="22" class="mt-5">
      <el-card class="font-serif">
        <el-button style="float: left" type="primary" @click="$router.go(-1)"
          ><el-icon><Back /></el-icon>&nbsp;&nbsp;返回上一页</el-button
        >
        <div class="w-800px ml-400px">
          <el-steps :space="200" :active="1" finish-status="success">
            <el-step title="选取商品" />
            <el-step title="购物车筛选" />
            <el-step title="下单" />
            <el-step title="付款" />
          </el-steps>
        </div>
      </el-card>
      <el-card class="mt-4">
        <el-table
          :data="cart"
          style="width: 100%"
          element-loading-text="正在改变购物车数据，请稍等"
          element-loading-background="rgba(122, 122, 122, 1)"
          v-loading="loading"
        >
          <el-table-column label="商品信息" width="100">
            <template #default="scope">
              <el-image
                style="width: 50px; height: 50px; border-radius: 15px"
                :src="scope.row.goodsImg"
                class="mt-0.5"
            /></template>
          </el-table-column>
          <el-table-column height="200" property="name" width="220">
            <template #default="scope">
              <span class="font-bold"> {{ scope.row.goodsName }}</span
              ><br /><span> {{ scope.row.goodsDesc }}</span>
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;租用时长(天)"
            show-overflow-tooltip
            class="ml-15"
            width="180"
          >
            <template #default="scope">
              <el-input-number
                size="small"
                v-model="scope.row.rentDays"
                :step="1"
                :max="scope.row.rentLimit"
                @change="changeCart(scope.row.goodsId, scope.row.rentDays)"
              />
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="租用价格"
            show-overflow-tooltip
            class="ml-15"
            width="120"
          >
            <template #default="scope">
              <el-tag size="large" type="primary">{{
                scope.row.rentPrice
              }}</el-tag
              ><span> 元/天</span>
            </template>
          </el-table-column>
          <el-table-column
            label="&nbsp;&nbsp;租用方式"
            show-overflow-tooltip
            class="ml-15"
            width="120"
          >
            <!-- <template #default="scope">
              <el-tag size="large" type="danger">{{ scope.row.modal }}</el-tag>
            </template> -->
            <template #default="scope">
              <el-popover
                effect="light"
                trigger="hover"
                placement="top"
                width="auto"
              >
                <template #default>
                  <div v-if="scope.row.modal == '先租后买'">
                    租用模式说明：规定一个租用时间上限，在租用时间截止之前考虑是否买下本商品
                  </div>
                  <div v-else-if="scope.row.modal == '以租代售'">
                    租用模式说明：规定一定的租赁时间，不可改变，在到达时间之前得退货，达到之后物品归为己有
                  </div>
                  <div v-else>租用模式说明：按照实际租用时长付费</div>
                </template>
                <template #reference>
                  <el-tag type="danger">{{ scope.row.modal }}</el-tag>
                </template>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="租用上限"
            show-overflow-tooltip
            class="ml-15"
            width="120"
          >
            <template #default="scope">
              <el-tag size="large" type="warning"
                >{{ scope.row.rentLimit }}天</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="商品总价"
            show-overflow-tooltip
            class="ml-15"
          >
            <template #default="scope">
              <span v-if="scope.row.modal != '共享租赁'"
                >{{ scope.row.goodsPrice }} 元</span
              >
              <span v-else>按租用时长计费<br />到时间可以续租</span>
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="小计"
            show-overflow-tooltip
            class="ml-15"
          >
            <template #default="scope">
              <span
                >{{
                  (scope.row.rentPrice * scope.row.rentDays).toFixed(2)
                }}
                元</span
              >
            </template>
          </el-table-column>

          <el-table-column
            property="address"
            label="操作"
            show-overflow-tooltip
            class="ml-15"
          >
            <template #default="scope">
              <el-popconfirm
                title="确定要删除购物车中此项吗？"
                @confirm="confirmDelete(scope.row.goodsId)"
              >
                <template #reference>
                  <el-link type="danger">删除</el-link>
                </template>
              </el-popconfirm>
              <br />
              <el-link type="primary">下单</el-link>
            </template>
          </el-table-column>
        </el-table>
        <!-- <div style="margin-top: 20px">
       
          <el-button type="warning" @click="toggleSelection()"
            >清空选项</el-button
          >
          <span class="ml-550px">共计 元</span>
          <router-link to="/isrpUser/confirmOrder">
            <el-button
              style="float: right"
              type="danger"
              class="w-200px h-60px -mt-4"
              >结算</el-button
            ></router-link
          >
        </div> -->
      </el-card>
    </el-col>

    <el-col :span="2"></el-col>
  </el-row>
</template>
<script lang="ts" setup>
import VueEvent from "@/utils/event";
import { ElTable } from "element-plus";
import { onBeforeMount, ref, computed } from "vue";
import { getCartInfo, changeCartInfo, deleteCartByGoodsId } from "@/api/order";
    import { ElNotification } from 'element-plus'
const num = ref(1);
const visible = ref(false);

//购物车结构定义
const cart = ref([
  {
    goodsId: 37,
    goodsImg: null,
    goodsName: "aaa",
    goodsDesc: "商品状态测试",
    rentDays: 8888,
    rentPrice: 388,
    modal: "先租后买",
    rentLimit: 10,
    total: 3448544,
    loading: false,
    goodsPrice: 1,
  },
]);
//获取购物车数据
onBeforeMount(() => {
  getCart();
});
const getCart = () => {
  getCartInfo().then((res) => {
    cart.value = res.data.cart;
    console.log(cart.value);
  });
};
const loading = ref(false);
//更新购物车数据
const changeCart = (goodsId, days) => {
  loading.value = true;
  changeCartInfo(goodsId, days)
    .then((res) => {
      cart.value = res.data.cart;
    })
    .then(() => {
      loading.value = false;
    });
};
//删除购物车项
const confirmDelete = (goodsId) => {
  deleteCartByGoodsId(goodsId).then(() => {
    getCart();
  }).then(() => {



  ElNotification({
    title: '提示信息',
    message: '商品成功从购物车移除',
    type: 'success',
  })

});
};
</script>
