<template>
  <b-modal id="login-modal" centered hide-header hide-footer>
    <!-- 모달 최상단 Title -->
    <h5 class="modal-title text-center">
      WorkerHolic
      <button
        type="button"
        aria-label="Close"
        class="close"
        @click="$bvModal.hide('login-modal')"
      >
        ×
      </button>
    </h5>

    <!-- 모달 소개  -->
    <div class="text-center m-5">
      <h1 class="content-expain">
        직장인들을 위한<br />커뮤니티 플랫폼, WorkerHolic!
      </h1>
    </div>
    <h2 class="content-intro">
      당신의 근처에 사는 직장인과 교류해보세요.
    </h2>

    <!-- 로그인 버튼 시작 -->
    <div class="login-button">
      <div class="email-text">
        이메일
      </div>
      <div class="input-email">
        <input type="email" placeholder="이메일을 입력해 주세요." id="email" />
      </div>
      <button pill class="mt-3 email-login-button">
        <svg
          class="icon-image"
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
        >
          <g fill="none" fill-rule="evenodd" stroke="#FFF" stroke-width="2">
            <rect width="17.2" height="14" x="3.4" y="5" rx="3"></rect>
            <path d="M3.2 5.6L12 12l8.8-6.4"></path>
          </g></svg
        >이메일로 시작하기
      </button>
      <div class="or-text">
        or
      </div>
      <button class="social-login-button">
        <img
          class="icon-image"
          width="18"
          height="18"
          viewBox="0 0 18 18"
          src="@/assets/icon/naver.png"
        />
        네이버로 시작하기
      </button>
      <button class="social-login-button">
        <img
          class="icon-image"
          width="18"
          height="18"
          viewBox="0 0 18 18"
          src="@/assets/icon/facebook.png"
        />
        페이스북으로 시작하기
      </button>

      <button class="social-login-button">
        <img
          class="icon-image"
          width="18"
          height="18"
          viewBox="0 0 18 18"
          src="@/assets/icon/google.png"
        />
        Google로 시작하기
      </button>

      <p class="footer-info">
        걱정마세요! 여러분의 활동은 SNS에 노출되지 않습니다.
      </p>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'LoginModal',
};
</script>

<style lang="scss">
@import '@/assets/scss/components/index.scss';
</style>
