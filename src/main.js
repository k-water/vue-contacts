// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import store from './store'
import Element from 'element-ui'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'

Vue.use(Vuex)
Vue.use(Element)
Vue.use(VueRouter)
Vue.use(VueResource)
Vue.http.options.emulateJSON = true;

const router = new VueRouter(require('./router/router'))

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})