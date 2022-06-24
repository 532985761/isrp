import { RouteRecordRaw } from 'vue-router'

// 管理端路由
export const manager: Array<RouteRecordRaw> = [
    {
        // 全局路径 
        path: '/manager', 
        // 导航栏
        component: () => import('@/components/ManagerNav.vue'), 
        // 首页
        redirect: '/manager/home',
        children: [
            {
                // 首页
                path: 'home',
                component: () => import('@/views/manager-home/index.vue')
            }
        ]
    },
    {
        path: '/manager/login/userid=isrp_grouptwo/password=wbw2022isrp_grouptwp/8888',
        component: () => import('@/views/manager-login/index.vue')
    }
]