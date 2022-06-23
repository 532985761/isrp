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
export function LoginApi () {
    return http.get('/isrpGoods/t/test')
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
