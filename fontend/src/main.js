// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import Vuex from 'vuex'

import App from './App'
import store from './store'
import router from './router/index'
// import axios from './util/interceptor.js'
import axios from 'axios'
Vue.prototype.$http = axios;

import Element from 'element-ui'
Vue.use(Vuex)
Vue.use(Element)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})