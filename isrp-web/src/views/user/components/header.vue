<template>
  <el-row>
    <el-col :span="24" class="bg-light-600">
      <el-row :gutter="10">
        <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"></el-col>
        <el-col :xs="4" :sm="6" :md="8" :lg="18" :xl="11" class="mt-6">

          <!-- <div style="display: inline-block; float: right">
            <el-image
              class="h-120px w-245px "
              src="/src/assets/znzzlogo.png"
            />
          </div> -->

          <el-row
            ><el-col :span="4" class="mt-5"
              ><div
                class="h-10 text-center text-1.5xl w-190px"
                style="
                  background-color: #fc3737;
                  color: white;
                  font-family: 'Lucida Calligraphy', cursive, serif, sans-serif;
                "
              >
                <el-icon><HotWater /></el-icon>
                <span style="line-height: 40px">热门推荐商品</span>
                &nbsp;<el-icon :size="13"><ArrowRightBold /></el-icon></div
            ></el-col>
            <el-col :span="14" class="mt-5"
              ><el-tabs
                v-model="activeName"
                class="demo-tabs ml-10"
                @tab-click="handleClick"
              >
                <el-tab-pane label="首页" name="5"> </el-tab-pane>
                <el-tab-pane label="智慧租中心" name="6"></el-tab-pane>
                <el-tab-pane label="秒杀活动" name="1"></el-tab-pane>
                <el-tab-pane label="共享租赁" name="2"></el-tab-pane>
                <el-tab-pane label="先租后买" name="3"></el-tab-pane>
                <el-tab-pane
                  label="已租代售"
                  name="4"
                ></el-tab-pane> </el-tabs></el-col
          >
          <!-- <el-col :span="1"></el-col> -->
          <el-col  :span="5" class="mt-5 ml-39px">
            <el-input
              v-model="keyWord"
              class="w-246px"
              size="large"
              placeholder="在此搜索你想租赁的商品"
              :suffix-icon="Search"
            />
         </el-col>

          </el-row>
        </el-col> </el-row
    ></el-col>
  </el-row>
</template>
<script lang="ts" setup>
import index from "@/views/user/index.vue";
import { ref, computed } from "vue";
import { userStore } from "@/store/user";
import { Search } from "@element-plus/icons-vue";
import type { TabsPaneContext } from "element-plus";
import router from "@/router";
import { ElMessage } from "element-plus";
import VueEvent from "@/utils/event";
import { useRouter } from "vue-router";
let keyWord = ref("");
// 退出
const userstore = userStore();
const logout = () => {
  router.push("/userLogin");
  userStore().logout();
  ElMessage({
    message: "退出成功",
    type: "success",
    duration: 2 * 1000,
  });
};

const nickname = userstore.info.nickname;
const activeName: any = ref("5");
const handleClick = (tab: TabsPaneContext, event: Event) => {
  if (tab.props.name == 5) {
    router.push("/isrpUser");
  }
  if (tab.props.name == 3) {
    router.push("/isrpUser/goodsdetail");
  }
  if (tab.props.name == 6) {
    router.push("/isrpUser/rentCenter/1/1");
  }
};
//激活导航栏
VueEvent.on("tomsg", (value: any) => {
  activeName.value = value;
});

let props = defineProps({
  msg: { type: String },
}); //声明 props
activeName.value = props.msg;
console.log("我是子组件接收的props值：" + props.msg);
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

@keyframes kenburns-top {
  0% {
    transform: scale(0.7) translateY(0);
    transform-origin: 50% 16%;
  }
  100% {
    transform: scale(1.25) translateY(-15px);
    transform-origin: top;
  }
}
.kenburns-top {
  animation: kenburns-top 8s ease-out both;
}
</style>
