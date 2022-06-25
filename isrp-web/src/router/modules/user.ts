import { RouteRecordRaw } from "vue-router";

// 用户路由
export const user: Array<RouteRecordRaw> = [
  {
    // 全局路径
    path: "/isrpUser",
    // 导航栏
    component: () => import("@/components/UserNav.vue"),
    // 首页
    redirect: "/isrpUser/index",
    children: [
      {
        // 用户首页
        path: "index",
        component: () => import("@/views/user/index.vue"),
      },
      {
        // 商品详情
        path: "goodsdetail",
        component: () => import("@/views/user/components/GoodsDetail.vue"),
      },
    ],
  },
  {
    // 登录页面
    path: "/userLogin",
    component: () => import("@/views/UserLogin.vue"),
  },
];
