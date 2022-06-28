# ISRP

#### 介绍
本系统为智能化共享租赁平台系统

#### 软件架构
|    开源软件     |     版本      | 所属项目 |
| :-------------: | :-----------: | :------: |
|       Vue       |       3       | isrp-web |
|      Pinia      |      v2       | isrp-web |
|   Vue Router    |       4       | isrp-web |
|     Vue Use     |    v8.7.4     | isrp-web |
|    Windi CSS    |     v3.4      | isrp-web |
|      axios      |    v0.27.2    | isrp-web |
|  Element-Plus   |    v2.2.6     | isrp-web |
|   SpringBoot    | 2.3.7.RELEASE |   isrp   |
|   SpringCloud   |  Hoxton.SR12  |   isrp   |
|      MySQL      |    8.0.25     |   isrp   |
|      Redis      |      7.0      |   isrp   |
|     Mybatis     |     2.1.2     |   isrp   |
| Spring Data JPA | 2.3.7.RELEASE |   isrp   |

- isrp-web为前端项目
- isrp为后端项目

#### 安装教程

进入isrp-web文件夹下依次输入如下命令：

Npm

```
npm install
```

Pinia

```
npm install pinia
```

Vue-Router4

```shell
npm install vue-router@4
```

Node.js

```shell
npm install @types/node --save-dev
```

Element-Plus

```shell
npm install element-plus --save
```

Windi CSS

```shell
npm i -D vite-plugin-windicss windicss
```

Cookie

```shell
npm i @vueuse/integrations
npm i universal-cookie
```

Axios

```shell
npm install axios
```

China-area-data
```
npm install element-china-area-data -S
```

NProcess
```
npm install --save nprogress
```
mitt
```
npm install --save mitt 
```
#### 后端配置

* 网关接口 http://localhost:9527/
* 用户接口 http://localhost:9527/isrpUser(端口号 10000)
* 订单接口 http://localhost:9527/isrpOrder(端口号 20000)
* 商品接口 http://localhost:9527/isrpGoods(端口号 30000)
