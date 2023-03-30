<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6 col-md-6 form-container">
                <div class="col-lg-8 col-md-12 col-sm-9 col-xs-12 form-box text-center">
                    <div class="heading mb-3">
                        <h4>注册账号</h4>
                    </div>
                    <div class="form-input">
                        <span><i class="fa fa-user"></i></span>
                        <input type="text" placeholder="昵称" v-model="registerForm.userNickname" required>
                    </div>
                    <div class="form-input">
                        <span><i class="fa fa-phone"></i></span>
                        <input type="text" placeholder="手机号" v-model="registerForm.userPhone" required>
                    </div>
                    <div class="text-left mb-3">
                        <button class="btn" style="background-color: white;">性别：
                            <select class="btn" v-model="registerForm.userGender">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </button>
                    </div>
                    <div class="form-input">
                        <span><i class="fa fa-lock"></i></span>
                        <input type="text" placeholder="用户名" v-model="registerForm.userName" required>
                    </div>
                    <div class="form-input">
                        <span><i class="fa fa-lock"></i></span>
                        <input type="password" placeholder="密码" v-model="registerForm.userPassword" required>
                    </div>
                    <div class="form-input">
                        <span><i class="fa fa-lock"></i></span>
                        <input type="password" placeholder="确认密码" v-model="secondPassword" required>
                    </div>
                    <div class="text-left mb-3">
                        <button type="submit" class="btn" @click="RegisterAccount">注册</button>
                    </div>
                    <div class="text-white">你有账号了?
                        <a href="/userLogin" class="login-link">去登录</a>
                    </div>
                </div>
            </div>

            <div class="col-lg-6 col-md-6 d-none d-md-block image-container"></div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { CLEAR_FORM, CURRENCY_OPERATION_API, CURRENCY_REQUEST, FORM_STATS_JUDGE } from '@/apis/normalCrudApi';
import router from '@/router';
import { ElNotification } from 'element-plus';
import { reactive,ref } from 'vue';

let registerForm:any = reactive({
    // userId: 0,
    userNickname: "",
    // userImage: "",
    userPhone: "",
    userGender: "",
    userName: "",
    userPassword: "",
    // userLevelId: "",
    // createTime: "",
})
let secondPassword = ref("")
const URL = "user" // 本组件内通用的url

async function RegisterAccount(){
    if(registerForm.userPassword != secondPassword.value){
        ElNotification({ title: '两次密码输入的不一致！', type: 'error' })
        secondPassword.value = ""
        return;
    }
    if(FORM_STATS_JUDGE(registerForm)){
        let cacheForm:any = {};
        cacheForm = registerForm
        cacheForm.userImage = "#"
        cacheForm.userLevelId = 1
        await CURRENCY_REQUEST(URL, registerForm, CURRENCY_OPERATION_API(1, "->" + "注册您的账号")).then(res=>{
                 ElNotification({ title: '注册成功，前往登录', type: 'success' })
                 setTimeout(() => {
                    router.push("/userLogin")
                }, 500);
            secondPassword.value = ""
            registerForm = CLEAR_FORM(registerForm)
        })
    }
}

</script>
