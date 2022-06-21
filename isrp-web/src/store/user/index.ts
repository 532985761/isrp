import { defineStore } from "pinia"
import { Names } from '../store_name'
import { useCookies } from '@vueuse/integrations/useCookies'

const key = "pinia-user"

export const userStore = defineStore(Names.user, {
    state: () => {
        return {
            test: useCookies().get(key) != null ? useCookies().get(key).test : null,
            test2: useCookies().get(key) != null ? useCookies().get(key).test2 : null
        }
    },
    getters: {

    },
    actions: {
        
    }
})