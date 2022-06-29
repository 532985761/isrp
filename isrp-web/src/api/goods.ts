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
export function getAllGoods() {
  return http.get("/isrpGoods/isrpGoods/selectAllGoods");
}
/**
 * 查询商品一级分类
 */
export function queryByPageGetGoodsCategoryFirst() {
  return http.get(
    "/isrpGoods/isrpGoodsCategoryFirst/queryByPageGetGoodsCategoryFirst?page=1&size=4"
  );
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
/**
 * 查询商品二级分类
 */
export function queryByPageGetGoodsCategorySecond(
  index: number,
  page: number,
  size: number
) {
  return http.get(
    "/isrpGoods/isrpGoodsCategorySecond/queryByPageGoodsCategorySecond?index=" +
      index +
      "&page=1&size=50"
  );
}
/**
 * 查询商品二级分类获得一级分类信息
 */
export function getRentCenterInfoFromGoodsCategoryId(firstId:string,secondId:string) {
  return http.get(
    "/isrpGoods/isrpGoods/getRentCenterInfoFromGoodsCategoryId?firstId="+firstId+"&secondId="+secondId
  );
}
