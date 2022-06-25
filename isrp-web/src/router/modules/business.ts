import { RouteRecordRaw } from 'vue-router'

// 商家路由
export const business: Array<RouteRecordRaw> = [
    {
        // 全局路径 
        path: '/isrpBusiness', 
        // 导航栏
        component: () => import('@/components/BusinessNav.vue'), 
        // 首页
        redirect: '/isrpBusiness/index',
        children: [
            {
                // 测试路径
                path: 'test',
                component: () => import('@/views/About.vue')
            },
            {
                // 厂商的欢迎页（主页）
                path: 'index',
                component: () => import('@/views/business/index.vue')
            },
            {
                // 管理商品
                path: 'goodsinfo',
                component: () => import('@/views/goods/index.vue')
            },
            {
                // 管理待支付订单
                path: 'orderinfo',
                component: () => import('@/views/order/index.vue')
            },
            {
                // 厂商个人资料
                path: 'info',
                component: () => import('@/views/business/info.vue')
            },
            {
                // 管理已支付订单
                path: 'orderfinish',
                component: () => import('@/views/order/finish.vue')
            },
            
        ]
    },
    {
        path:'/businessLogin',
        component: () => import('@/views/business/BusinessLogin.vue')
    },
]