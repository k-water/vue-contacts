# Contacts
### 简述
这是一个通讯录的project，以web方式展示，目前只支持PC端，暂无移动端的适配。

采用前后端分离的方法，后台只提供接口。

### 实现的功能
```bash
# 联系人的增删查改

# 支持模糊查询

# 分组功能

  ## 分组的增加删除
  
  ## 各分组联系人的展示
  
  
# 联系人资料(Excel)
  ## 导入
  ## 导出
```
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

### 构建

#### 前端
``` bash
# install dependencies
npm install

# serve in dev mode, with hot reload at localhost:12221
npm run dev

# build for production
npm run build

```
#### 后台
```bash
# clone 
克隆servlet分支并启动Tomcat，启动后台服务，同时本地也要配置好MySql
```
> PS: 如果不懂配置后台代码，可以自己修改get和post里面的链接，本地mock数据，在本项目的static提供了一个json文件，可以参考


### 最后

此项目实现了通讯录的基本功能，欢迎提issue，喜欢的请点个Star。

欢迎与我交流。
