import Vue from 'vue'
import axios from 'axios'
import Router from 'vue-router'
import store from '../store/index'

Vue.use(Router)

const router = new Router({
  routes: [{
    path: '/',
    name: 'home',
    component(resolve) {
      require.ensure(['../components/Home.vue'], () => {
        resolve(require('../components/Home.vue'))
      })
    },
    meta: {
      requireAuth: true
    }
  }, {
    path: '/group',
    name: 'group',
    meta: {
      requireAuth: true
    },
    component(resolve) {
      require.ensure(['../components/Group.vue'], () => {
        resolve(require('../components/Group.vue'))
      })
    },
  }, {
    path: '/login',
    name: 'login',
    component(resolve) {
      require.ensure(['../components/Login.vue'], () => {
        resolve(require('../components/Login.vue'))
      })
    },
  }, {
    path: '/more',
    name: 'more',
    meta: {
      requireAuth: true
    },
    component(resolve) {
      require.ensure(['../components/More.vue'], () => {
        resolve(require('../components/More.vue'))
      })
    },
  }, {
    path: '/register',
    name: 'register',
    component(resolve) {
      require.ensure(['../components/Register.vue'], () => {
        resolve(require('../components/Register.vue'))
      })
    },
  }]
})

router.beforeEach((to, from, next) => {
  let token = localStorage.getItem('token')
  if (to.meta.requireAuth) {
    if (token) {
      next()
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    }
  } else {
    next()
  }
})

export default router;