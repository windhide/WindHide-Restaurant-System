<template>
    <div id='showorders' style='width:100%; height:100%;'></div>
</template>

<script lang="ts" setup>
import * as echarts from 'echarts';
import {onMounted} from 'vue';
import axios from "@/apis/axiosApis";

let incomeState: any = []
let echartsData: any = []
let echartsLegen: any = []
const datainit= ()=>{
    axios.post("incomeState/select").then(async (res) => {
    incomeState.push(...res.data.responeData)
    await incomeState.forEach((incomeState: any) => {
        let have = false;
        for (let i = 0; i < echartsData.length; i++) {
            if (echartsData[i].name == incomeState.incomeStateYear) {
                have = true;
                break;
            }
        }
        if (!have) {
            echartsData.push({
                name: incomeState.incomeStateYear,
                type: 'line',
                stack: 'Total',
                areaStyle: {},
                emphasis: {
                    focus: 'series'
                },
                data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            })
        }
    });
    await incomeState.forEach((incomeState: any) => {
        echartsData.forEach((echartsData: any) => {
            if (incomeState.incomeStateYear == echartsData.name) {
                echartsData.data[incomeState.incomeStateMonth - 1] = incomeState.incomeStateMoney
            }
        });
    });
    //  基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('showorders')!);
    // 指定图表的配置项和数据
    let option = {
        title: {
            text: '收入报表'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data: [...echartsLegen]
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月',"10月","11月","12月"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [...echartsData]
    };
    option && myChart.setOption(option);
})
}
//*** 挂载Echarts
onMounted(async () => {
    await datainit()
})
</script>