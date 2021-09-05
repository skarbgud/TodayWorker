<template>
  <div class="mt-3 mb-2">
    <a href="#">
    <b-form-tags
      input-id="tags-pills"
      v-model="value"
      tag-variant="primary"
      tag-pills
      size="md"
      separator=" "
      placeholder=""
      add-on-change=false
      disabled
    />
    </a>
  </div>
</template>

<script>
export default {
  name: "HashTag",
  data() {
    return {
      value:["# aaaa","# bbb","# ccc"]
    }
  },
  methods:{
  }
};
</script>


<style lang="scss" scoped>
// @import '@/assets/scss/components/hashTag.scss';
</style>

