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
/**
 * 通过userId查询商品信息
 */
export function selectGoodsByUserId(userId){
    return http.get('/isrpGoods/isrpGoods/selectGoodsByUserId/'+userId)
}
/**
 * 通过goodsId删除商品
 */
export function deleteGoodsByGoodsId(goodsId){
    return http.get('/isrpGoods/isrpGoods/deleteGoodsById/'+goodsId)
}