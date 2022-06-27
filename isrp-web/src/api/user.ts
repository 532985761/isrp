import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function registerUser(yu:any) {
        console.log(yu);
        
    return http.post('/isrpUser/isrpUser/register',yu)
}
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
 * 用户登录
 * 
 * @returns 
 */
export function userLogin (loginForm : any) {
    return http.post('/isrpUser/isrpUser/userLogin', loginForm)
}
/**
 * 商家登录
 * 
 * @returns 
 */
export function businessLogin (loginForm : any) {
    return http.post('/isrpUser/isrpUser/businessLogin', loginForm)
}
/**
 * 管理员登录
 * 
 * @returns 
 */
export function managerLogin (loginForm : any) {
    return http.post('/isrpUser/isrpUser/managerLogin', loginForm)
}
/**
 * 获取用户信息
 * 
 * @returns 
 */
export function getOneUserInfo (userId:string) {
    return http.post('/isrpUser/isrpUser/getUserInfo/?userId='+userId)
}
/**
 * 
 *用户注册
 * @returns 
 */
//  export function registerUser (user:any) {
//     console.log(user);
    
//     return http.post('/isrpUser/isrpUser/register',JSON.stringify(user))
// }

/**
 * 分页查询用户
 * 
 * @param page 
 * @param size 
 * @returns 
 */
export function queryUserByPage (page:number, size:number) {
    return http.get('/isrpUser/isrpUser/queryByPage/' + page + "/" + size)
}
