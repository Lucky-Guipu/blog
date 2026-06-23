<template>
  <div>
    <div v-if="blog" class="ui padded attached segment m-padded-tb-large m-box">
      <div class="ui grid m-margin-lr">
        <!--标题-->
        <div class="row m-padded-tb-small">
          <h2 class="ui header m-center">{{ blog.title }}</h2>
        </div>
        <!--文章简要信息-->
        <div class="row m-padded-tb-small">
          <div class="ui horizontal link list m-center">
            <div class="item m-datetime">
              <i class="small calendar icon"></i><span>{{ dateFormat(blog.createTime,'YYYY-MM-DD') }}</span>
            </div>
            <div class="item m-views">
              <i class="small eye icon"></i><span>{{ blog.views }}</span>
            </div>
            <div class="item m-common-black">
              <i class="small pencil alternate icon"></i><span>字数≈{{ blog.words }}字</span>
            </div>
            <div class="item m-common-black">
              <i class="small clock icon"></i><span>阅读时长≈{{ blog.readTime }}分</span>
            </div>
          </div>
        </div>
        <!--文章内容-->
        <div class="typo m-padded-tb-small line-numbers match-braces rainbow-braces" v-html="blog.content"></div>
      </div>
    </div>
    <div v-else class="ui padded attached segment" style="text-align:center;padding:40px">
      加载中...
    </div>
  </div>
</template>

<script>
  import { getBlogById } from "@/api/home";
  import { dateFormat } from "@/util/dateTimeFormatUtils";

  export default {
    name: "blogDetail",
    data() {
      return {
        blog: null
      }
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        vm.getBlog(to.params.blogId)
      })
    },
    methods: {
      getBlog(blogId) {
        getBlogById(blogId).then(res => {
          if (res.code === 200) {
            this.blog = res.data
          }
        }).catch(() => {
          this.$router.push('/home')
        })
      },
      dateFormat
    }
  }
</script>
