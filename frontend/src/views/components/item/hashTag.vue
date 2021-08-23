<template>
  <div class="mt-4">
    <div class="tag-box" v-for="value in hashTag" :key="value">
      <a href="#"> {{ value }}</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "HashTag",
  props: {
    hashTag: {
      type: Object,
    },
  },
};
</script>


<style lang="scss">
@import '@/assets/scss/components/hashTag.scss';
</style>

