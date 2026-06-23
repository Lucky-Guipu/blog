<template>
	<div class="ui bottom" style="text-align:center">
		<el-pagination @current-change="handleCurrentChange" :current-page="pageNum" :page-count="totalPage" layout="prev, pager, next" background hide-on-single-page>
		</el-pagination>
	</div>
</template>


<script setup>
import { ref, onActivated, computed, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import { useBlogStore } from '@/stores/blog'

const props = defineProps({
  getBlogList: {
    type: Function,
    required: true
  },
  totalPage: {
    type: Number,
    required: true
  }
})

const blogStore = useBlogStore()
const route = useRoute()

const pageNum = ref(1)

onActivated(() => {
  nextTick(() => {
    if (!blogStore.isBlogToHome) {
      pageNum.value = 1
    }
  })
})

const handleCurrentChange = (newPage) => {
  if (route.name === 'home') {
    window.scrollTo({
      top: blogStore.clientSize.clientHeight,
      behavior: 'smooth'
    })
  } else {
    scrollToTop()
  }
  pageNum.value = newPage
  props.getBlogList(newPage)
}

const scrollToTop = () => {
  window.scrollTo({ top: 0, behavior: 'smooth' })
}
</script>


<style>
	.el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li {
		background-color: #ffffff !important;
	}

	.el-pagination.is-background .el-pager li:not(.disabled).active {
		background-color: #409EFF !important;
	}
</style>