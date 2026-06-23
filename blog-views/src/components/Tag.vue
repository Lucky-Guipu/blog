<template>
  <div>
    <div class="ui top attached segment category-card">
      <h2 class="category-title">标签：{{ tagName }}</h2>
    </div>
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :totalPage="totalPage"/>
  </div>
</template>

<script>
  import BlogList from "@/components/blog/BlogList.vue";
  import { getTagBlogList } from "@/api/home";
  import { ElMessage } from "element-plus";

  export default {
    name: "blogTag",
    components: { BlogList },
    data() {
      return {
        blogList: [],
        totalPage: 0,
        tagName: ''
      }
    },
    watch: {
      '$route.params.name'(newName) {
        this.tagName = newName
        this.getBlogList()
      }
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        vm.tagName = to.params.name
        vm.getBlogList()
      })
    },
    methods: {
      getBlogList(pageNum) {
        getTagBlogList(this.tagName, pageNum).then(res => {
          if (res.code === 200) {
            this.blogList = res.data.list
            this.totalPage = res.data.totalPage
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

<style>
.category-title {
  text-align: center;
  font-size: 22px;
  font-weight: 600;
  margin-bottom: 20px;
  color: #333;
}
</style>
