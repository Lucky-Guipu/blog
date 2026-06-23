<template>
  <div>
    <div class="ui segments m-box">
      <div class="music-box">
        <meting-js server="netease" type="playlist" id="18083174473" list-folded="false" list-max-height="340px">
        </meting-js>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
:deep(.aplayer-played) {
  background: #409EFF !important;
}

:deep(.aplayer-thumb) {
  background: #409EFF !important;
}
</style>