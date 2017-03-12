import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
import * as types from '../mutations-types'
Vue.use(Vuex)
Vue.use(VueResource)
const state = {
  contacts: []
}

const getters = {
  allContacts: state => state.contacts
}

const actions = {

  GET_PERSON({ commit }) {
    Vue.http.get('http://localhost:8081/ContactsBe/getPerson').then((res) => {
      commit(types.GET_PERSON, { contacts: JSON.parse(res.body) })
    }, error => {
      console.log(error)
    })
  },
  FUZZY_QUERY({ commit }, fkey) {
    if (fkey === '') {
      return confirm('查询的内容不能为空，请重新输入！！')
    } else {
      Vue.http.post('http://localhost:8081/ContactsBe/SearchPerson', { key: fkey }).then(res => {
        commit(types.GET_PERSON, { contacts: JSON.parse(res.body) })
        let len = state.contacts.length
        if (len === 0) {
          return confirm('您查询的联系人不存在...')
        } else {
          return confirm('查询到了' + len + '位联系人')
        }
      }, err => {
        return console.log(err)
      })
    }
  },
  async ADD_PERSON({ commit }, list) {
    await commit(types.ADD_PERSON, list)
    await Vue.http.post('http://localhost:8081/ContactsBe/addPerson', list).then(response => {
      console.log('addPerson status is: ' + response.status)
    }, error => {
      console.log(error)
    })
  },
  async DEL_PERSON({ commit }, person, index) {
    Vue.http.post('http://localhost:8081/ContactsBe/delPerson', person).then(response => {
      console.log('delPerson status is: ' + response.status)
    }, error => {
      console.log(error)
    })
    commit(types.DEL_PERSON, index)
  },
  UPDATE_PERSON({ commit }, person) {
    Vue.http.post('http://localhost:8081/ContactsBe/updatePerson', person).then(response => {
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
