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
        name: "index",
        component: () => import("@/views/user/index.vue"),
      },
      {
        // 商品详情
        path: "goodsdetail/:id",
        name: "goodsdetail",
        component: () => import("@/views/goods-detail/GoodsDetail.vue"),
      },
      {
        // 租赁中心
        path: "rentCenter/:firstId/:secondId",
        name: "rentCenter",
        component: () => import("@/views/user-rent-center/index.vue"),
      },
      {
        // 购物车页面
        path: "userCart",
        component: () => import("@/views/user-cart/index.vue"),
      },
      {
        // 提交订单页
        path: "confirmOrder/:id",
        name: "confirmOrder",
        component: () => import("@/views/user-cart-to-order/index.vue"),
      },
      {
        // 成功页
        path: "success",
        name: "success",
        component: () =>
          import("@/views/user-cart-to-order/components/success.vue"),
      },
      {
        // 个人中心页
        path: "userCenter",
        name: "userCenter",
        component: () => import("@/views/user-center/index.vue"),
        children:[
          {
            // 待支付
            path: "waitPay",
            name: "waitPay",
            component: () => import("@/views/user-center/components/WaitPay.vue"),
          },
        ]
      },
    ],
  },
  {
    // 登录页面
    path: "/userLogin",
    name: "/userLogin",
    component: () => import("@/views/UserLogin.vue"),
  },
];
