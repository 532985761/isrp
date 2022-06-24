<template>
  <div>
    <el-container>
      <el-header class="text-center">Header</el-header>
      <el-container>
        <el-aside :width="navWidth">
          <el-button :icon="Edit" circle @click="isCollapse = !isCollapse" />
          <el-menu default-active="2" :collapse="isCollapse">
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>Navigator One</span>
              </template>
              <el-menu-item-group>
                <template #title><span>Group One</span></template>
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item two</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="Group Two">
                <el-menu-item index="1-3">item three</el-menu-item>
              </el-menu-item-group>
              <el-sub-menu index="1-4">
                <template #title><span>item four</span></template>
                <el-menu-item index="1-4-1">item one</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-menu-item index="2">
              <el-icon><icon-menu /></el-icon>
              <template #title>Navigator Two</template>
            </el-menu-item>
            <el-menu-item index="3" disabled>
              <el-icon><document /></el-icon>
              <template #title>Navigator Three</template>
            </el-menu-item>
            <el-menu-item index="4">
              <el-icon><setting /></el-icon>
              <template #title>Navigator Four</template>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>Main</el-main>
          <el-footer>Footer</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref, watch } from "vue";
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
  Edit,
} from "@element-plus/icons-vue";

const isCollapse = ref(false);
const navWidth = ref("200px");
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath, "open");
  navWidth.value = "200px";
};
// 回调函数异常;
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath, "close");
  navWidth.value = "100px";
};
// watch(isCollapse, (newValue) => {
//   if (newValue == true) {
//     console.log("close");
//     setTimeout(function () {
//       navWidth.value = "100px";
//     }, 800);
//   }
//   if (newValue == false) {
//     console.log("open");
//     navWidth.value = "200px";
//   }
// });
</script>

<style></style>
