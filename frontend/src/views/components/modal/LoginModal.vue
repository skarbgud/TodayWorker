<template>
  <b-modal id="login-modal" centered hide-header hide-footer>
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

    <div class="text-center m-5">
      <h1 class="content-expain">
        직장인들을 위한<br />커뮤니티 플랫폼, WorkerHolic!
      </h1>
    </div>
    <h2 class="content-intro">
      당신의 근처에 사는 직장인과 교류해보세요.
    </h2>

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
          src="@/assets/naver.png"
        />
        네이버로 시작하기
      </button>
      <button class="social-login-button">
        <svg
          class="icon-image"
          xmlns="http://www.w3.org/2000/svg"
          width="18"
          height="18"
          viewBox="0 0 18 18"
        >
          <path
            fill="#1877f2"
            fill-rule="evenodd"
            d="M18 9a9 9 0 1 0-10.406 8.89v-6.288H5.309V9h2.285V7.017c0-2.255 1.343-3.501 3.4-3.501.984 0 2.014.175 2.014.175v2.215h-1.135c-1.118 0-1.467.694-1.467 1.406V9h2.496l-.399 2.602h-2.097v6.289C14.71 17.215 18 13.492 18 9"
          ></path></svg
        >페이스북으로 시작하기
      </button>
      <button class="social-login-button">
        <svg
          class="icon-image"
          xmlns="http://www.w3.org/2000/svg"
          width="18"
          height="18"
          viewBox="0 0 18 18"
        >
          <g fill="none" fill-rule="evenodd">
            <path
              fill="#EA4335"
              d="M9 3.48c1.69 0 2.83.73 3.48 1.34l2.54-2.48C13.46.89 11.43 0 9 0 5.48 0 2.44 2.02.96 4.96l2.91 2.26C4.6 5.05 6.62 3.48 9 3.48z"
            ></path>
            <path
              fill="#4285F4"
              d="M17.64 9.2c0-.74-.06-1.28-.19-1.84H9v3.34h4.96c-.1.83-.64 2.08-1.84 2.92l2.84 2.2c1.7-1.57 2.68-3.88 2.68-6.62z"
            ></path>
            <path
              fill="#FBBC05"
              d="M3.88 10.78A5.54 5.54 0 0 1 3.58 9c0-.62.11-1.22.29-1.78L.96 4.96A9.008 9.008 0 0 0 0 9c0 1.45.35 2.82.96 4.04l2.92-2.26z"
            ></path>
            <path
              fill="#34A853"
              d="M9 18c2.43 0 4.47-.8 5.96-2.18l-2.84-2.2c-.76.53-1.78.9-3.12.9-2.38 0-4.4-1.57-5.12-3.74L.97 13.04C2.45 15.98 5.48 18 9 18z"
            ></path>
            <path d="M0 0h18v18H0z"></path>
          </g></svg
        >Google로 시작하기
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

<style>
.content-expain {
  line-height: 1.54;
  font-size: 26px;
  font-weight: 600;
  color: #333;
}
.content-intro {
  margin-top: 16px;
  line-height: 1.5;
  font-size: 16px;
  font-weight: 400;
  color: #666;
  text-align: center;
  word-break: break-word;
  margin-top: 24px;
  margin-bottom: 40px;
}
.login-button {
  position: relative;
  overflow: hidden;
  z-index: 1;
  margin-bottom: 20px;
}
.email-text {
  display: block;
  content: 'or';
  color: #969696;
  font-size: 14px;
  font-weight: 500;
  line-height: 1;
  text-align: left;
  margin: 10px auto;
}
.input-email {
  margin-top: 11px;
}
.input-email > input {
  width: 100%;
  height: 50px;
  padding-right: 15px;
  padding-left: 15px;
  border-radius: 5px;
  border: 1px solid #e1e2e3;
  background-color: #fff;
  font-size: 15px;
  color: #333;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  -webkit-box-shadow: none;
  box-shadow: none;
  outline: none;
  resize: none;
}
.email-login-button {
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  width: 100%;
  height: 54px;
  border: 0;
  border-radius: 27px;
  background-color: #36f;
  font-size: 16px;
  font-weight: 600;
  color: #fff;
  cursor: pointer;
}
.or-text {
  display: block;
  content: 'or';
  color: #969696;
  font-size: 14px;
  font-weight: 500;
  line-height: 1;
  text-align: center;
  margin: 10px auto;
}
.social-login-button {
  margin-bottom: 10px;
  color: #737373;
  border-radius: 27px;
  border: 1px solid #e1e2e3;
  background-color: #fff;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  width: 100%;
  height: 54px;
  font-size: 16px;
  font-weight: 600;
  background: none;
}
.icon-image {
  margin-right: 10px;
}
.footer-info {
  margin-top: 26px;
  text-align: center;
  font-size: 12px;
  line-height: 18px;
  color: #999;
}
</style>
