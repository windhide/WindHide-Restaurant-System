import router from '@/router';
import {ElNotification} from 'element-plus';
import {createStore} from 'vuex'

export default createStore({
    state: {
        Authorization: localStorage.getItem("Authorization") ? localStorage.getItem("Authorization") : '',
        refreshToken: localStorage.getItem("refreshToken") ? localStorage.getItem("refreshToken") : '',
        token: localStorage.getItem("token") ? localStorage.getItem("token") : '',
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
    commonSignOut(state, pathGo) {
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

      ElNotification({ title: '没有登录！即将跳转！', type: 'warning' })
      setTimeout(async function () {
        if (pathGo === "employee") {
          await router.push("/employeeLogin")
        } else if(pathGo === "user") {
          await router.push("/userLogin")
        }
        await router.go(0)
      }, 1000)
    }
  },
  actions: {
  },
  modules: {
  }
})
