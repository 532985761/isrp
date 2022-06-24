import { defineStore } from "pinia"
import { Names } from '../store_name'
import { useCookies } from '@vueuse/integrations/useCookies'

const key = "pinia-user"

export const userStore = defineStore(Names.user, {
    state: () => {
        return {
            test: useCookies().get(key) != null ? useCookies().get(key).test : null,
            test2: useCookies().get(key) != null ? useCookies().get(key).test2 : null,
            info: useCookies().get(key) != null ? useCookies().get(key).info : null,
            token: useCookies().get(key) != null ? useCookies().get(key).token : null
        }
    },
    getters: {

    },
    actions: {
        setInfo(info : any){
            this.info = info
        },
        setToken(tokenHeader:any, token:any){
            this.token = tokenHeader + ' ' + token
        }
    }
})