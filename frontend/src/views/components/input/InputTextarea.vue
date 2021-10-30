<template>
  <el-input
    class="input-area"
    type="textarea"
    :placeholder="placeHolder"
    resize="none"
    :autosize="{ minRows: minRows, maxRows: maxRows }"
    v-model="content"
    @input="updateInput"
  ></el-input>
</template>

<script>
export default {
  name: 'InputTextarea',
  props: {
    updateContent : {
      type: String,
    },
    minRows:{
      type: Number,
    },
    maxRows:{
      type: Number,
    },
    placeHolder:{
      type: String,
    }
  },
  watch: {
    updateContent : {
      immediate: true,
      handler(val) {
        this.content = val;
      }
    }
  },
  data() {
    return {  
      content : '',
    }
  },
  methods: {
    initData() {
      this.content = ''
    },
    updateInput() {
      this.$emit('inputContent', this.content);
    },
  },
}
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/inputTextarea.scss';
</style>