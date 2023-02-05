<template>
    <el-scrollbar>
        <el-table :data="employeeList" max-height="700">
            <el-table-column prop="employeeId" label="员工id" width="120" />
            <el-table-column prop="employeeName" label="员工姓名" width="150" />
            <el-table-column prop="employeeImage" label="员工照片" width="200" />
            <el-table-column prop="employeeUsername" label="员工用户名" width="150" />
            <el-table-column prop="employeeSalary" label="员工工资(元/月)" width="150" />
            <el-table-column prop="employeeType.employeeTypeName" label="员工职位" width="200" />
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

    <el-dialog v-model="EDIT_DIALOG" title="员工信息修改" width="30%" align-center>
        <el-form :model="employeeEditForm">
            <el-form-item label="员工Id" :label-width="formLabelWidth">
                <el-input v-model="employeeEditForm.employeeId" disabled />
            </el-form-item>
            <el-form-item label="员工名字" :label-width="formLabelWidth">
                <el-input v-model="employeeEditForm.employeeName" autocomplete="off" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="EDIT_DIALOG = false">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL,'null',2)">修改</el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="ADD_DIALOG" title="员工添加" width="30%" align-center>
        <el-form :model="employeeAddForm">
            <el-form-item label="员工名字" :label-width="formLabelWidth">
                <el-input v-model="employeeAddForm.employeeName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="员工照片" :label-width="formLabelWidth">
                <!-- 占位符 -->
            </el-form-item>
            <el-form-item label="员工用户名" :label-width="formLabelWidth">
                <el-input v-model="employeeAddForm.employeeUsername" autocomplete="off" />
            </el-form-item>
            <el-form-item label="员工密码" :label-width="formLabelWidth">
                <el-input v-model="employeeAddForm.employeePassword" autocomplete="off" />
            </el-form-item>
            <el-form-item label="员工工资" :label-width="formLabelWidth">
                <el-input v-model="employeeAddForm.employeeSalary" autocomplete="off" />
            </el-form-item>
            <el-form-item label="员工职位" :label-width="formLabelWidth">
                <el-select v-model="employeeAddForm.employeeTypeId" placeholder="选择职位">
                    <el-option v-for="employeeType in employeeTypelist" :label=employeeType.employeeTypeName :value=employeeType.employeeTypeId />
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

let employeeList: any = reactive([])
const URL = "employee" // 本组件内通用的url
const formLabelWidth = '100px' // dialog中组件的宽度
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
const ADD_DIALOG = ref(false) // 添加dialog窗口开关
let pageSize = ref(1); // 给初始值
let totals = ref(1); // 给初始值
let nowPage = ref(1);
let cacheData = "";
let SHOW_PAGINATION = ref(true);

let employeeTypelist: any = reactive([])

CURRENCY_SELECT("employeeType",1,100)?.then(res =>{
    employeeTypelist.length = 0
    employeeTypelist.push(...res.data.responeData.data)
})


let employeeEditForm = reactive({
    employeeId: 0,
    employeeName: "",
    employeeImage: "",
    employeeUsername: 0.0,
    employeePassword: 0.0,
    employeeSalary:0,
    employeeTypeId:0,
    employeeType:{},
    creatTime:"",
})
let employeeAddForm = reactive({
    employeeId: 0,
    employeeName: "",
    employeeImage: "",
    employeeUsername: 0.0,
    employeePassword: 0.0,
    employeeSalary:0,
    employeeTypeId:0,
    employeeType:{},
    creatTime:"",
})

function RELOAD() {
    CURRENCY_SELECT(URL, nowPage.value, 30)?.then(res => { // 赋值unit
        employeeList.length = 0
        console.log(res.data)
        employeeList.push(...res.data.responeData.data);
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
    await CURRENCY_REQUEST(url, { removeId: data.employeeId }, CURRENCY_OPERATION_API(operationId, data.employeeName))
    await RELOAD()
}
async function ADD(url: String, data: any, operationId: Number) {
    ADD_DIALOG.value = !ADD_DIALOG.value;
    employeeAddForm.employeeId = NaN;
    if (!ADD_DIALOG.value && data == 'null' && FORM_STATS_JUDGE(employeeAddForm)) {
        await CURRENCY_REQUEST(url, employeeAddForm, CURRENCY_OPERATION_API(operationId, "->" + employeeAddForm.employeeName))
        employeeAddForm = CLEAR_FORM(employeeAddForm)
    }
    RELOAD()
}
async function EDIT(url: String, data: any, operationId: Number) {
    if (!EDIT_DIALOG.value && data != 'null') {
        employeeEditForm.employeeId = data.employeeId
        employeeEditForm.employeeName = data.employeeName
        cacheData = data.employeeName
    }
    EDIT_DIALOG.value = !EDIT_DIALOG.value;
    if (!EDIT_DIALOG.value && data == 'null') {
        await CURRENCY_REQUEST(url, employeeEditForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + employeeEditForm.employeeName))
        RELOAD()
        employeeEditForm = CLEAR_FORM(employeeEditForm)
    }
}

function handleSizeChange(val: number) {
    setTimeout(() => {
        CURRENCY_SELECT(URL, val, 30).then(res => {
            employeeList.length = 0
            nowPage.value = val
            pageSize.value = res.size
            totals.value = res.total
            employeeList.push(...res.data)
        })
    }, 100);
};

RELOAD();
</script>