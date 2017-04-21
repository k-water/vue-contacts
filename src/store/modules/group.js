import Vue from 'vue'
import Vuex from 'vuex'
import * as types from '../mutations-types'
Vue.use(Vuex)
const qs = require('qs')
const state = {
  getItems: []
}

const getters = {
  getItems: state => state.getItems
}
const _this = Vue.prototype
const actions = {
  GET_GROUP({ commit }) {
    _this.$http.get('http://119.29.151.195:8080/ContactsBe/getGroup').then(response => {
      commit(types.GET_GROUP, { list: response.data })
    }, error => {
      return console.log(error)
    })
  },
  ADD_GROUP({ commit }, params) {
    commit(types.ADD_GROUP, params)
    _this.$http.post('http://119.29.151.195:8080/ContactsBe/addGroup', qs.stringify(params)).then(response => {
      console.log('addGroup status is: ' + response.status)
    }, error => {
      return console.log(error)
    })
  },
  DEL_GROUP({ commit }, group, index) {
    _this.$http.post('http://119.29.151.195:8080/ContactsBe/delGroup', qs.stringify(group)).then(response => {
      console.log('delGroup status is: ' + response.status)
    }, error => {
      return console.log(error)
    })
  }
}

const mutations = {
  [types.GET_GROUP](state, { list }) {
    state.getItems = list
  },
  [types.ADD_GROUP](state, add) {
    state.getItems.push(
      add
    )
  },
  [types.DEL_GROUP](state, index) {
    state.getItems.splice(index, 1)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}