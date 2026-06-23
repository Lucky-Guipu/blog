<!--<template>-->
<!--	<div>-->
<!--		<div class="ui padded attached segment m-padded-tb-large m-margin-bottom-big m-box" v-for="item in blogList" :key="item.id">-->
<!--			<div class="ui large red right corner label" v-if="item.top">-->
<!--				<i class="arrow alternate circle up icon"></i>-->
<!--			</div>-->
<!--			<div class="ui middle aligned mobile reversed stackable">-->
<!--				<div class="ui grid m-margin-lr">-->
<!--					&lt;!&ndash;标题&ndash;&gt;-->
<!--					<div class="row m-padded-tb-small">-->
<!--						<h2 class="ui header m-center m-scaleup">-->
<!--							<a href="javascript:;" @click.prevent="toBlog(item)" class="m-black">{{ item.title }}</a>-->
<!--						</h2>-->
<!--					</div>-->
<!--					&lt;!&ndash;文章简要信息&ndash;&gt;-->
<!--					<div class="row m-padded-tb-small">-->
<!--						<div class="ui horizontal link list m-center">-->
<!--							<div class="item m-datetime">-->
<!--								<i class="small calendar icon"></i><span>{{ dateFormat(item.createTime,'YYYY-MM-DD') }}</span>-->
<!--							</div>-->
<!--							&lt;!&ndash; <div class="item m-views">-->
<!--								<i class="small eye icon"></i><span>{{ item.views }}</span>-->
<!--							</div> &ndash;&gt;-->
<!--							<div class="item m-common-black">-->
<!--								<i class="small pencil alternate icon"></i><span>字数≈{{ item.words }}字</span>-->
<!--							</div>-->
<!--							<div class="item m-common-black">-->
<!--								<i class="small clock icon"></i><span>阅读时长≈{{ item.readTime }}分</span>-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--					&lt;!&ndash;分类&ndash;&gt;-->
<!--					&lt;!&ndash; <router-link :to="`/category/${item.category.name}`" class="ui orange large ribbon label">-->
<!--						<i class="small folder open icon"></i><span class="m-text-500">{{ item.category.name }}</span>-->
<!--					</router-link> &ndash;&gt;-->
<!--					&lt;!&ndash;文章Markdown描述&ndash;&gt;-->
<!--					<div class="typo m-padded-tb-small line-numbers match-braces rainbow-braces" v-html="item.description"></div>-->
<!--					&lt;!&ndash;阅读全文按钮&ndash;&gt;-->
<!--					<div class="row m-padded-tb-small m-margin-top">-->
<!--						<a href="javascript:;" @click.prevent="toBlog(item)" class="color-btn">阅读全文</a>-->
<!--					</div>-->
<!--					&lt;!&ndash;横线&ndash;&gt;-->
<!--					<div class="ui section divider m-margin-lr-no"></div>-->
<!--					&lt;!&ndash;标签&ndash;&gt;-->
<!--					<div class="row m-padded-tb-no">-->
<!--						<div class="column m-padding-left-no">-->
<!--							<router-link :to="`/tag/${tag.name}`" class="ui tag label m-text-500 m-margin-small" :class="tag.color" v-for="(tag,index) in item.tags" :key="index">{{ tag.name }}</router-link>-->
<!--						</div>-->
<!--					</div>-->
<!--				</div>-->
<!--			</div>-->
<!--		</div>-->
<!--	</div>-->
<!--</template>-->

<!--<script>-->
<!--  import {dateFormat} from "@/util/dateTimeFormatUtils";-->

<!--  export default {-->
<!--		name: "blogItem",-->
<!--		props: {-->
<!--			blogList: {-->
<!--				type: Array,-->
<!--				required: true-->
<!--			}-->
<!--		},-->
<!--		methods: {-->
<!--			toBlog(blog) {-->
<!--				this.$router.push({ name: 'blog', params: { blogId: blog.id } })-->
<!--			},-->
<!--      dateFormat:dateFormat-->
<!--		}-->
<!--	}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->



<template>
  <div class="blog-list">
    <div
        class="blog-card"
        v-for="item in blogList"
        :key="item.id"
    >
      <!-- 置顶 -->
      <div
          v-if="item.top"
          class="top-badge"
      >
        📌 置顶
      </div>

      <!-- 标题 -->
      <h2 class="blog-title">
        <a
            href="javascript:;"
            @click.prevent="toBlog(item)"
        >
          {{ item.title }}
        </a>
      </h2>

      <!-- 元信息 -->
      <div class="blog-meta">
        <span>
          <i class="calendar icon"></i>
          {{ dateFormat(item.createTime,'YYYY-MM-DD') }}
        </span>

        <span>
          <i class="pencil alternate icon"></i>
          {{ item.words }} 字
        </span>

        <span>
          <i class="clock icon"></i>
          {{ item.readTime }} 分钟
        </span>
      </div>

      <!-- 描述 -->
      <div
          class="blog-desc typo line-numbers match-braces rainbow-braces"
          v-html="item.description"
      ></div>

      <!-- 标签 -->
      <div
          v-if="item.tags && item.tags.length"
          class="tag-list"
      >
        <router-link
            v-for="(tag,index) in item.tags"
            :key="index"
            :to="`/tag/${tag.name}`"
            class="blog-tag"
        >
          #{{ tag.name }}
        </router-link>
      </div>

      <!-- 分割线 -->
      <div class="divider"></div>

      <!-- 阅读全文 -->
      <div class="read-more">
        <a
            href="javascript:;"
            @click.prevent="toBlog(item)"
        >
          阅读全文 →
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import { dateFormat } from "@/util/dateTimeFormatUtils";

export default {
  name: "BlogItem",

  props: {
    blogList: {
      type: Array,
      required: true
    }
  },

  methods: {
    toBlog(blog) {
      this.$router.push({
        name: "blog",
        params: {
          blogId: blog.id
        }
      });
    },

    dateFormat
  }
};
</script>

<style scoped>
/* 列表 */
.blog-list {
  display: flex;
  flex-direction: column;
  gap: 28px;
}

/* 卡片 */
.blog-card {
  position: relative;

  padding: 32px;

  border-radius: 18px;

  background: #fff;

  transition: all 0.3s ease;

  box-shadow:
      0 4px 20px rgba(0, 0, 0, 0.05);
}

.blog-card:hover {
  box-shadow:
      0 16px 40px rgba(0, 0, 0, 0.12);

}

/* 置顶 */
.top-badge {
  display: inline-block;

  margin-bottom: 14px;

  padding: 5px 12px;

  border-radius: 999px;

  font-size: 13px;

  color: #ff6b35;

  background: #fff1eb;
}

/* 标题 */
.blog-title {
  margin: 0;

  text-align: center;
}

.blog-title a {
  font-size: 30px;

  font-weight: 700;

  color: #222;

  text-decoration: none;

  transition: 0.3s;
}

.blog-title a:hover {
  color: #ff6b35;
}

/* 元信息 */
.blog-meta {
  margin-top: 18px;

  display: flex;

  justify-content: center;

  flex-wrap: wrap;

  gap: 24px;

  color: #888;

  font-size: 14px;
}

.blog-meta span {
  display: flex;
  align-items: center;
}

.blog-meta i {
  margin-right: 6px;
}

/* 摘要 */
.blog-desc {
  position: relative;

  margin-top: 24px;

  line-height: 1.9;

  color: #666;

  font-size: 15px;

  max-height: 140px;

  overflow: hidden;
}

/* 底部渐变 */
.blog-desc::after {
  content: "";

  position: absolute;

  left: 0;
  right: 0;
  bottom: 0;

  height: 50px;

  background:
      linear-gradient(
          transparent,
          white
      );
}

/* 标签 */
.tag-list {
  margin-top: 24px;

  display: flex;

  flex-wrap: wrap;

  gap: 10px;
}

.blog-tag {
  padding: 6px 14px;

  border-radius: 999px;

  background: #f6f6f6;

  color: #666;

  text-decoration: none;

  transition: all 0.3s;
}

.blog-tag:hover {
  background: #ff6b35;

  color: white;
}

/* 分割线 */
.divider {
  margin-top: 22px;

  border-top: 1px solid #f0f0f0;
}

/* 阅读全文 */
.read-more {
  margin-top: 18px;

  text-align: right;
}

.read-more a {
  color: #ff6b35;

  font-weight: 600;

  text-decoration: none;

  transition: all 0.3s;
}

.read-more a:hover {
  letter-spacing: 2px;
}

/* 手机端 */
@media (max-width: 768px) {
  .blog-card {
    padding: 22px;
  }

  .blog-title a {
    font-size: 22px;
  }

  .blog-meta {
    gap: 12px;
  }

  .blog-desc {
    max-height: 110px;
  }
}

</style>