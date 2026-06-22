<template>
  <div class="about-page">
    <div class="ui top attached segment about-card">
      <h2 class="about-title">关于帅气的 LuckyCat</h2>
      <div class="about-divider"></div>
      <div class="typo content about-content" v-html="about.content"></div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const about = reactive({
  content: `
  <div class="mine-item-content">

    <div class="item">
      <div class="title">教育经历</div>
      <ul>
        <li>2024-09 华中农业大学，研究生， 生物信息学</li>
        <li>2020-09 武汉科技大学，本科，生物技术</li>
      </ul>
    </div>

    <div class="item">
      <div class="title">专业技能</div>
      <ul>
        <li>Java / Python / Vue / HTML / CSS / JavaScript </li>
      </ul>
    </div>

    <div class="item">
      <div class="title">科研成果</div>
      <ul>
        <li>2025-08 中科院一区论文二作</li>
        <li>2022-11 发明专利第三申请人</li>
      </ul>
    </div>

    <div class="item">
      <div class="title">实习经历</div>
      <ul>
        <li>2024-06 北京智谱华章科技有限公司</li>
        <li>2024-02 武汉新东方培训学校</li>
      </ul>
    </div>

    <div class="item">
      <div class="title">语言证书</div>
      <ul>
        <li>2023-06 CET4</li>
      </ul>
    </div>

    <div class="item">
      <div class="title">荣誉经历</div>
      <ul>
        <li>2026-02 杭州元启视界有限公司｜vibe coding比赛三等奖</li>
        <li>2025-11 学业二等奖学金</li>
        <li>2025-09 人工智能基础课程助教</li>
        <li>2024-11 学业二等奖学金</li>
        <li>2020-09 学生会干部</li>
      </ul>
    </div>

  </div>
  `
})
</script>

<style>
.about-title {
  text-align: center;
  font-size: 22px;
  font-weight: 600;
  margin-bottom: 20px;
  color: #333;
}


.about-divider {
  height: 1px;
  background: #eee;
  margin: 20px 0;
}

.mine-item-content {
  max-width: 800px;
  margin: 0 auto;
}

.item {
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

.title {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
  border-left: 4px solid #25CCF7;
  padding-left: 10px;
}

ul {
  margin: 0;
  padding-left: 20px;
}

li {
  line-height: 1.8;
  color: #555;
}
</style>
