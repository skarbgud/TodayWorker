<template>
  <el-input
    class="input-area"
    type="textarea"
    :placeholder="placeHolder"
    resize="none"
    :autosize="{ minRows: minRows, maxRows: maxRows }"
    v-model="content"
  ></el-input>
</template>

<script>
export default {
  name: 'InputArea',
  data() {
    return {
      content: '',
    };
  },
  props: {
    minRows:{
      type: Number,
    },
    maxRows:{
      type: Number,
    },
    placeHolder:{
      type: String,
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/inputTextarea.scss';
</style>