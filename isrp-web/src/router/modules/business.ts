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
                // 管理已支付订单
                path: 'orderfinish',
                component: () => import('@/views/order/finish.vue')
            },
            {
                // 管理待租赁订单
                path: 'orderwait',
                component: () => import('@/views/order/wait.vue')
            },
            
        ]
    },
    //切换商家登录页
    {
        path:'/businessLogin',
        component: () => import('@/views/business/BusinessLogin.vue')
    },
    
]