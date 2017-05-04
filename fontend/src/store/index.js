import Vue from 'vue'
import Vuex from 'vuex'
import person from './modules/person'
import group from './modules/group'
import login from './modules/login'
Vue.use(Vuex)
export default new Vuex.Store({
  //actions,
  //getters,
  modules: {
    person,
    group,
    login
  }
})