import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testGoodsApi () {
    return http.get('/isrpGoods/t/test')
}
/**
 * 查询所有商品
 */
export function getAllGoods(){
    return http.get('/isrpGoods/isrpGoods/selectAllGoods')
}