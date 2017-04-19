module.exports = {
  routes: [{
    path: '/',
    component: require('../components/Login.vue')
  }, {
    path: '/group',
    component: require('../components/Group.vue')
  }, {
    path: '/home',
    component: require('../components/Home.vue')
  }, {
    path: '/more',
    component: require('../components/More.vue')
  }]
}