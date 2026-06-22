<script setup>
import { useRoute } from 'vue-router'
import NavMenu from './components/common/NavMenu.vue'
import Introduction from './components/sidebar/Introduction.vue'
import Music from './components/sidebar/Music.vue';
const route = useRoute()
</script>

<template>
  <nav-menu class="nav-menu"></nav-menu>

  <router-view v-if="route.path === '/login'" />

  <div class="main" v-if="route.path !== '/login'">
    <div class="m-padded-tb-big">
      <div class="ui container">
        <div class="ui stackable grid">

          <!-- 左边 -->
          <div class="three wide column m-mobile-hide">
            <Introduction :class="{ 'm-display-none': focusMode }" />
          </div>

          <!-- 中间 -->
          <div class="ten wide column">
            <!-- <keep-alive include="Home"> -->
            <router-view />
            <!-- </keep-alive> -->
          </div>

          <!-- 右边 -->
          <div class="three wide column m-mobile-hide">
            <Music :class="{ 'm-display-none': focusMode }" />
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped>
.site {
  display: flex;
  min-height: 100vh;
  /* 没有元素时，也把页面撑开至100% */
  flex-direction: column;
}

.main {
  margin-top: 40px;
  flex: 1;
}

.main .ui.container {
  width: 1400px !important;
  margin-left: auto !important;
  margin-right: auto !important;
}

.ui.grid .three.column {
  padding: 0;
}

.ui.grid .ten.column {
  padding-top: 0;
}

.m-display-none {
  display: none !important;
}
</style>
