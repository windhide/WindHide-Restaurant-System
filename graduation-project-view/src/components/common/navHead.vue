<template>
    <el-header style="text-align: right; font-size: 12px">
        <div class="toolbar">
            <el-dropdown>
                <el-icon style="margin-right: 8px; margin-top: 1px">
                    <setting />
                </el-icon>
                <template #dropdown v-if="loginName != ''">
                    <el-dropdown-menu>
                        <el-dropdown-item @click="logOut()">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
            <span v-if="loginName == ''"><a href="/userLogin">请先去登陆</a></span>
            <span v-else>{{ "欢迎您" + loginName }}</span>
        </div>
    </el-header>
</template>

<script lang="ts" setup>
import { Setting } from '@element-plus/icons-vue'
import store from '@/store/index'
import router from '@/router';
let loginName: any = ""
if (localStorage.getItem("Aunother") == undefined) loginName = ""
else loginName = localStorage.getItem("Aunother")
function logOut() {
    store.commit('logOut')
    router.push("/userLogin")
}
</script>