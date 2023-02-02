<template>
    <el-scrollbar>
        <el-table :data="employeeTypeList" max-height="800">
            <el-table-column prop="employeeTypeId" label="员工Id" width="100" />
            <el-table-column prop="employeeTypeName" label="员工名" width="100" />
            <el-table-column prop="employeeTypeUserName" label="账号" width="100" />
            <el-table-column prop="employeeTypeSex" label="性别" width="100" />
            <el-table-column prop="employeeTypePhone" label="电话号码" width="300" />
            <el-table-column prop="pharmacy.pharmacyName" label="所属药房" width="300" />
            <el-table-column prop="authority.authorityName" label="职位" width="100" />
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
    </el-scrollbar>

    <el-dialog v-model="EDIT_DIALOG" title="员工信息修改" width="30%" align-center>
        <el-form :model="employeeTypeEditForm">
            <el-form-item label="员工名字" :label-width="formLabelWidth">
                <el-input v-model="employeeTypeEditForm.employeeTypeName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="员工账号" :label-width="formLabelWidth">
                <el-input v-model="employeeTypeEditForm.createTime" autocomplete="off" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="EDIT_DIALOG = false">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL, 'null', 2)">修改</el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="ADD_DIALOG" title="员工信息添加" width="30%" align-center>
        <el-form :model="employeeTypeAddForm">
            <el-form-item label="员工名字" :label-width="formLabelWidth">
                <el-input v-model="employeeTypeAddForm.employeeTypeName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="员工账号" :label-width="formLabelWidth">
                <el-input v-model="employeeTypeAddForm.createTime" autocomplete="off" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="ADD_DIALOG = false">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL, 'null', 1)">添加</el-button>
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

let cacheData = "";
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
const ADD_DIALOG = ref(false) // 添加dialog窗口开关
const formLabelWidth = '100px' // dialog中组件的宽度
const URL = "employeeType" // 本组件内通用的url
let employeeTypeList: any = reactive([])
let employeeTypeEditForm = reactive({
    employeeTypeId: 0,
    employeeTypeName: "",
    createTime: "",
})
let employeeTypeAddForm = reactive({
    employeeTypeId: 0,
    employeeTypeName: "",
    createTime: "",
})
function RELOAD() {
    setTimeout((_: any) => {
        CURRENCY_SELECT(URL,0,100)?.then(res => {
            employeeTypeList.length = 0
            employeeTypeList.push(...res.data)
        })
    }, 200)
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
    await CURRENCY_REQUEST(url, { employeeTypeId: data.employeeTypeId }, CURRENCY_OPERATION_API(operationId, data.employeeTypeName))
    RELOAD()
}
async function ADD(url: String, data: any, operationId: Number) {
    ADD_DIALOG.value = !ADD_DIALOG.value;
    employeeTypeAddForm.employeeTypeId = NaN;
    if (!ADD_DIALOG.value && data == 'null' && FORM_STATS_JUDGE(employeeTypeAddForm)) {
        await CURRENCY_REQUEST(url, employeeTypeAddForm, CURRENCY_OPERATION_API(operationId, "->" + employeeTypeAddForm.employeeTypeName))
        employeeTypeAddForm = CLEAR_FORM(employeeTypeAddForm)
    }
    RELOAD()
}
async function EDIT(url: String, data: any, operationId: Number) {
    if (!EDIT_DIALOG.value && data != 'null') {
        employeeTypeEditForm.employeeTypeId = data.employeeTypeId
        employeeTypeEditForm.employeeTypeName = data.employeeTypeName
        employeeTypeEditForm.createTime = data.createTime
    }
    EDIT_DIALOG.value = !EDIT_DIALOG.value;
    if (!EDIT_DIALOG.value && data == 'null') {
        await CURRENCY_REQUEST(url, employeeTypeEditForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + employeeTypeEditForm.employeeTypeName))
        RELOAD()
        employeeTypeEditForm = CLEAR_FORM(employeeTypeEditForm)
    }
}
RELOAD()
</script>