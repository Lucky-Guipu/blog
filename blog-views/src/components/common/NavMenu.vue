<template>
  <div>
    <el-menu :default-active="currentPath" router mode="horizontal" background-color="white" text-color="#222"
      active-text-color="red" style="min-width: 1300px">
      <el-menu-item v-for="(item, i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>

      <!--  title -->
      <!-- <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">
        White Jotter - Your Mind Palace
      </span> -->

      <el-input placeholder="快速搜索..." prefix-icon="Search" size="default"
        style="width: 300px;position:absolute;margin-top: 12px;right: 18%" v-model="keywords">
      </el-input>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'NavMenu',
  data() {
    return {
      navList: [
        { name: '/home', navItem: '首页' },
        { name: '/moments', navItem: '动态' },
        { name: '/category', navItem: '分类' },
        { name: '/about', navItem: '关于' },
        // { name: '/login', navItem: '登录' }
      ],
      keywords: ''
    }
  },
  computed: {
    hoverBackground() {
      return '#ffd04b'
    },
    currentPath() {
      var x = this.$route.path.indexOf('/', 1)
      if (x !== -1) {
        return this.$route.path.substring(0, x)
      } else {
        return this.$route.path
      }
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}

span {
  pointer-events: none;
}
</style>
