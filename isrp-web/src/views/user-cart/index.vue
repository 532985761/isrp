<template>
  <el-row :gutter="20">
    <el-col :span="1"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="22" class="mt-5">
      <el-card
        style="text-align: center"
        class="font-serif text-3xl font-medium"
      >
      <router-link to="/isrpUser/index"> <el-button style="float: left" type="primary"
          ><el-icon><Back /></el-icon>&nbsp;&nbsp;返回主页</el-button
        >
</router-link>


        <span class="text-2xl font-bold -ml-25"> 请选择需要下单的商品进行操作</span>

        <!-- <el-button>返回上一页</el-button> -->
      </el-card>
      <el-card class="mt-4">
        <el-table
          ref="multipleTableRef"
          :data="tableData"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" height="200" />
          <el-table-column label="商品信息" width="150">
            <template #default>
              <el-image
                style="width: 50px; height: 50px; border-radius: 15px"
                src="/src/assets/znzzlogo.png"
                class="mt-0.5"
            /></template>
          </el-table-column>
          <el-table-column height="200" property="name" width="220">
            <template #default="scope">
              <span class="ml-10">
                {{ scope.row.name }}Apple iPhone 13 (A2634) 128GB 星光色
                支持移动联通电信5G 双卡双待手机 选服务</span
              >
            </template>
          </el-table-column>
          <!-- <el-table-column
            property="address"
            label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量"
            show-overflow-tooltip
            class="ml-15"
            width="160"
          >
            <template #default>
              <span class="ml-18">x1</span>
            </template>
          </el-table-column> -->
          <el-table-column
            property="address"
            label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;租用时长/天"
            show-overflow-tooltip
            class="ml-15"
            width="180"
          >
            <template #default>
               <el-input-number size="small" v-model="num" :precision="2" :step="0.1" :max="10" />

            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="租用价格"
            show-overflow-tooltip
            class="ml-15"
            width="120"
          >
            <template #default>
              <span>30元/天</span>
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="&nbsp;&nbsp;租用方式"
            show-overflow-tooltip
            class="ml-15"
            width="120"
          >
            <template #default>
              <el-tag type="danger">以租代售</el-tag>
            </template>
          </el-table-column>
            <el-table-column
            property="address"
            label="租用限制"
            show-overflow-tooltip
            class="ml-15"
            width="120"
          >
            <template #default>
              <el-tag type="warning">暂无</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            property="address"
            label="小计"
            show-overflow-tooltip
            class="ml-15"
          >
            <template #default>
              <span>500元</span>
            </template>
          </el-table-column>

          <el-table-column
            property="address"
            label="操作"
            show-overflow-tooltip
            class="ml-15"
          >
            <template #default>
              <el-link type="danger">删除</el-link> <br />
              <el-link type="primary">立即下单</el-link>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 20px">
          <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])"
            >Toggle selection status of second and third rows</el-button
          > -->
          <el-button type="warning" @click="toggleSelection()"
            >清空选项</el-button
          >
          <span class="ml-550px">共计 元</span>
          <router-link to="/isrpUser/confirmOrder"> <el-button
            style="float: right"
            type="danger"
            class="w-200px h-60px -mt-4"
            >结算</el-button
          ></router-link>

        </div>
      </el-card>
    </el-col>

    <el-col :span="2"><div class="grid-content ep-bg-purple" /></el-col>
  </el-row>
</template>
<script lang="ts" setup>
import VueEvent from "@/utils/event";
import { ElTable } from "element-plus";
import { ref } from "vue";
const num = ref(1);
const handleChange = (value: number) => {
  console.log(value);
};
interface User {
  date: string;
  name: string;
  address: string;
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>();
const multipleSelection = ref<User[]>([]);
const toggleSelection = (rows?: User[]) => {
  if (rows) {
    rows.forEach((row) => {
      // TODO: improvement typing when refactor table
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-expect-error
      multipleTableRef.value!.toggleRowSelection(row, undefined);
    });
  } else {
    multipleTableRef.value!.clearSelection();
  }
};
const handleSelectionChange = (val: User[]) => {
  multipleSelection.value = val;
};

const tableData: User[] = [
  {
    date: "2016-05-03",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
  {
    date: "2016-05-02",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
  {
    date: "2016-05-04",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
  {
    date: "2016-05-01",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
  {
    date: "2016-05-08",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
  {
    date: "2016-05-06",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
  {
    date: "2016-05-07",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles",
  },
];
//隐藏导航栏下部分内容
VueEvent.emit("showDiv", { data: false });
</script>
