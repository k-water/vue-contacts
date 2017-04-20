module.exports = {
  routes: [{
    path: '/',
    name: 'home',
    meta: {
      requireLogin: true
    },
    component: require('../components/Home.vue')
  }, {
    path: '/group',
    name: 'group',
    component: require('../components/Group.vue')
  }, {
    path: '/login',
    name: 'login',
    component: require('../components/Login.vue')
  }, {
    path: '/more',
    name: 'more',
    component: require('../components/More.vue')
  }, {
    path: '/register',
    name: 'register',
    component: require('../components/Register.vue')
  }]
}