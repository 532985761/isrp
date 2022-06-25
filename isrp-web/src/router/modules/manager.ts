import { RouteRecordRaw } from 'vue-router'

// 管理端路由
export const manager: Array<RouteRecordRaw> = [
    {
        // 全局路径 
        path: '/isrpManager/userid=isrp_grouptwo/8888', 
        // 导航栏
        component: () => import('@/components/ManagerNav.vue'), 
        // 首页
        redirect: '/isrpManager/userid=isrp_grouptwo/8888/home',
        children: [
            {
                // 首页
                path: 'home',
                component: () => import('@/views/manager-home/index.vue')
            },
            {
                // 订单模式管理
                path: 'orderModel',
                component: () => import('@/views/manager-order-model/index.vue')
            },
            {
                // 订单流程
                path: 'orderProcess',
                component: () => import('@/views/manager-order-process/index.vue')
            },{
                // 用户流程
                path: 'user',
                component: () => import('@/views/manager-user/index.vue')
            }
        ]
    },
    {
        // 管理员登陆页面
        path: '/managerLogin/userid=isrp_grouptwo/password=wbw2022isrp_grouptwo/8888',
        component: () => import('@/views/manager-login/index.vue')
    }
]