<template>
  <el-menu :default-openeds="['0', '1', '2']" :collapse="isCollapse"
    class="el-menu-vertical-demo">
    <el-menu-item @click="isCollapse = !isCollapse">
      <el-icon>
        <Expand v-if="isCollapse" />
        <Fold v-else />
      </el-icon>
      <template #title>
        {{ isCollapse?"展开菜单": "收起菜单" }}
      </template>
    </el-menu-item>
    <el-sub-menu :index="String(index)" v-for="(navigation,index) in NAVIGATION.children" >
      <template #title>
        <el-icon>
          <component :is="navigation.icon" />
        </el-icon>
        <span>{{ navigation.childrenName }}</span>
      </template>
      <router-link v-for="(children, childrenIndex) in navigation.children"
        :to="NAVIGATION.path + navigation.childrenPath + children.childrenPath">
        <el-menu-item :index="index + '-' + childrenIndex">
          <el-icon>
            <component :is="children.icon" />
          </el-icon>
          {{ children.childrenName }}
        </el-menu-item>
      </router-link>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import { getNavigation } from '@/apis/navigationApi';
import { Switch } from '@element-plus/icons-vue';
import { ref } from 'vue';
const isCollapse = ref(true);

type Props = {
  navigationFatherName: string,
}
const prop = defineProps<Props>()
console.log(prop.navigationFatherName)
const NAVIGATION = getNavigation(prop.navigationFatherName)
// 传参为一个/的时候，修复bug
if (prop.navigationFatherName == "") {
  NAVIGATION.path = ""
}
</script>
