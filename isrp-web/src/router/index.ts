import { createRouter,createWebHashHistory,RouteRecordRaw } from 'vue-router'
import { user } from './modules/user'
import { manager } from './modules/manager'
import { business } from './modules/business'
import { start, close } from '@/utils/nprogress';
import { userStore } from "@/store/user";

const whiteNames = [
  "/managerLogin/userid=isrp_grouptwo/password=wbw2022isrp_grouptwo/8888",
  "/businessLogin",
  "/userLogin",
  "/",
  "/404"
]

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

router.beforeEach(async(to, from, next) => {
  start()
  // 白名单通过
  if(whiteNames.indexOf(to.path) == -1) {
    const token = userStore().token
    const info = userStore().info
    // 没有info或token
    if(token == null || info == null || token == '' || info == '') {
      next("/")
    }
    // 访问管理员
    if(to.path.indexOf('/isrpManager/userid=isrp_grouptwo/8888') != -1) {
      // 不是管理员
      if(info.role != 2){
        next('/404')
      }
    }
    // 访问商家
    if(to.path.indexOf('/isrpBusiness') != -1) {
      // 不是商家
      if(info.role != 1){
        next('/businessLogin')
      }
    }
    // 访问用户
    if(to.path.indexOf('/isrpUser') != -1) {
      // 不是用户
      if(info.role != 0){
        next('/userLogin')
      }
    }
  }
  // 更新菜单项
  userStore().navActive = to.path
  next()
})

router.afterEach(async(to, from, next) => {
  close()
})


export default router

