<template>
    <el-table :data="shopingCarList" stripe style="width: 100%">
        <el-table-column align="left" label="菜品图片">
            <template #default="scope">
                <img :src="'/api/static/food/' + scope.row.goodsImage" width="100" />
            </template>
        </el-table-column>
        <el-table-column prop="goodsName" label="菜名" width="100" />
        <el-table-column prop="count" label="数量" width="100" />
        <el-table-column prop="goodsPrice" label="单价" width="100" />
        <el-table-column prop="goodsDiscount" label="折扣" width="100">
            <template #default="scope">
                {{ parseFloat(scope.row.goodsDiscount) == 1 ? "不打折":parseFloat(scope.row.goodsDiscount)*100+"%折" }}
            </template>
        </el-table-column>
        <el-table-column prop="total" label="价格" />
        <el-table-column label="操作">
            <template #default="scope">
                <el-button size="small" type="primary"
                    @click="CHANGE_SHOPING_CAR_ITEM(scope.$index, scope.row)">修改数量</el-button>
                <el-button size="small" type="danger"
                    @click="DELETE_SHOPING_CAR_ITEM(scope.$index, scope.row)">不要了</el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-space wrap>
        <el-card class="box-card" style="width: 15em;margin: 1em 1em;">
            <template #header>
                <span style="margin-left: 1em" v-if="shopingCarList.length != 0"> 总计：{{ total.toFixed(2) }} 元</span>
                <span v-else> 购物车空空如也，<br>快去添加一些菜品吧！</span>
            </template>
            <el-popconfirm v-if="shopingCarList.length != 0" confirm-button-text="是的" cancel-button-text="取消"
                           :icon="InfoFilled" icon-color="#626AEF" title="你确定需要清空购物车吗?"
                           @confirm="clearShopingCar">
                <template #reference>
                    <el-button type="primary" round>清空购物车</el-button>
                </template>
            </el-popconfirm>
            <el-button v-if="shopingCarList.length != 0" round type="primary" @click="deskDialog = true">结账！
            </el-button>
        </el-card>
    </el-space>


    <el-dialog v-model="changeShopCarDialog" style="width:500px" title="选择菜品数量">
        <el-form>
            <el-form-item label="选择数量" :label-width="formLabelWidth">
                <el-input-number v-model="select_num" :step="2"/>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="EDIT_DIALOG_STATE(0)">取消</el-button>
                <el-button type="primary" @click="EDIT_DIALOG_STATE(1)">
                    修改
                </el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="deskDialog" style="width:500px" title="选择想要就餐的位置">
        <el-form-item>
            <reserveForShoppingCart/>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="输入您想要预约的位置">
            <el-input v-model="deskId"/>
        </el-form-item>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="deskDialog = false">我再想想</el-button>
        <el-button type="primary" @click="PayBeforeReserve">
          就坐这里了！
        </el-button>
      </span>
        </template>
    </el-dialog>


</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue';
import {InfoFilled} from '@element-plus/icons-vue'
import {CHANGE_SHOPING_CAR_ITEM_API, SELECT_SHOPINGCAR} from '@/apis/shoppingCartApis';
import {ElMessage, ElMessageBox} from 'element-plus';
import axios from "@/apis/axiosApis";
import {order, shoppingCart} from '@/apis/dataJson';

let shopingCarList: any = reactive([]);
let changeShopCarDialog = ref(false);
let deskDialog = ref(false)
let select_num = ref(1)
const formLabelWidth = '100px'
let cacheShopingCar: any = reactive({})
let total = ref(0)
let thisShopingCarID: undefined = undefined
let allDesk: any = reactive([])
let deskId: any = ref("");

axios.post("desk/select").then(res => {
    allDesk.push(...res.data.responeData)
})

function reload() {
    SELECT_SHOPINGCAR().then(async (res: any) => {
        shopingCarList.length = 0
        total.value = 0
        await (shopingCarList.push(...JSON.parse(res.data.responeData[0].shoppingCartDataJson)))
        shopingCarList.forEach((shopingCar: any) => {
            total.value += shopingCar.total
        });
        thisShopingCarID = res.data.responeData[0].shoppingCartId
    })
}


async function DELETE_SHOPING_CAR_ITEM(index: any, row: any) {
    const shopingCarCacheList: any = [...shopingCarList.filter((goods: any) => {
        return goods.goodsId != row.goodsId
    })]
    let editObject = new shoppingCart()
    await (editObject.userId = parseInt(localStorage.getItem("userId") + ""))
    await (editObject.shoppingCartDataJson = JSON.stringify(shopingCarCacheList))
    await (editObject.shoppingCartId = thisShopingCarID)
    CHANGE_SHOPING_CAR_ITEM_API(editObject).then((res: any) => {
        setTimeout(reload, 200);
    })
}

async function EDIT_DIALOG_STATE(options: number) {
    if(select_num.value <= 0){
        ElMessage({ type: 'error', message: '数量不允许小于0！' })
        return;
    }
    if (options == 1) {
        let cacheShopingCarList:any = []
        shopingCarList.forEach((Goods: any) => {
            if (Goods.goodsId == cacheShopingCar.goodsId) {
                Goods.count = select_num.value
                Goods.total = (Goods.goodsPrice * Goods.goodsDiscount) * Goods.count
                cacheShopingCarList.push(Goods)
            }else{
                cacheShopingCarList.push(Goods)
            }
        });
        console.log(cacheShopingCarList)
        let addObject = new shoppingCart()
        await (addObject.userId = parseInt(localStorage.getItem("userId") + ""))
        await (addObject.shoppingCartDataJson = JSON.stringify(cacheShopingCarList))
        await (addObject.shoppingCartId = thisShopingCarID)
        CHANGE_SHOPING_CAR_ITEM_API(addObject)?.then((res: any) => {
            console.log(res)
            changeShopCarDialog.value = false
        })
        setTimeout(() => {
            reload()
        }, 200)
    } else {
        changeShopCarDialog.value = false
        ElMessage({ type: 'warning', message: '取消操作', })
    }
}
function CHANGE_SHOPING_CAR_ITEM(index: any, row: any) {
    changeShopCarDialog.value = true
    cacheShopingCar = reactive({})
    cacheShopingCar = row
    select_num.value = row.count
    changeShopCarDialog.value = true
}

const clearShopingCar = () => {
    let userId = localStorage.getItem("userId")
    axios.post("/shoppingCart/remove/" + userId).then(res => {
        ElMessage({ type: 'success', message: '操作完成' })
        setTimeout(reload, 200);
    })
}

async function PAY() {
    let cacheShopingCarList: any = [...shopingCarList]
    let orderPrice = 0;
    shopingCarList.forEach((Goods: any) => {
        orderPrice += Goods.total
    });
    let insertOrderData = new order()
    await (insertOrderData.orderDataJson = JSON.stringify(cacheShopingCarList))
    await (insertOrderData.orderPrice = parseFloat(orderPrice.toFixed(2)))
    await (insertOrderData.userId = parseInt(localStorage.getItem("userId") + ""))
    await (insertOrderData.deskId = deskId.value)
    axios.post("/orderDetail/insert", insertOrderData).then(res => {
        if (res?.data?.status != 500) {
            ElMessage({type: 'success', message: '支付完成'})
            clearShopingCar()
            setTimeout(reload, 500);
        }
        console.log(res)
    })
}

let message = ""
const PayBeforeReserve = (done: () => void) => {
    ElMessageBox.confirm('确认选择这个位置吗？')
        .then(() => {
            if (deskId.value == "") {
                ElMessage({type: 'error', message: "你没有输入需要坐的位置"})
                throw "你没有输入需要坐的位置"
            }
            axios.post("/desk/select/" + deskId.value).then(res => {
                console.log(res)
                switch (res.data.responeData[0].isUse) {
                    case 0:
                        axios.post("desk/update", {
                            deskId: deskId.value,
                            isUse: 1
                        }).then(res => {
                            ElMessage({type: 'success', message: '选座成功！'})
                            reload()
                            PAY();
                        })
                        break
                    case 1:
                        ElMessage({type: 'error', message: "你输入的位置被使用了"})
                        break
                    case 2:
                        ElMessage({type: 'error', message: "你输入的位置被预约了"})
                        break
                    default:
                        ElMessage({type: 'error', message: "未知错误"})
                        break
                }
            })
            deskDialog.value = false
        })
}
reload()
</script>

<style>
</style>