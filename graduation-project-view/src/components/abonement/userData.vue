<template>
    <div>
        <el-space fill wrap :fill-ratio="85" direction="vertical" style="width: 100%">
            <el-card class="box-card">
                <template #header>
                    <div class="card-header">
                        <span>尊敬的用户: {{ user.userName }}您好！</span>
                        <el-button class="button" text @click="EDITINSTALL">修改</el-button>
                    </div>
                </template>
                <div class="text item">
                    序号:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userId }} <br /><br />
                    昵称:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userName }} <br /><br />
                    登陆用户名:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userUserName }} <br /><br />
                    用户性别:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userSex }} <br /><br />
                    手机号码:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userPhone }} <br /><br />
                    用户等级:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userLevelTypeId }} <br /><br />
                    注册时间:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.createTime }} <br /><br />
                </div>
            </el-card>
        </el-space>
    </div>

    <el-dialog v-model="EDIT_DIALOG" title="权限信息修改" width="30%" align-center>
        <el-form :model="userEditForm">
            <el-form-item label="昵称" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userName" />
            </el-form-item>
            <el-form-item label="用户名" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userUserName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userPassWord" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户性别" :label-width="formLabelWidth">
                <el-select v-model="userEditForm.userSex" placeholder="选择性别">
                    <el-option label="男" value="男" />
                    <el-option label="女" value="女" />
                </el-select>
            </el-form-item>
            <el-form-item label="电话号码" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userPhone" autocomplete="off" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="EDIT_DIALOG = false">取消</el-button>
                <el-button type="primary" @click="EDIT(URL, 'null', 2)">修改</el-button>
            </span>
        </template>
    </el-dialog>

</template>

<script lang="ts" setup>
import { CURRENCY_OPERATION_API, CURRENCY_REQUEST} from '@/apis/normalCrudApi';
import { ElMessage } from 'element-plus';
import { reactive, ref } from 'vue';
const URL = "user"
const formLabelWidth = '100px' // dialog中组件的宽度
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
let user: any = reactive({
    userId: 0,
    userName: '',
    userUserName: '',
    userPassWord: '',
    userSex: '',
    userPhone: '',
    userLevelTypeId: '',
    createTime: '',
})
let userEditForm: any = reactive({
    userId: 0,
    userName: '',
    userUserName: '',
    userPassWord: '',
    userSex: '',
    userPhone: '',
    userLevelTypeId: '',
    createTime: '',
})
user.userId = localStorage.getItem("userId")
user.userUserName = localStorage.getItem("username")
function EDITINSTALL() {
    userEditForm.userId = user.userId
    userEditForm.userPassWord = user.userPassWord
    userEditForm.userName = user.userName
    userEditForm.userUserName = user.userUserName
    userEditForm.userSex = user.userSex
    userEditForm.userPhone = user.userPhone
    userEditForm.createTime = user.createTime
    EDIT_DIALOG.value = true
}
async function EDIT(url: String, data: any, operationId: Number) {
    if (userEditForm.userPassWord == "") {
        userEditForm.userPassWord = user.userPassWord
    }
    await CURRENCY_REQUEST(url, userEditForm, CURRENCY_OPERATION_API(operationId, user.username))
    RELOAD()
    EDIT_DIALOG.value = false;
}
function RELOAD() {
    // CURRENCY_SELECT_BY_CONDITION(URL + "/select/userData", user)?.then(res => {
    //         user.userName = res.data.userName;
    //         user.userSex = res.data.userSex;
    //         user.userPhone = res.data.userPhone;
    //         user.userLevelTypeId = res.data.userLevelTypeId;
    //         user.createTime = res.data.createTime;
    // })
}
RELOAD()
</script>

<style>
</style>