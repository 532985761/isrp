// 封装请求
import axios from 'axios'
import { ElMessage } from 'element-plus'
// 请求
const http = axios.create({
    baseURL: "http://localhost:9527",
    timeout: 6000
})
// 请求拦截
http.interceptors.request.use(config => {
    //请求头设置
    config.headers = {
      ...config.headers,
      'content-type': "application/json"
    }
    // let token = localStorage.getItem('token') || ''
    // config.headers = {
    //   ...config.headers,
    //   Authorization: token
    // }
    return config
}, err => {
    console.log(err);
})
// 响应拦截
http.interceptors.response.use(arr => {
    // 对响应码的处理
    if(arr.status == 200) {
        return arr.data
    } else {
        ElMessage({
            message: arr.data,
            type: 'warning',
        })
    }
}, err => {
    ElMessage({
        message: err.response.data,
        type: 'error',
    })
})
// 返出
export default http