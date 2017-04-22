# Contacts
### 简述
这是一个通讯录的project，以web方式展示，目前只支持PC端，暂无移动端的适配。

### 实现的功能

* [x] 登录
* [x] 注册
* [x] 联系人增删查改
* [x] 联系人分组
* [x] 联系人导入
* [x] 联系人导出

### 技术栈
  
* Vue
* Vuex
* VueRouter
* Axios
* Vue-cli
* ElementUI
* Node
* Express
* MongoDB

### 目录结构
<pre>
|
├── README.md           
├── build               // 构建服务和webpack配置
├── config              // 项目不同环境的配置
├── dist                // 项目build目录
├── index.html          // 项目入口文件
├── package.json
├── server              // 登录注册后台目录
|   ├── controllers     // 控制器
|   ├── db              // 数据库配置
|   ├── middlewares     // 中间件
|   ├── models          // Collections models
|   ├── router          // 后台路由
|
├── src                 // 生产目录
│   ├── assets          // css js 和图片资源
│   ├── components      // 各种组件
│   ├── router          // 前端路由
│   ├── store           // vuex状态管理器
│   ├── utils           // axios拦截器
|   ├── App.vue         // 主组件
│   └── main.js         // Webpack 预编译入口
|
├── static              // 其他静态资源 
├── server.js           // 启动后台JS文件
|
</pre>
### 构建
需要启动两个服务，一个前端，一个后台
``` bash
# install dependencies
npm install

# server in dev mode, with hot reload at localhost:12221
npm run dev

# build for production
npm run build

```

``` bash
# server with node
node server.js
```

### 最后

此项目实现了通讯录的基本功能，欢迎提issue，喜欢的请点个Star。

欢迎与我交流。

Email：625592890@qq.com
