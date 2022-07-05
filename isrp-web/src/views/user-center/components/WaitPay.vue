<template>
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
              <router-link :to="'/isrpUser/confirmOrder/' + scope.row.goodsId">
                <el-link type="primary">下单</el-link></router-link
              >
            </template>
          </el-table-column>
        </el-table>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ElTable } from 'element-plus'

interface User {
  date: string
  name: string
  address: string
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<User[]>([])
const toggleSelection = (rows?: User[]) => {
  if (rows) {
    rows.forEach((row) => {
      // TODO: improvement typing when refactor table
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-expect-error
      multipleTableRef.value!.toggleRowSelection(row, undefined)
    })
  } else {
    multipleTableRef.value!.clearSelection()
  }
}
const handleSelectionChange = (val: User[]) => {
  multipleSelection.value = val
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-08',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
</script>
