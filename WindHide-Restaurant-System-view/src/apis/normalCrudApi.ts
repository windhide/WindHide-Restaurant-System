import {ElMessage, ElMessageBox} from "element-plus"
import axios from "@/apis/axiosApis"

class Operation {
    opreationUrl = ""
    operationName = ""
    dataShowName = ""
}

export function CURRENCY_SELECT(url: String, pageNum: Number, pageSize: Number) {
    return axios.post(url + "/select",{pageNum: pageNum, pageSize: pageSize}).then((res: any) => {
        if (res.data.state != "SUCCESS") {
            setTimeout(() => {
                ElMessage({ type: 'error', message: '数据请求出错！', })
            }, 300);
            return;
        }
        return res;
    })
}

/**
 * 
 * @param url 
 * @param operation 
 * @param data 
 * @param 注意：1为新增、2为修改、3为删除。其他的传入值直接报错！ 
 * @returns 
 */
export async function CURRENCY_REQUEST(url: String, data: any, operation: Operation) {
    if (operation.opreationUrl == "" || operation.operationName == "") {
        ElMessage({ type: 'error', message: '信息操作失败，请检查传入参数!' })
        return
    }
    return await ElMessageBox.confirm(
        '确认' + operation.operationName + '>>' + operation.dataShowName + '吗?',
        operation.operationName + '确认',
        {
            confirmButtonText: operation.operationName,
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(async () => {
        if(operation.opreationUrl == "remove"){
            await axios.post(url + "/" + operation.opreationUrl+"/"+data.removeId).then(async (res: any) => {
                if (res.data.responeData) {
                    await ElMessage({ type: 'success', message: '数据>>' + operation.dataShowName + '<<' + operation.operationName + '成功!' })
                    return true
                } else {
                    await ElMessage({ type: 'error', message: '因为服务器原因，数据>>' + operation.dataShowName + '<<' + operation.operationName + '失败!' })
                    return false
                }
            })
        }else{
            await axios.post(url + "/" + operation.opreationUrl, data).then(async (res: any) => {
                if (res.data.responeData) {
                    await ElMessage({ type: 'success', message: '数据>>' + operation.dataShowName + '<<' + operation.operationName + '成功!' })
                    return true
                } else {
                    await ElMessage({ type: 'error', message: '因为服务器原因，数据>>' + operation.dataShowName + '<<' + operation.operationName + '失败!' })
                    return false
                }
            })
        }
    }).catch(() => {
        ElMessage({ type: 'info', message: '取消操作', })
        return false
    })
}

export function CURRENCY_OPERATION_API(operation: Number, dataShowName: string) {
    let cacheOperation = new Operation()
    switch (operation) {
        case 1:
            cacheOperation.opreationUrl = "insert"
            cacheOperation.operationName = "新增"
            cacheOperation.dataShowName = dataShowName
            break;
        case 2:
            cacheOperation.opreationUrl = "update"
            cacheOperation.operationName = "修改"
            cacheOperation.dataShowName = dataShowName
            break;
        case 3:
            cacheOperation.opreationUrl = "remove"
            cacheOperation.operationName = "删除"
            cacheOperation.dataShowName = dataShowName
            break;
        default:
            cacheOperation.opreationUrl = ""
            cacheOperation.operationName = ""
            cacheOperation.dataShowName = ""
            break;
    }
    return cacheOperation
}

export function FORM_STATS_JUDGE(data: any) {
    for (let key in data) {
        if(key == "createTime") continue;
        if (data[key] == "" || data[key] == undefined || data[key] == null) {
            ElMessage({ type: 'error', message: '表单的数据不能为空！' })
            console.log(key)
            return false;
        }
    }
    return true
}

export function CLEAR_FORM(data: any) {
    for (let key in data) {
        switch (typeof (data[key])) {
            case "number":
                data[key] = 0
            case "string":
                data[key] = ""
        }
    }
    return data
}

export function TOSTRING(data: any) {
    let cacheString = "{"
    for (let key in data) {
        cacheString += key + " : " + data[key] + ",\t"
    }
    cacheString += "}"
    return cacheString
}

export function GET_NOW_DATE_FORMATE() {
    let date = new Date()
    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate();
    return year + "-" + month + "-" + day
}
