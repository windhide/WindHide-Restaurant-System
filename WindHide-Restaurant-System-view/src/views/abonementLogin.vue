<template>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6 col-md-6 form-container">
				<div class="col-lg-8 col-md-12 col-sm-9 col-xs-12 form-box text-center">
					<div class="heading mb-3">
						<h4>登录您的账号</h4>
					</div>
						<div class="form-input">
							<span><i class="fa fa-user"></i></span>
							<input type="text" placeholder="用户名" v-model="loginForm.userName" required>
						</div>
						<div class="form-input">
							<span><i class="fa fa-lock"></i></span>
							<input type="password" placeholder="密码" v-model="loginForm.userPassword" required>
						</div>
						<div class="text-left mb-3">
							<button class="btn" @click="Login">登录</button>
						</div>
						<div class="text-white">没有账号?
							<a href="/register" class="register-link">马上注册！</a>
						</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 d-none d-md-block image-container"></div>
		</div>
	</div>
</template>

<script lang="ts" setup>
import axios from '@/apis/axiosApis';
import {FORM_STATS_JUDGE} from '@/apis/normalCrudApi';
import router from '@/router';
import store from '@/store';
import {ElMessage} from 'element-plus';
import {reactive} from 'vue';

const loginForm = reactive({
    userName: "",
    userPassword: "",
})

function Login() {
    if (FORM_STATS_JUDGE(loginForm)) {
        axios.post("auth/login/user", loginForm).then(async (res) => {
            console.log(res.data)
			if (res.data.code === 0) {
				ElMessage({ type: 'success', message: '登录成功，正在跳转...' })
				await store.commit("commonSignIn", {
					refreshToken: res.data.data.refreshToken,
					token: res.data.data.token,
					userId: res.data.data.userId,
					userName: res.data.data.userName,
					nickName: res.data.data.nickName,
					identity: res.data.data.identity,
					identityType: res.data.data.identityType
				})
				await router.push("/")
			}else{
				ElMessage({ type: 'error', message: res.data.msg, })
			}
		}).catch(() => {
			ElMessage({ type: 'error', message: '账号或者密码有误！请检查！', })
		})
	}
}
</script>
<style scoped>
.form-box button {
    border: none;
    cursor: pointer;
    width: 150px;
    height: 40px;
    border-radius: 5px;
    background-color: #fff;
    color: #000;
    font-weight: bold;
    transition: 0.5s;
}
</style>