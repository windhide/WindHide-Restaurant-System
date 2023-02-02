<template>
    <el-aside width="12%">
    <el-scrollbar>
      <el-menu :default-openeds="['0', '1', '2', '3']" v-for="(navigation,index) in NAVIGATION.children">
        <el-sub-menu :index="String(index)">
          <template #title>
            <el-icon>
              <component :is="navigation.icon" />
            </el-icon>{{ navigation.childrenName }}
          </template>
          <div v-for="(children, childrenIndex) in navigation.children">
              <router-link  :to="NAVIGATION.path + navigation.childrenPath + children.childrenPath">
              <el-menu-item :index="index + '-' + childrenIndex">
                <el-icon>
                  <component :is="children.icon" />
                </el-icon>
                {{ children.childrenName }}
              </el-menu-item>
            </router-link>
          </div>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<script lang="ts" setup>
    import { getNavigation } from '@/apis/navigationApi';
    type Props = {
        navigationFatherName: string,
    }
    const prop = defineProps<Props>()    
    console.log(prop.navigationFatherName)
    const NAVIGATION = getNavigation(prop.navigationFatherName)
    // 传参为一个/的时候，修复bug
    if(prop.navigationFatherName == ""){
      NAVIGATION.path = ""
    }

</script>
