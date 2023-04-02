<template>
    <el-scrollbar>
        <el-table :data="historyOrderList" max-height="700">
            <el-table-column prop="orderId" label="订单Id" width="80" />
            <!-- <el-table-column prop="user.userNickname" label="购买者" width="100" /> -->
            <el-table-column prop="orderPrice" label="订单价格" width="120" />
            <el-table-column prop="createTime" label="日期" width="250" />
            <el-table-column fixed="right" label="操作">
                <template #header>
                    操作
                </template>
                <template #default="scope">
                    <el-button type="primary" :icon="Promotion" round @click="tableDrugsDetail(scope.row)">
                        详情
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-scrollbar>

    <el-drawer v-model="drawer" title="订单详细" direction="rtl" size="50%">
        <el-table :data="inObject">
            <el-table-column property="goodsName" label="菜品名称" width="200" />
            <el-table-column property="goodsPrice" label="菜品价格" width="200" />
            <el-table-column property="goodsDiscount" label="菜品折扣" width="200" />
            <el-table-column property="count" label="数量" />
            <el-table-column label="总价" width="120">
                <template #default="scope">
                    {{(scope.row.count).toFixed(2)}} 元
                </template>
            </el-table-column>
        </el-table>
        总共: {{total.toFixed(2)}}元
    </el-drawer>

</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { Promotion } from '@element-plus/icons-vue'
import axios from '@/apis/axiosApis';

let historyOrderList: any = reactive([])
let drawer = ref(false)
let inObject: any = reactive([])
let total: any = ref(0)

function RELOAD() {
    setTimeout((_: any) => {
        axios.post("orderDetail/select/"+localStorage.getItem("userId")).then(res=>{
            historyOrderList.length = 0
            historyOrderList.push(...res.data.responeData)
        })
    }, 200)
}
const tableDrugsDetail = (row: any) => {
    inObject = JSON.parse(row.orderDataJson)
    total.value = 0
    inObject.forEach((obj:any) => {
        total.value += obj.total
    });
    drawer.value = true;
}
RELOAD()

</script>