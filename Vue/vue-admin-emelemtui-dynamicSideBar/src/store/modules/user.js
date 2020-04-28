import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import store from "@/store";

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    menus:[]
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: () => {
    Object.assign(state,getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_MENUS: (state, menus) => {
    state.menus = menus
  },
  SET_PERMISSIONS: (state, permissions) => {
    state.permissions = permissions
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        //模拟请求回来的栏目树数据,后端根据角色递归组装
        const menus =
          [
            {
              path: '/a',
              component: 'Layout',
              meta: { title: '请求栏目', icon: 'form' },
              children: [
                {
                  path: '/form',
                  name: 'form',
                  component: 'form/index',    //该组件位置在views/form/index.vue,
                  // form前加不加 /，取决于你的router/_import_  文件中的@/view、后面是否有/
                  meta: { title: '通讯录', icon: 'form' }
                },
                {
                  path: '/table',
                  name: 'table',
                  component: 'table/index',    //该组件位置在views/form/index.vue,
                  // form前加不加 /，取决于你的router/_import_  文件中的@/view、后面是否有/
                  meta: { title: '列表', icon: 'table' }
                }
              ]
            }
          ]

        //如果需要404 页面，请在此处添加
        menus.push(
          {
            path: '/404',
            component: '404',
            hidden: true
          },
          {
            path: '*',
            redirect: '/404',
            hidden: true
          })
        const { name, avatar,permissions} = data
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        commit('SET_MENUS', menus)
        commit('SET_PERMISSIONS', permissions)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        console.log(store.getters.name)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

