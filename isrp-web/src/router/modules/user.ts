import { RouteRecordRaw } from 'vue-router'

// 用户路由
export const user: Array<RouteRecordRaw> = [
    {
        // 全局路径 
        path: '/user', 
        // 导航栏
        component: () => import('@/views/About.vue'), 
        // 首页
        redirect: '/user/test',
        children: [
            {
                // 测试路径
                path: 'test',
                component: () => import('@/views/About.vue')
            }
        ]
    },
]