<template>
    <el-scrollbar>
        <el-table :data="userList" max-height="700">
            <el-table-column prop="userId" label="用户id" width="120" />
            <el-table-column prop="userNickname" label="用户昵称" width="150" />
            <el-table-column align="center" label="用户头像" width="200">
                <template #default="scope">
                    <img :src="'/api/static/avatar/' + scope.row.userImage" width="100" />
                </template>
            </el-table-column>
            <el-table-column prop="userPhone" label="用户电话号码" width="150" />
            <el-table-column prop="userGender" label="用户性别" width="150" />
            <el-table-column prop="userName" label="登录名" width="200" />
            <el-table-column prop="userLevel.userLevelName" label="用户等级" width="150" />
            <el-table-column prop="createTime" label="创建时间" width="300" />
            <el-table-column fixed="right" label="操作">
                <template #header>
                    操作
                </template>
                <template #default="scope">
                    <el-button type="primary" :icon="Edit" circle @click="CURRENCY_CRUD(URL, scope.row, 2)" />
                    <el-button type="danger" :icon="Delete" circle @click="CURRENCY_CRUD(URL, scope.row, 3)" />
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-if="SHOW_PAGINATION" background :page-size="pageSize" layout="prev, pager, next"
            :total="totals" :current-page="nowPage" hide-on-single-page @current-change="handleSizeChange" />
    </el-scrollbar>

    <el-dialog v-model="EDIT_DIALOG" title="用户信息修改" width="30%" align-center draggable :before-close="closeDialog">
        <el-form :model="userEditForm">
            <el-form-item label="用户Id" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userId" disabled />
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
            <el-form-item label="用户登录名" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户登录密码" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userPassword" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户等级" :label-width="formLabelWidth">
                <el-select v-model="userEditForm.userLevelId" placeholder="选择用户等级">
                    <el-option v-for="userLevel in userLevellist" :label=userLevel.userLevelName
                        :value=userLevel.userLevelId />
                </el-select>
            </el-form-item>
            <el-form-item label="用户当前头像" :label-width="formLabelWidth">
                <el-image style="width: 100px; height: 100px" :src="'/api/static/avatar/' + EditNowImage"
                    fit="scale-down" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelDialog('EDIT')">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL, 'null', 2)">修改</el-button>
            </span>
        </template>
    </el-dialog>
    <el-dialog v-model="dialogindialogVisible" :append-to-body="true">
        <img style="width:60%" :src="dialogImageUrl" alt="">
    </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { Delete, Edit } from '@element-plus/icons-vue'
import {
    CLEAR_FORM,
    CURRENCY_OPERATION_API,
    CURRENCY_REQUEST,
    CURRENCY_SELECT,
} from "@/apis/normalCrudApi"
import axios from '@/apis/axiosApis';
import { ElMessageBox, UploadInstance } from 'element-plus';


let dialogImageUrl = ref('')
let dialogindialogVisible = ref(false)
let EditUpload = ref<UploadInstance>()// 对应上传的ref
let userList: any = reactive([])
const URL = "user" // 本组件内通用的url
const formLabelWidth = '100px' // dialog中组件的宽度
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
let pageSize = ref(1); // 给初始值
let totals = ref(1); // 给初始值
let nowPage = ref(1);
let cacheData = "";
let SHOW_PAGINATION = ref(true);
let EditNowImage = ref("");

let userLevellist: any = reactive([])

CURRENCY_SELECT("userLevel", 1, 100)?.then(res => {
    userLevellist.length = 0
    userLevellist.push(...res.data.responeData)
})


let userEditForm = reactive({
    userId: 0,
    userNickname: "",
    userImage: "",
    userPhone: "",
    userGender: "",
    userName: "",
    userPassword: "",
    userLevelId: "",
    createTime: "",
})
function RELOAD() {
    CURRENCY_SELECT(URL, nowPage.value, 30)?.then(res => { // 赋值unit
        userList.length = 0
        console.log(res.data)
        userList.push(...res.data.responeData.data);
    })
}

async function CURRENCY_CRUD(url: String, data: any, operationId: Number) {
    switch (operationId) {
        case 2: // 2是修改
            await EDIT(url, data, operationId)
            break;
        case 3: // 3是删除
            await DELETE(url, data, operationId)
            break;
        default:
            console.error("错误的操作！");
            return
    }
}

async function DELETE(url: String, data: any, operationId: Number) {
    await CURRENCY_REQUEST(url, { removeId: data.userId }, CURRENCY_OPERATION_API(operationId, data.userName))
    await RELOAD()
}
async function EDIT(url: String, data: any, operationId: Number) {
    if (!EDIT_DIALOG.value && data != 'null') {
        userEditForm.userId = data.userId
        userEditForm.userNickname = data.userNickname
        // userEditForm.userImage = data.userImage
        userEditForm.userPhone = data.userPhone
        userEditForm.userGender = data.userGender
        userEditForm.userName = data.userName
        userEditForm.userPassword = data.userPassword
        userEditForm.userLevelId = data.userLevelId
        userEditForm.createTime = data.createTime
        EditNowImage.value = data.userImage
        cacheData = data
    }
    EDIT_DIALOG.value = !EDIT_DIALOG.value;
    if (!EDIT_DIALOG.value && data == 'null') {
        let cacheForm: any = reactive({})
        cacheForm.userId = userEditForm.userId
        cacheForm.userNickname = userEditForm.userNickname
        cacheForm.userGender = userEditForm.userGender
        cacheForm.userName = userEditForm.userName
        cacheForm.userLevelId = userEditForm.userLevelId
        userEditForm.userImage != "" ? cacheForm.userImage = userEditForm.userImage : null
        cacheForm.userPassword = userEditForm.userPassword
        if (cacheForm.userImage == null) {
            await CURRENCY_REQUEST(url, cacheForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + userEditForm.userName))
        } else {
            await CURRENCY_REQUEST(url, cacheForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + userEditForm.userName))
            await axios.post("/uploadRemove/avatar/" + EditNowImage.value)
        }
        RELOAD()
        EditUpload.value!.clearFiles();
        userEditForm = CLEAR_FORM(userEditForm)
        EditNowImage.value = ""
    }
}

function handleSizeChange(val: number) {
    setTimeout(() => {
        CURRENCY_SELECT(URL, val, 30).then(res => {
            userList.length = 0
            nowPage.value = val
            pageSize.value = res.size
            totals.value = res.total
            userList.push(...res.data)
        })
    }, 100);
};

function userSuccessAdd(response: any, file: any, fileList: any) { //增加的上传图片方法
    if (fileList.length > 1) {
        axios.post("/uploadRemove/avatar/" + fileList[0].response)
        fileList.splice(0, 1);
    }
    userEditForm.userImage = response
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

function cancelDialog(addOrEdit: String) {
    if (addOrEdit == "EDIT") {
        EditUpload.value!.clearFiles();
        EDIT_DIALOG.value = false
        axios.post("/uploadRemove/avatar/" + userEditForm.userImage)
        userEditForm = CLEAR_FORM(userEditForm)
        EditNowImage.value = ""
    }
}
RELOAD();
</script>