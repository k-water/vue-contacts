import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
import * as types from '../mutations-types'
Vue.use(Vuex)
Vue.use(VueResource)

const state = {
  getItems: []
}

const getters = {
  getItems: state => state.getItems
}

const actions = {
  GET_GROUP({ commit }) {
    Vue.http.get('http://119.29.151.195:8080/ContactsBe/getGroup').then(response => {
      commit(types.GET_GROUP, { list: JSON.parse(response.body) })
    }, error => {
      return console.log(error)
    })
  },
  ADD_GROUP({ commit }, params) {
    commit(types.ADD_GROUP, params)
    Vue.http.post('http://119.29.151.195:8080/ContactsBe/addGroup', params).then(response => {
      console.log('addGroup status is: ' + response.status)
    }, error => {
      return console.log(error)
    })
  },
  DEL_GROUP({ commit }, group, index) {
    Vue.http.post('http://119.29.151.195:8080/ContactsBe/delGroup', group).then(response => {
      console.log('delGroup status is: ' + response.status)
    }, error => {
      return console.log(error)
    })

    // commit(types.DEL_GROUP, index)
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
