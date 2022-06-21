import { createApp, toRaw } from 'vue'
import { createPinia, PiniaPluginContext } from 'pinia'
import { useCookies } from '@vueuse/integrations/useCookies'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'


const app = createApp(App)

// Windi CSS
import 'virtual:windi.css'
// Pinia
const store = createPinia()
const cookie = useCookies()
const __piniaKey__:string = "grouptwo"
type Options = {
    key?:string
}
const piniaPlugin = (options:Options) => {
    return (context: PiniaPluginContext) => {
        const {store} = context
        store.$subscribe(() => {
            cookie.set(`${options?.key ?? __piniaKey__}-${store.$id}`, toRaw(store.$state))
        })
    }
}
store.use(piniaPlugin({
    key: "pinia"
}))
app.use(store)
// Element-Plus
app.use(ElementPlus)
// 路由
app.use(router)

app.mount('#app')