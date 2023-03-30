import axios from "@/apis/axiosApis"
import router from "@/router";
import { ElMessage } from "element-plus";
import { GET_NOW_DATE_FORMATE } from "@/apis/normalCrudApi";

export function ADD_ITEM_TO_SHOPINGCAR(data:any){
    let userId = localStorage.getItem("userId")
    return axios.post("shoppingCart/insert",{"shoppingCart": data,"userId": userId}).then(res =>{
        if(res.data){
            ElMessage({ type: 'success', message: '成功添加至购物车！', })
            return true
        }else{
            ElMessage({ type: 'error', message: '因未知原因，添加到购物车失败。', })
            return false
        }
    })
}

export function SELECT_SHOPINGCAR(){
    let userId = localStorage.getItem("userId")
    return axios.post("shoppingCart/select/"+userId).then(res =>{
        if (res.data.code == 2002) {
            setTimeout(() => {
                ElMessage({ type: 'error', message: '没有登录！操作取消,请先登陆！', })
                if (router.currentRoute.value.path.indexOf("employee") != -1) {
                    router.push("/employeeLogin")
                } else {
                    router.push("/abonementLogin")
                }
            }, 300);
            return;
        }
        return res;
    })
}

export function CHANGE_SHOPING_CAR_ITEM_API(originalData: any){
    let userId = localStorage.getItem("userId")
    return axios.post("shoppingCart/update",{"userId":userId,"shoppingCartDataJson":originalData}).then(res =>{
        if(res.data){
            ElMessage({ type: 'success', message: '已经完成变更', })
            return true
        }else{
            ElMessage({ type: 'error', message: '因未知原因，失败。', })
            return false
        }
    })
}

export function REMOVE_SHOPING_CAR_ITEM_API(drugsId:number){
    let userId = localStorage.getItem("userId")
    return axios.post("shoppingCart/remove/"+userId).then(res =>{
        if(res.data){
            ElMessage({ type: 'success', message: '已经移除购物车', })
            return true
        }else{
            ElMessage({ type: 'error', message: '因未知原因，失败。', })
            return false
        }
    })
}
export function PAY_CAR(data:any){
    let userId = localStorage.getItem("userId")
    let userName = localStorage.getItem("userName")
    axios.post("shoppingCart/Pay",{"userKey":userId+"-"+userName+"-car","shoppingCart":data,"nowTime":GET_NOW_DATE_FORMATE()}).then(res =>{
        if(res.data){
            ElMessage({ type: 'success', message: '支付完成', })
        }else{
            ElMessage({ type: 'error', message: '因未知原因，失败。', })
        }
    })
}