<template>
    <el-scrollbar>
        <el-table :data="goodsList" max-height="700">
            <el-table-column align="center" prop="goodsId" label="菜品id" width="80" />
            <el-table-column align="center" prop="goodsName" label="菜品名" width="150" />
            <el-table-column align="center" label="菜品图片" width="200">
                <template #default="scope">
                    <img :src="'/api/static/food/' + scope.row.goodsImage" width="100" />
                </template>
            </el-table-column>
            <el-table-column align="center" prop="goodsPrice" label="菜品价格" width="100" />
            <el-table-column align="center" prop="goodsDiscount" label="菜品折扣" width="100" />
            <el-table-column align="center" prop="goodsType.goodsTypeName" label="菜品类型" width="200" />
            <el-table-column align="center" prop="createTime" label="创建时间" width="300" />
            <el-table-column align="center" fixed="right" label="操作">
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

    <el-dialog v-model="EDIT_DIALOG" title="菜品信息修改" width="30%" align-center draggable :before-close="closeDialog">
        <el-form :model="goodsEditForm">
            <el-form-item label="菜品Id" :label-width="formLabelWidth">
                <el-input v-model="goodsEditForm.goodsId" disabled />
            </el-form-item>
            <el-form-item label="菜品名字" :label-width="formLabelWidth">
                <el-input v-model="goodsEditForm.goodsName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品图片" :label-width="formLabelWidth">
                <el-upload accept="image/*" ref="EditUpload" action="/api/upload/food" list-type="picture-card"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :limit="2"
                    :on-success="ItemLinkSuccessAdd">
                    <el-icon class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
                <!-- 占位符 -->
            </el-form-item>
            <el-form-item label="菜品价格" :label-width="formLabelWidth">
                <el-input v-model="goodsEditForm.goodsPrice" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品折扣" :label-width="formLabelWidth">
                <el-input v-model="goodsEditForm.goodsDiscount" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品价格" :label-width="formLabelWidth">
                <el-input v-model="goodsEditForm.goodsPrice" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品类型" :label-width="formLabelWidth">
                <el-select v-model="goodsEditForm.goodsTypeId" placeholder="选择菜品类型">
                    <el-option v-for="goodsType in goodsTypeList" :label=goodsType.goodsTypeName
                        :value=goodsType.goodsTypeId />
                </el-select>
            </el-form-item>
            <el-form-item label="现在的菜品图" :label-width="formLabelWidth">
                <el-image style="width: 100px; height: 100px" :src="'/api/static/food/' + EditNowImage"
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

    <el-dialog v-model="ADD_DIALOG" title="菜品添加" width="30%" align-center draggable :before-close="closeDialog">
        <el-form :model="goodsAddForm">
            <el-form-item label="菜品名字" :label-width="formLabelWidth">
                <el-input v-model="goodsAddForm.goodsName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品图片" :label-width="formLabelWidth">
                <el-upload accept="image/*" ref="AddUpload" action="/api/upload/food" list-type="picture-card"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :limit="2"
                    :on-success="ItemLinkSuccessAdd">
                    <el-icon class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
                <!-- 占位符 -->
            </el-form-item>
            <el-form-item label="菜品价格" :label-width="formLabelWidth">
                <el-input v-model="goodsAddForm.goodsPrice" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品折扣" :label-width="formLabelWidth">
                <el-input v-model="goodsAddForm.goodsDiscount" autocomplete="off" />
            </el-form-item>
            <el-form-item label="菜品类型" :label-width="formLabelWidth">
                <el-select v-model="goodsAddForm.goodsTypeId" placeholder="选择菜品类型">
                    <el-option v-for="goodsType in goodsTypeList" :label=goodsType.goodsTypeName
                        :value=goodsType.goodsTypeId />
                </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelDialog('ADD')">取消</el-button>
                <el-button type="primary" @click="CURRENCY_CRUD(URL, 'null', 1)">添加</el-button>
            </span>
        </template>
    </el-dialog>
    <el-dialog v-model="dialogindialogVisible" :append-to-body="true">
        <img style="width:60%" :src="dialogImageUrl" alt="">
    </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { Delete, Edit, StarFilled } from '@element-plus/icons-vue'
import {
    CLEAR_FORM,
    CURRENCY_OPERATION_API,
    CURRENCY_REQUEST,
    CURRENCY_SELECT,
    FORM_STATS_JUDGE
} from "@/apis/normalCrudApi"
import axios from 'axios';
import { ElMessageBox, UploadInstance } from 'element-plus';

let dialogImageUrl = ref('')
let dialogindialogVisible = ref(false)
let AddUpload = ref<UploadInstance>() // 对应上传的ref
let EditUpload = ref<UploadInstance>()// 对应上传的ref

let goodsList: any = reactive([])
const URL = "goods" // 本组件内通用的url
const formLabelWidth = '100px' // dialog中组件的宽度
const EDIT_DIALOG = ref(false) // 修改dialog窗口开关
const ADD_DIALOG = ref(false) // 添加dialog窗口开关
let EditNowImage = ref("");
let pageSize = ref(1); // 给初始值
let totals = ref(1); // 给初始值
let nowPage = ref(1);
let cacheData = "";
let SHOW_PAGINATION = ref(true);
let goodsTypeList: any = reactive([])

CURRENCY_SELECT("goodsType", 1, 100)?.then(res => {
    goodsTypeList.length = 0
    goodsTypeList.push(...res.data.responeData.data)
})

let goodsEditForm = reactive({
    goodsId: 0,
    goodsName: "",
    goodsImage: "",
    goodsPrice: 0.0,
    goodsDiscount: 0.0,
    goodsTypeId: 0,
    goodsType: {},
    creatTime: "",
})
let goodsAddForm = reactive({
    goodsId: 0,
    goodsName: "",
    goodsImage: "",
    goodsPrice: "",
    goodsDiscount: 0.0,
    goodsTypeId: "",
    goodsType: {},
})

function RELOAD() {
    CURRENCY_SELECT(URL, nowPage.value, 30)?.then(res => { // 赋值unit
        goodsList.length = 0
        console.log(res.data)
        goodsList.push(...res.data.responeData.data);
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
    await CURRENCY_REQUEST(url, { removeId: data.goodsId }, CURRENCY_OPERATION_API(operationId, data.goodsName))
    await axios.post("/uploadRemove/food/" + data.goodsImage)
    goodsEditForm.goodsImage = ''
    goodsAddForm.goodsImage = ''
    await RELOAD()
}
async function ADD(url: String, data: any, operationId: Number) {
    ADD_DIALOG.value = !ADD_DIALOG.value;
    goodsAddForm.goodsId = NaN;
    if (!ADD_DIALOG.value && data == 'null' && FORM_STATS_JUDGE(goodsAddForm)) {
        await CURRENCY_REQUEST(url, goodsAddForm, CURRENCY_OPERATION_API(operationId, "->" + goodsAddForm.goodsName))
        goodsAddForm = CLEAR_FORM(goodsAddForm)
        AddUpload.value!.clearFiles();
    }
    RELOAD()
}
async function EDIT(url: String, data: any, operationId: Number) {
    if (!EDIT_DIALOG.value && data != 'null') {
        goodsEditForm.goodsId = data.goodsId
        goodsEditForm.goodsName = data.goodsName
        // goodsEditForm.goodsImage = data.goodsImage
        goodsEditForm.goodsPrice = data.goodsPrice
        goodsEditForm.goodsDiscount = data.goodsDiscount
        goodsEditForm.goodsTypeId = data.goodsTypeId
        goodsEditForm.goodsType = data.goodsType
        EditNowImage.value = data.goodsImage
        cacheData = data.goodsName
    }
    EDIT_DIALOG.value = !EDIT_DIALOG.value;
    if (!EDIT_DIALOG.value && data == 'null') {
        if(goodsEditForm.goodsImage == ""){
            let cacheForm:any = reactive({})
            cacheForm.goodsId = goodsEditForm.goodsId
            cacheForm.goodsName = goodsEditForm.goodsName
            cacheForm.goodsPrice = goodsEditForm.goodsPrice
            cacheForm.goodsDiscount = goodsEditForm.goodsDiscount
            cacheForm.goodsTypeId = goodsEditForm.goodsTypeId
            cacheForm.goodsType = goodsEditForm.goodsType
            await CURRENCY_REQUEST(url, cacheForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + goodsEditForm.goodsName))
        }else{
            await CURRENCY_REQUEST(url, goodsEditForm, CURRENCY_OPERATION_API(operationId, "前->" + cacheData + ",后->" + goodsEditForm.goodsName))
            await axios.post("/uploadRemove/food/" + EditNowImage.value)
        }
        EditUpload.value!.clearFiles();
        RELOAD()
        goodsEditForm = CLEAR_FORM(goodsEditForm)
        EditNowImage.value = ""
    }
}

function cancelDialog(addOrEdit: String) {
    if (addOrEdit == "ADD") {
        AddUpload.value!.clearFiles();
        ADD_DIALOG.value = false
        axios.post("/uploadRemove/food/" + goodsAddForm.goodsImage)
        goodsAddForm = CLEAR_FORM(goodsAddForm)
    } else {
        EditUpload.value!.clearFiles();
        EDIT_DIALOG.value = false
        axios.post("/uploadRemove/food/" + goodsEditForm.goodsImage)
        goodsEditForm = CLEAR_FORM(goodsEditForm)
        EditNowImage.value = ""
    }
}

function handleSizeChange(val: number) {
    setTimeout(() => {
        CURRENCY_SELECT(URL, val, 30).then(res => {
            goodsList.length = 0
            nowPage.value = val
            pageSize.value = res.size
            totals.value = res.total
            goodsList.push(...res.data)
        })
    }, 100);
};

function ItemLinkSuccessAdd(response: any, file: any, fileList: any) { //增加的上传图片方法
    if (fileList.length > 1) {
        axios.post("/uploadRemove/food/" + fileList[0].response)
        fileList.splice(0, 1);
    }
    goodsEditForm.goodsImage = response
    goodsAddForm.goodsImage = response
}
function handlePictureCardPreview(file: any) {
    dialogImageUrl = file.url;
    dialogindialogVisible.value = true;
}
function handleRemove() {
    axios.post("/uploadRemove/food/" + goodsAddForm.goodsImage)
    goodsEditForm.goodsImage = ''
    goodsAddForm.goodsImage = ''
}

function closeDialog(){
    ElMessageBox.confirm('确认关闭？').then(async (_) => {  
        console.log(AddUpload.value)
        axios.post("/uploadRemove/food/" + goodsAddForm.goodsImage)
        axios.post("/uploadRemove/food/" + goodsEditForm.goodsImage)
        EDIT_DIALOG.value = false
        ADD_DIALOG.value = false
        AddUpload.value!.clearFiles();
        EditUpload.value!.clearFiles();
      }).catch(_ => {});
}

RELOAD();
</script>

<style scoped>
.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>