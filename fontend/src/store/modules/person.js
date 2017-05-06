import Vue from 'vue'
import Vuex from 'vuex'
import * as types from '../mutations-types'
const qs = require('qs')
Vue.use(Vuex)
const state = {
  contacts: []
}

const getters = {
  allContacts: state => state.contacts
}
const _this = Vue.prototype
const actions = {
  GET_PERSON({ commit }) {
    _this.$http.get('http://localhost:3000/api/users/getPerson').then((res) => {
      commit(types.GET_PERSON, { contacts: res.data })
    }, error => {
      console.log(error)
    })
  },
  FUZZY_QUERY({ commit }, fkey) {
    _this.$http.post('http://119.29.151.195:8080/ContactsBe/SearchPerson', qs.stringify({ key: fkey })).then(res => {
      commit(types.GET_PERSON, { contacts: res.data })
    }, err => {
      return console.log(err)
    })
  },
  async ADD_PERSON({ commit }, list) {
    await commit(types.ADD_PERSON, list)
    await _this.$http.post('http://119.29.151.195:8080/ContactsBe/addPerson', qs.stringify(list)).then(response => {
      console.log('addPerson status is: ' + response.status)
    }, error => {
      console.log(error)
    })
  },
  DEL_PERSON({ commit }, person) {
    _this.$http.post('http://localhost:3000/api/users/delPerson', qs.stringify(person)).then(res => {
      console.log(res.data.message)
    }, error => {
      console.log(error)
    })
  },
  UPDATE_PERSON({ commit }, person) {
    _this.$http.post('http://119.29.151.195:8080/ContactsBe/updatePerson', qs.stringify(person)).then(response => {
      console.log('update status is: ' + response.status)
    }, error => {
      console.log(error)
    })
  }
}

const mutations = {
  [types.GET_PERSON](state, { contacts }) {
    state.contacts = contacts
  },
  [types.DEL_PERSON](state, index) {
    state.contacts.splice(index, 1)
  },
  [types.ADD_PERSON](state, person) {
    state.contacts.push(
      person
    )
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}