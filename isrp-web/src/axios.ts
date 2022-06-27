// 封装请求
import axios from "axios";
import { ElMessage } from "element-plus";
import { userStore } from "@/store/user";
import router from "./router";
import { start, close } from '@/utils/nprogress';

const userstore = userStore()

// 请求
const http = axios.create({
  baseURL: "http://localhost:9527",
  timeout: 6000,
});
// 请求拦截
http.interceptors.request.use(
  (config) => {
    // 加载
    start()
    // 请求头设置
    let token = userstore.token || ''
    config.headers = {
      ...config.headers,
      "content-type": "application/json",
      "Authorization": token
    }
    return config;
  },
  (err) => {
    console.log(err);
  }
);
// 响应拦截
http.interceptors.response.use(
  (arr) => {
    // 对响应码的处理
    if (arr.status != 200) {
      ElMessage({
        message: arr.data,
        type: "warning",
        duration: 2 * 1000,
      });
    }
    // 关闭加载
    close()
    return arr;
  },
  (err) => {
    if(err.response.status == 401) {
      router.push('/')
      if(userStore().info.role == 2){
        // 管理员过期提示
        ElMessage({
          message: "为保证安全请您重新输入管理员网址",
          type: "warning",
          duration: 2 * 1000,
        });
      }
    }
    // 对异常处理
    ElMessage({
      message: err.response.data,
      type: "error",
      duration: 2 * 1000,
    });
    // 关闭加载
    close()
    return err.response;
  }
);
// 返出
export default http;