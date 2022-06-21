import { RouteRecordRaw } from 'vue-router'

// 管理端路由
export const manager: Array<RouteRecordRaw> = [
    {
        // 全局路径 
        path: '/manager', 
        // 导航栏
        component: () => import('@/views/About.vue'), 
        // 首页
        redirect: '/manager/test',
        children: [
            {
                // 测试路径
                path: 'test',
                component: () => import('@/views/About.vue')
            }
        ]
    },
]