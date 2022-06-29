import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testOrderApi () {
    return http.get('/isrpOrder/t/test')
}

/**
 * 分页查询订单模式
 * 
 * @param page 
 * @param size 
 * @returns 
 */
export function queryOrderModelByPage (page:number, size:number) {
    return http.get('/isrpOrder/isrpOrderModel/queryByPage/' + page + '/' + size)
}

/**
 * 添加订单模式
 * 
 * @param orderModelName
 * @returns 
 */
export function addOrderModel (orderModelName:string) {
    return http.post('/isrpOrder/isrpOrderModel/add', {orderModelName: orderModelName})
}

/**
 * 删除订单模式
 * 
 * @param orderModelId 
 * @returns 
 */
export function deleteOrderModel (orderModelId:number) {
    return http.delete('/isrpOrder/isrpOrderModel/delete/' + orderModelId)
}

/**
 * 修改订单模式
 * 
 * @param orderModel 
 * @returns 
 */
export function editOrderModel (orderModel:any) {
    return http.put('/isrpOrder/isrpOrderModel/edit', orderModel)
}