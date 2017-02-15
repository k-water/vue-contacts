// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App'
import Element from 'element-ui'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import Home from './router/Home'
import Group from './router/group'
import GroupCompany from './router/group-1'

Vue.use(Element)
Vue.use(VueRouter)
Vue.use(VueResource)
const routes = [
  { path: '/', component: Home },
  { path: '/groupHome', component: Group },
  { path: '/groupCompany', component: GroupCompany }
]

const router = new VueRouter({ //创建路由实例
  routes
})

new Vue({
  router: router,
  el: '#app',
  /**
   * 
   * 
   * @param {any} h
   */
  render: h => h(App)
})
