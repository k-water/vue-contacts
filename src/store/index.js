import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'
import person from './modules/person'
import group from './modules/group'
Vue.use(Vuex)
export default new Vuex.Store({
  actions,
  getters,
  modules: {
    person,
    group
  }
})
