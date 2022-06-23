import { RouteRecordRaw } from "vue-router";

// 用户路由
export const user: Array<RouteRecordRaw> = [
  {
    // 全局路径
    path: "/user",
    // 导航栏
    component: () => import("@/components/UserNav.vue"),
    // 首页
    redirect: "/login",
    children: [
      {
        // 测试路径
        path: "test",
        component: () => import("@/views/About.vue"),
      },
    ],
  },
  {
    // 登录页面
    path: "/userlogin",
    component: () => import("@/views//UserLogin.vue"),
  },
];
