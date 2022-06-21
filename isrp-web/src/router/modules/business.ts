import { RouteRecordRaw } from 'vue-router'

// 商家路由
export const business: Array<RouteRecordRaw> = [
    {
        // 全局路径 
        path: '/business', 
        // 导航栏
        component: () => import('@/views/About.vue'), 
        // 首页
        redirect: '/business/test',
        children: [
            {
                // 测试路径
                path: 'test',
                component: () => import('@/views/About.vue')
            }
        ]
    },
]