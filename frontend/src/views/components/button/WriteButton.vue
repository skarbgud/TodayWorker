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
    <!-- bootstrap-vue 에서는 el-input 한글 오류 발생으로 인해 element ui로 팝업 구현 -->
    <write-modal ref="modal"></write-modal>
  </div>
</template>

<script>
import WriteModal from '@/views/components/modal/WriteModal';

export default {
  name: 'WriteButton',
  components: { WriteModal },
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
