<template>
  <div>
    <b-button
      size="sm"
      squared
      class="my-2 mr-2 px-3 col-sm-auto col-md-auto col-lg-auto"
      variant="outline-danger"
      v-b-modal.write-modal
      >글쓰기</b-button
    >
    <!-- Write Modal 창 ( v-b-modal -> id="write-modal" ) -->
    <write-modal></write-modal>
  </div>
</template>

<script>
import WriteModal from '@/views/components/modal/WriteModal';

export default {
  name: 'WriteButton',
  components: { WriteModal },
  data() {
    return {};
  },
};
</script>

<style></style>
