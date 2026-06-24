<template>
  <div class="container">
    <!-- <div class="block text-center">
      <el-carousel trigger="click" height="265px" motion-blur>
        <el-carousel-item v-for="(img, index) in carouselImages" :key="index">
          <img :src="img" alt="轮播图" style="width: 100%; height: 100%; object-fit: cover;" />
        </el-carousel-item>
      </el-carousel>
    </div> -->
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :totalPage="totalPage"/>
  </div>
</template>

<script>
  import BlogList from "@/components/blog/BlogList.vue";
  import {getBlogList} from "@/api/home";
  import {useBlogStore} from "@/stores/blog";
  import {ElMessage} from "element-plus";

  export default {
    name: "blogHome",
    components: {BlogList},
    data() {
      return {
        blogList: [],
        totalPage: 0,
        getBlogListFinish: false,
        // carouselImages: [
        //   '/banner/IMG_20251231_211548.jpg',
        //   '/banner/IMG_20260120_090002.jpg',
        //   '/banner/IMG_20260211_154251.jpg',
        //   '/banner/IMG_20240614_071055.jpg',
        // ]
      }
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        const blogStore = useBlogStore()
        if (from.name !== 'blog') {
          //其它页面跳转到首页时，重新请求数据
          //设置一个flag，让首页的分页组件指向正确的页码
          blogStore.isBlogToHome = false
          vm.getBlogList()
        } else {
          //如果文章页面是起始访问页，首页将是第一次进入，即缓存不存在，要请求数据
          if (!vm.getBlogListFinish) {
            vm.getBlogList()
          }
          //从文章页面跳转到首页时，使用首页缓存
          blogStore.isBlogToHome = true
        }
      })
    },
    methods: {
      getBlogList(pageNum) {
        getBlogList(pageNum).then(res => {
          if (res.code === 200) {
            this.blogList = res.data.list
            this.totalPage = res.data.totalPage
            this.getBlogListFinish = true
          } else {
            ElMessage.error(res.msg)
          }
        }).catch(() => {
          ElMessage.error("请求失败")
        })
      }
    }
  }
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
}
</style>
