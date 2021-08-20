<template>
  <div>
    <b-button
      size="sm"
      squared
      class="my-2"
      variant="light"
      @click="clickMemberLoginButton()"
      >회원가입 / 로그인</b-button
    >
    <b-modal ref="login-modal" centered hide-footer title="Worker Holic">
      <div class="d-block text-center">
        <h3>직장인들을 위한</h3>
        <h3>커뮤니티 플랫폼, WorkerHolic!</h3>
      </div>

      <b-button pill class="mt-3" variant="primary" block
        >이메일로 시작하기</b-button
      >

      <b-button variant="outline-primary" block>페이스북으로 시작하기</b-button>
      <b-button variant="outline-primary" block>Naver로 시작하기</b-button>
      <b-button variant="outline-primary" block>Google으로 시작하기</b-button>

      <b-button class="mt-3" variant="outline-danger" block @click="hideModal"
        >Close Me</b-button
      >
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'LoginButton',
  data() {
    return {};
  },
  methods: {
    // 회원가입 / 로그인 버튼 method
    clickMemberLoginButton() {
      this.$refs['login-modal'].show();
    },
    hideModal() {
      this.$refs['login-modal'].hide();
    },
    toggleModal() {
      // We pass the ID of the button that we want to return focus to
      // when the modal has hidden
      this.$refs['login-modal'].toggle('#toggle-btn');
    },
  },
};
</script>

<style>
#__BVID__19___BV_modal_header_ {
  border-bottom: 2px black;
}
</style>
