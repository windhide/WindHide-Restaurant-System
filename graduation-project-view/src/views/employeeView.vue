<template>
  <el-container class="layout-container-demo">
    <NavBar navigationFatherName="employeeView" />
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
          <div style="position:absolute;line-height: 350%;font-size: 1.5em;">
            饭店餐饮管理系统
          </div>
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
          <span v-if="loginName == ''"><a href="/employeeLogin">请先去登陆</a></span>
          <span v-else>{{ "欢迎您" + loginName }}</span>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import NavBar from "@/components/common/navBar.vue";
import {Setting} from '@element-plus/icons-vue'
import store from '@/store/index'
import router from '@/router';

let loginName: any = ""
if (localStorage.getItem("nickName") == undefined) loginName = ""
else loginName = localStorage.getItem("nickName")
function logOut() {
  store.commit('logOut')
  router.push("/employeeLogin")
}
</script>

<style>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 50px;
}


.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

</style>