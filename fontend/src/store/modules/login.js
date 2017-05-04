import Vue from 'vue'
import Vuex from 'vuex'
import * as types from '../mutations-types'
Vue.use(Vuex)

const state = {
  token: null,
  username: ''
}

const actions = {
  userLogin({ commit }, data) {
    commit(types.LOGIN, data);
  },
  userLoginOut({ commit }) {
    commit(types.LOGINOUT)
  }
}

const mutations = {
  [types.LOGIN](state, data) {
    // 将token和username储存在本地
    localStorage.setItem('token', data.token)
    state.token = data
    localStorage.setItem('username', data.name)
    state.username = data.name
      // vuex的本质作用是管理组件之间复杂的状态的（如购物车逻辑等等...）
      // 所以当刷新浏览器时，这些状态也会一并被清空
      // 所以还是需要有一个长期在浏览器中保存如登录/登出状态的机制
      // 因此这里采用了localStorage
      // 一定要明白vuex这类库的本质作用，它极大的增加了前端逻辑处理的可能性
  },
  [types.LOGINOUT](state) {
    localStorage.removeItem('token');
    state.token = null
    localStorage.removeItem('username');
    state.username = null;
  }
}

export default {
  state,
  actions,
  mutations
}