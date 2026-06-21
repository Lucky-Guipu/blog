<template>
  <div class="container">
    <div class="block text-center">
      <el-carousel trigger="click" height="265px" motion-blur>
        <el-carousel-item v-for="(img, index) in carouselImages" :key="item">
          <img :src="img" alt="轮播图" style="width: 100%; height: 100%; object-fit: cover;" />
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script setup>
const carouselImages = [
  '/banner/IMG_20251231_211548.jpg',
  '/banner/IMG_20260120_090002.jpg',
  '/banner/IMG_20260211_154251.jpg',
  '/banner/IMG_20240614_071055.jpg',
];
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
}
</style>
