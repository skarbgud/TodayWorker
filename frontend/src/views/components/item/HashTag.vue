<template>
  <div class="mt-3 mb-2">
    <a href="#">
    <b-form-tags
      input-id="tags-pills"
      v-model="tagList"
      tag-variant="primary"
      tag-pills
      size="md"
      separator=" "
      placeholder=""
      :disabled="disabled"
      addButtonText="+"
    />
    </a>
  </div>
</template>

<script>
export default {
  name: "HashTag",
  props: ["tagList", "disabled"],
  data() {
    return {
     
    }
  },
  methods:{
  }
};
</script>


<style lang="scss" scoped>
// @import '@/assets/scss/components/hashTag.scss';
</style>

