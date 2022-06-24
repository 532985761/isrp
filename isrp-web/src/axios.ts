// 封装请求
import axios from "axios";
import { ElMessage } from "element-plus";
import { userStore } from "@/store/user";
import router from "./router";

const userstore = userStore()

// 请求
const http = axios.create({
  baseURL: "http://localhost:9527",
  timeout: 6000,
});
// 请求拦截
http.interceptors.request.use(
  (config) => {
    //请求头设置
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
      });
    }
    return arr;
  },
  (err) => {
    if(err.response.status == 401) {
      router.push('/')
    }
    ElMessage({
      message: err.response.data,
      type: "error",
    });
    return err.response;
  }
);
// 返出
export default http;