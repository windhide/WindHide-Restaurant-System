import { createStore } from 'vuex'

export default createStore({
  state: {
    Authorization: localStorage.getItem("Authorization") ? localStorage.getItem("Authorization") : '',
    refreshToken: localStorage.getItem("refreshToken") ? localStorage.getItem("refreshToken") : '',
    token: localStorage.getItem("refreshToken") ? localStorage.getItem("refreshToken") : '',
    userId: '',
    userName: '',
    nickName: '',
    identity: '',
    identityType: '',
  },
  getters: {
  },
  mutations: {
    commonSignIn(state, user) {
      state.Authorization = user.token;
      state.refreshToken = user.refreshToken;
      state.token = user.token;
      localStorage.setItem('Authorization', user.token)
      localStorage.setItem('refreshToken', user.refreshToken)
      localStorage.setItem('token', user.token)
      localStorage.setItem('userId', user.userId)
      localStorage.setItem('userName', user.username)
      localStorage.setItem('nickName', user.nickName)
      localStorage.setItem('identity', user.identity)
      localStorage.setItem('identityType', user.identityType)
    },
    commonSignOut(state) {
      state.Authorization = "";
      state.refreshToken = "";
      state.token = "";
      localStorage.setItem('Authorization', "")
      localStorage.setItem('refreshToken', "")
      localStorage.setItem('token', "")
      localStorage.setItem('userId', "")
      localStorage.setItem('userName', "")
      localStorage.setItem('nickName', "")
      localStorage.setItem('identity', "")
      localStorage.setItem('identityType', "")
    }
  },
  actions: {
  },
  modules: {
  }
})
