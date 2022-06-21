import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testGoodsApi () {
    return http.get('/isrpGoods/t/test')
}