import { createRouter,createWebHashHistory,RouteRecordRaw } from 'vue-router'
import { user } from './modules/user'
import { manager } from './modules/manager'
import { business } from './modules/business'

const startRoutes: Array<RouteRecordRaw> = [
  { path: '/', component: () => import('@/views/Home.vue') },
  { path: '/404', component: () => import('@/views/404.vue') }
]

const endRoutes: Array<RouteRecordRaw> = [
  { path: '/:pathMatch(.*)', redirect:'/404' }
]

const routes = startRoutes.concat(user).concat(manager).concat(business).concat(endRoutes)

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

