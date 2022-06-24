import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testUserApi () {
    return http.get('/isrpUser/t/test')
}

/**
 * 获取验证码
 * 
 * @returns 
 */
 export function captcha () {
    return http({
        url: '/isrpUser/captcha',
        method: 'get',
        responseType: 'blob'
    })
}

/**
 * 登录
 * 
 * @returns 
 */
 export function login (loginForm : any) {
    return http.post('/isrpUser/isrpUser/login', loginForm)
}
