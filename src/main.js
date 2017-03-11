// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App'
import Element from 'element-ui'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import Home from './router/Home'
import Group from './router/Group'
import More from './router/More'


Vue.use(Element)
Vue.use(VueRouter)
Vue.use(VueResource)
Vue.http.options.emulateJSON = true;
const routes = [
  { path: '/', component: Home },
  { path: '/group', component: Group },
  { path: '/more', component: More }
]

const router = new VueRouter({ //创建路由实例
  routes
})

new Vue({
  router: router,
  el: '#app',
  render: h => h(App)
})
