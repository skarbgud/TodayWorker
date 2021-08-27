<template>
  <div>
    <b-button
      size="sm"
      squared
      class="my-2 mr-2 px-3 col-sm-auto col-md-auto col-lg-auto"
      variant="outline-danger"
      @click="clickWriteButton"
      >글쓰기</b-button
    >
    <!-- Write Modal 창 ( v-b-modal -> id="write-modal" ) -->
    <!-- <write-modal></write-modal> -->
    <!-- bootstrap-vue 에서는 el-input 한글 오류 발생으로 인해 element ui로 팝업 구현 -->
    <regist-modal ref="modal"></regist-modal>
  </div>
</template>

<script>
// import WriteModal from '@/views/components/modal/WriteModal';
import RegistModal from '@/views/components/modal/RegistModal';

export default {
  name: 'WriteButton',
  components: { RegistModal },
  data() {
    return {
      dialogVisible: false,
    };
  },
  methods: {
    clickWriteButton() {
      this.$refs.modal.open();
    },
  },
};
</script>

<style></style>
