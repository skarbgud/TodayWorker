<template>
  <div class="mt-3 mb-2 ml-1 mr-1">
    <b-form-tags
      input-id="tags-pills"
      v-model="tagList"
      tag-variant="primary"
      tag-pills
      size="md"
      addButtonText="+"
      placeholder="엔터하여 태그를 추가해주세요."
      duplicateTagText="중복된 해시태그입니다"
    >
    </b-form-tags>
  </div>
</template>

<script>
export default {
  name: 'HashTag',
  data() {
    return {
      tagList: [],
    };
  },
  watch: {
    tagList: {
      immediate:true,
      handler(value)
      {
        let convertValue = value + '1';
        console.log(convertValue);
        this.changeTagList();
      }
    }
  },
  methods: {
    changeTagList(){
      this.$emit('changeTagList', this.tagList);
    },
  },
};
</script>

<style lang="scss" scoped>
// @import '@/assets/scss/components/hashTag.scss';
</style>
