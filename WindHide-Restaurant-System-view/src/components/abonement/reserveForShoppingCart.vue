<template>
      <div style="width: 100%;">
         空位:<el-icon :size="30" color="#606266" ><KnifeFork /></el-icon> &nbsp;
         使用中:<el-icon :size="30" color="#5a9cf8" ><KnifeFork /></el-icon> &nbsp;
         被预约:<el-icon :size="30" color="#61c654" ><KnifeFork /></el-icon> &nbsp;
       </div>
       <el-card class="box-card" style="margin-top: 20px;float: left;height: 18em;">
            <template #header>
                <div class="card-header">
                    <span>
                        <el-radio-group v-model="radio">
                            <el-radio-button label="小桌" />
                            <el-radio-button label="中桌" />
                            <el-radio-button label="大桌" />
                        </el-radio-group>
                    </span>
                </div>
            </template>
            <div v-for="common in commonDesk" style="height: 5em;width: 3em; float: left; margin-left: 1em;">
                <div v-if="common.isUse == 0">
                    <el-icon :size="50" color="#606266" ><KnifeFork /></el-icon> &nbsp;
                    {{common.deskId}}号
                </div>
                <div v-if="common.isUse == 1">
                    <el-icon :size="50" color="#5a9cf8" ><KnifeFork /></el-icon> &nbsp;
                    {{common.deskId}}号
                </div>
                <div v-if="common.isUse == 2">
                    <el-icon :size="50" color="#61c654" ><KnifeFork /></el-icon> &nbsp;
                    {{common.deskId}}号
                </div>
             </div>
       </el-card>
</template>

<script lang="ts" setup>
import axios from "@/apis/axiosApis";
import { reactive, ref, watch } from "vue";
let smallDesk: any = reactive([])
let mediumDesk: any = reactive([])
let bigDesk: any = reactive([])
let commonDesk: any = reactive([])
let allDesk: any = reactive([])
const radio = ref('小桌')

const reload = () => {
        smallDesk.length = 0
        mediumDesk.length = 0
        bigDesk.length = 0
        commonDesk.length = 0
        allDesk.length = 0
        axios.post("desk/select").then(res => {
                allDesk.push(...res.data.responeData)
                allDesk.forEach((allDesk: any) => {
                        console.log(allDesk.deskType)
                        switch (allDesk.deskType) {
                                case 1:
                                        smallDesk.push(allDesk)
                                        break
                                case 2:
                                        mediumDesk.push(allDesk)
                                        break
                                case 3:
                                        bigDesk.push(allDesk)
                                        break
                        }
                });
                commonDesk.push(...smallDesk)
        })
}

watch(radio,_=>{ 
    commonDesk.length = 0
    switch(radio.value){
        case "小桌":
            commonDesk.push(...smallDesk)
            break
        case "中桌":
            commonDesk.push(...mediumDesk)
            break
        case "大桌":
            commonDesk.push(...bigDesk)
            break
    }

})

reload()
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 500px;
}
</style>