<template>
  <div>
    <b-button
      size="sm"
      squared
      class="my-2 col-sm-12"
      variant="light"
      v-b-modal.login-modal
      >회원가입 / 로그인
    </b-button>
    <!-- Login Modal 창 ( v-b-modal -> id="login-modal" ) -->
    <login-modal></login-modal>
  </div>
</template>

<script>
import LoginModal from '@/views/components/modal/LoginModal';

export default {
  name: 'LoginButton',
  components: { LoginModal },
  data() {
    return {};
  },
};
</script>

<style></style>
