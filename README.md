# Contacts
### 简述
这是一个通讯录的project，以web方式展示，目前只支持PC端，暂无移动端的适配。

采用前后端分离的方法，后台只提供接口。

### 实现的功能

* 联系人的增删查改

* 支持模糊查询

* 分组功能

  * 分组的增加删除
  
  * 各分组联系人的展示
  
  
* 联系人资料(Excel)
  * 导入
  * 导出
### 技术栈
  
#### 前端
  
* Vue
* Vuex
* VueRouter
* VueResource
* Vue-cli
* ElementUI
* 环境node

#### 后台
* JAVA
* Servlet
* MySQL
* 环境Tomcat8.0

### 运行
* 克隆项目

> git clone https://github.com/k-water/Contacts.git

* 启动前端页面
在master分支启动前端页面，安装依赖并启动

> npm install && npm run dev

* 打包

> npm run build

* 启动后台

> 需提前装好Tomcat和MySQL

> 构建项目并启动Tomcat,后端代码在servlet分支

### 最后

这只是个初版，很多地方设计还不完善，先做个功能出来。喜欢的请点个Star。

欢迎与我交流
