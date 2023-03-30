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
            <el-table-column fixed="right" label="操作">
                <template #default="scope">
                    <el-button type="primary" :icon="ShoppingCart" circle @click="ADD_SHOPCAR(scope.row)" />
                </template>
            </el-table-column>
        </el-table>
        <!-- <el-pagination v-if="SHOW_PAGINATION" background :page-size="pageSize" layout="prev, pager, next"
            :total="totals" :current-page="nowPage" hide-on-single-page @current-change="handleSizeChange" /> -->
    </el-scrollbar>

    <el-dialog v-model="addShopCarDialog" title="选择菜品数量" style="width:500px" :before-close="stateShop">
        <el-form>
            <el-form-item label="选择数量" :label-width="formLabelWidth">
                <el-input-number v-model="select_num" :step="2" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="stateShop(0)">取消</el-button>
                <el-button type="primary" @click="stateShop(1)">
                    添加
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ShoppingCart } from '@element-plus/icons-vue'
import {
    CURRENCY_SELECT,
    GET_NOW_DATE_FORMATE
} from "@/apis/normalCrudApi"
import { ElMessage } from 'element-plus';
import { ADD_ITEM_TO_SHOPINGCAR, SELECT_SHOPINGCAR } from '@/apis/shoppingCartApis';
import { tr } from 'element-plus/es/locale';

let addShopCarDialog = ref(false)
let select_num = ref(1)

let goodsList: any = reactive([])
const URL = "goods" // 本组件内通用的url
const formLabelWidth = '100px' // dialog中组件的宽度
let pageSize = ref(1); // 给初始值
let totals = ref(1); // 给初始值
let nowPage = ref(1);
let goodsTypeList: any = reactive([])
let dataCache: any = reactive({})


CURRENCY_SELECT("goodsType", 1, 100)?.then(res => {
    goodsTypeList.length = 0
    goodsTypeList.push(...res.data.responeData.data)
})


function RELOAD() {
    CURRENCY_SELECT(URL, nowPage.value, 30)?.then(res => { // 赋值unit
        goodsList.length = 0
        console.log(res.data)
        goodsList.push(...res.data.responeData.data);
    })
}

function ADD_SHOPCAR(data: any) {
    dataCache = reactive({})
    dataCache.count = 50
    dataCache.drugs = data
    dataCache.createTime = GET_NOW_DATE_FORMATE()
    addShopCarDialog.value = true
}

function stateShop(stateCode: number) {
    if (stateCode == 1) {
        dataCache.count = select_num.value;

        SELECT_SHOPINGCAR().then((res: any)=>{
            if(res.data.responeData.length === 0){
                // 如果为空直接添加
                ADD_ITEM_TO_SHOPINGCAR(dataCache)?.then((res: any) => {
                    if (res) addShopCarDialog.value = false
                })
                console.log("没数据")
            }else{
                // 有数据则基于原版去改
                console.log("有数据")
            }
            
        })
    } else {
        addShopCarDialog.value = false
        ElMessage({ type: 'warning', message: '取消操作', })
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