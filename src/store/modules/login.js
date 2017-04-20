import Vue from 'vue'
import Vuex from 'vuex'
import * as types from '../mutations-types'
Vue.use(Vuex)

const state = {
  user: {
    name: ''
  }
}

const actions = {
  userLogin({ commit }, data) {
    commit(types.LOGIN, data)
  },
  delUserSession({ commit }, data) {
    commit(types.DELSESSION, data)
  },
  userLoginOut({ commit }) {
    commit(types.LOGINOUT)
  }
}

const mutations = {
  [types.LOGIN](state, data) {
    state.user.name = data.name
    localStorage.setItem('user', data.name)
  },
  [types.DELSESSION](state) {
    localStorage.removeItem('session')
  },
  [types.LOGINOUT](state) {
    state.user = {}
    localStorage.removeItem('user')
  }
}

export default {
  state,
  actions,
  mutations
}