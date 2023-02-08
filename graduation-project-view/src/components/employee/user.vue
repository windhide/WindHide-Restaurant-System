<template>
    <el-scrollbar>
        <el-table :data="userList" max-height="700">
            <el-table-column prop="userId" label="用户id" width="120" />
            <el-table-column prop="userNickname" label="用户昵称" width="150" />
            <el-table-column prop="userImage" label="用户照片" width="200" />
            <el-table-column prop="userPhone" label="用户电话号码" width="150" />
            <el-table-column prop="userGender" label="用户性别" width="150" />
            <el-table-column prop="userName" label="登录名" width="200" />
            <el-table-column prop="userLevel.userLevelName" label="用户等级" width="150" />
            <el-table-column prop="createTime" label="创建时间" width="300" />
            <el-table-column fixed="right" label="操作">
                <template #header>
                    操作
                    <el-button type="success" :icon="StarFilled" @click="ADD_DIALOG = true">添加</el-button>
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

    <el-dialog v-model="EDIT_DIALOG" title="用户信息修改" width="30%" align-center>
        <el-form :model="userEditForm">
            <el-form-item label="用户Id" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userId" disabled />
            </el-form-item>
            <el-form-item label="用户昵称" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userNickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户照片" :label-width="formLabelWidth">
                <el-input v-model="userEditForm.userImage" autocomplete="off" />
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
                    <el-option v-for="userLevel in userLevellist" :label=userLevel.userLevelName :value=userLevel.userLevelId />
                </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="EDIT_DIALOG = false">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL,'null',2)">修改</el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="ADD_DIALOG" title="用户添加" width="30%" align-center>
        <el-form :model="userAddForm">
            <el-form-item label="用户昵称" :label-width="formLabelWidth">
                <el-input v-model="userAddForm.userNickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户照片" :label-width="formLabelWidth">
                <el-input v-model="userAddForm.userImage" autocomplete="off" />
                <!-- 占位符 -->
            </el-form-item>
            <el-form-item label="用户手机号" :label-width="formLabelWidth">
                <el-input v-model="userAddForm.userPhone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户性别" :label-width="formLabelWidth">
                <el-select v-model="userAddForm.userGender" placeholder="选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="男"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="用户登录名" :label-width="formLabelWidth">
                <el-input v-model="userAddForm.userName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户登录密码" :label-width="formLabelWidth">
                <el-input v-model="userAddForm.userPassword" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户等级" :label-width="formLabelWidth">
                <el-select v-model="userAddForm.userLevelId" placeholder="选择职位">
                    <el-option v-for="userLevel in userLevellist" :label=userLevel.userLevelName :value=userLevel.userLevelId />
                </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="ADD_DIALOG = false">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL,'null',1)">添加</el-button>
            </span>
        </template>
    </el-dialog>

</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import {Delete, Edit, StarFilled} from '@element-plus/icons-vue'
import {
  CLEAR_FORM,
  CURRENCY_OPERATION_API,
  CURRENCY_REQUEST,
  CURRENCY_SELECT,
  FORM_STATS_JUDGE
} from "@/apis/normalCrudApi"

let userList: any = reactive([])
const URL = "user" // 本组件内通用的url
const formLabelWidth = '100px' // dialog中组件的宽度
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
const ADD_DIALOG = ref(false) // 添加dialog窗口开关
let pageSize = ref(1); // 给初始值
let totals = ref(1); // 给初始值
let nowPage = ref(1);
let cacheData = "";
let SHOW_PAGINATION = ref(true);

let userLevellist: any = reactive([])

CURRENCY_SELECT("userLevel",1,100)?.then(res =>{
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
let userAddForm = reactive({
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
        case 1: // 1是新增
            await ADD(url, data, operationId)
            break;
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
async function ADD(url: String, data: any, operationId: Number) {
    ADD_DIALOG.value = !ADD_DIALOG.value;
    userAddForm.userId = NaN;
    if (!ADD_DIALOG.value && data == 'null' && FORM_STATS_JUDGE(userAddForm)) {
        await CURRENCY_REQUEST(url, userAddForm, CURRENCY_OPERATION_API(operationId, "->" + userAddForm.userNickname))
        userAddForm = CLEAR_FORM(userAddForm)
    }
    RELOAD()
}
async function EDIT(url: String, data: any, operationId: Number) {
    if (!EDIT_DIALOG.value && data != 'null') {
        userEditForm.userId = data.userId
        userEditForm.userNickname = data.userNickname
        userEditForm.userImage = data.userImage
        userEditForm.userPhone = data.userPhone
        userEditForm.userGender = data.userGender
        userEditForm.userName = data.userName
        userEditForm.userPassword = data.userPassword
        userEditForm.userLevelId = data.userLevelId
        userEditForm.createTime = data.createTime
        cacheData = data
    }
    EDIT_DIALOG.value = !EDIT_DIALOG.value;
    if (!EDIT_DIALOG.value && data == 'null') {
        await CURRENCY_REQUEST(url, userEditForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + userEditForm.userName))
        RELOAD()
        userEditForm = CLEAR_FORM(userEditForm)
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

RELOAD();
</script>