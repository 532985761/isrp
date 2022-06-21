import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testUserApi () {
    return http.get('/isrpUser/t/test')
}
