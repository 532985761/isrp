import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testOrderApi () {
    return http.get('/isrpOrder/t/test')
}