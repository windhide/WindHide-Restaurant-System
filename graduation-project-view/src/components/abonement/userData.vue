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
                    头像: 
                    <img :src="'/api/static/avatar/' + user.userImage" width="100" />
                    <br /><br />
                    <!-- 头像占位符 -->
                    昵称:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userNickname }} <br /><br />
                    登陆用户名:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userName }} <br /><br />
                    用户性别:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userGender }} <br /><br />
                    手机号码:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userPhone }} <br /><br />
                    用户等级:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.userLevelId }} <br /><br />
                    注册时间:&nbsp;&nbsp;&nbsp;&nbsp;{{ user.createTime }} <br /><br />
                </div>
            </el-card>
        </el-space>
    </div>

    <el-dialog v-model="EDIT_DIALOG" title="用户信息修改" width="30%" align-center draggable :before-close="closeDialog">
        <el-form :model="userEditForm">
            <el-form-item label="用户Id" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userId" disabled />
            </el-form-item>

            <el-form-item label="登录名" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userName" autocomplete="off" disabled />
            </el-form-item>
            <el-form-item label="等级" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userLevelId" autocomplete="off" disabled />
            </el-form-item>
            <el-form-item label="用户昵称" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userNickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户头像" :label-width="formLabelWidth">
                <el-upload accept="image/*" ref="EditUpload" action="/api/upload/avatar" list-type="picture-card"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :limit="2"
                    :on-success="userSuccessAdd">
                    <el-icon class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
                <!-- 占位符 -->
            </el-form-item>
            <el-form-item label="用户手机号" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userPhone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户性别" :label-width="formLabelWidth">
                <el-select v-model="userEditForm.userGender" placeholder="选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="男"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="登录密码" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userPassword" autocomplete="off" />
            </el-form-item>
            <el-form-item label="当前头像" :label-width="formLabelWidth">
                <el-image style="width: 100px; height: 100px" :src="'/api/static/avatar/' + EditNowImage"
                    fit="scale-down" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelDialog('EDIT')">取消</el-button>
                <el-button type="primary" @click="EDIT(URL, 'null', 2)">修改</el-button>
            </span>
        </template>
    </el-dialog>
    <el-dialog v-model="dialogindialogVisible" :append-to-body="true">
        <img style="width:60%" :src="dialogImageUrl" alt="">
    </el-dialog>
</template>

<script lang="ts" setup>
import axios from '@/apis/axiosApis';
import { CLEAR_FORM, CURRENCY_OPERATION_API, CURRENCY_REQUEST } from '@/apis/normalCrudApi';
import store from '@/store';
import { ElMessageBox, UploadInstance } from 'element-plus';
import { reactive, ref } from 'vue';
const URL = "user"
const formLabelWidth = '100px' // dialog中组件的宽度
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
let EditUpload = ref<UploadInstance>()// 对应上传的ref
let dialogImageUrl = ref('')
let dialogindialogVisible = ref(false)
let EditNowImage = ref("");

let user: any = reactive({
    userId: '',
    userGender: '',
    userImage: '',
    userLevel: '',
    userLevelId: '',
    userName: '',
    userNickname: '',
    userPassword: '',
    userPhone: '',
    createTime: ''
})
let userEditForm: any = ref({});
user.userId = localStorage.getItem("userId")
user.userUserName = localStorage.getItem("username")
function EDITINSTALL() {
    userEditForm = user;
    userEditForm.userPassword = "";
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
    axios.post("/user/select/" + localStorage.getItem("userId")).then(res => {
        if(res.data.code === 2002){
            store.commit("commonSignOut",{pathGo:"user"})
        }
        user.userId = res.data.responeData[0].userId
        user.userGender = res.data.responeData[0].userGender
        user.userImage = res.data.responeData[0].userImage
        user.userLevel = res.data.responeData[0].userLevel
        user.userLevelId = res.data.responeData[0].userLevelId
        user.userName = res.data.responeData[0].userName
        user.userNickname = res.data.responeData[0].userNickname
        user.userPassword = res.data.responeData[0].userPassword
        user.userPhone = res.data.responeData[0].userPhone
        user.createTime = res.data.responeData[0].createTime
    })
}


function handlePictureCardPreview(file: any) {
    dialogImageUrl = file.url;
    dialogindialogVisible.value = true;
}
function handleRemove() {
    if (userEditForm.userImage != "")
        axios.post("/uploadRemove/avatar/" + userEditForm.userImage)
    userEditForm.userImage = ''
}

function closeDialog() {
    ElMessageBox.confirm('确认关闭？').then(async (_) => {
        axios.post("/uploadRemove/avatar/" + userEditForm.userImage)
        EDIT_DIALOG.value = false
        EditUpload.value!.clearFiles();
    }).catch(_ => { });
}


function userSuccessAdd(response: any, file: any, fileList: any) { //增加的上传图片方法
    if (fileList.length > 1) {
        axios.post("/uploadRemove/avatar/" + fileList[0].response)
        fileList.splice(0, 1);
    }
    userEditForm.userImage = response
}

function cancelDialog(addOrEdit: String) {
    if (addOrEdit == "EDIT") {
        EditUpload.value!.clearFiles();
        EDIT_DIALOG.value = false
        axios.post("/uploadRemove/avatar/" + userEditForm.userImage)
        userEditForm = CLEAR_FORM(userEditForm)
        EditNowImage.value = ""
    }
    RELOAD()
}
RELOAD()
</script>
