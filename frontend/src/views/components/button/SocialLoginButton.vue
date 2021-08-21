<template>
  <button class="social-login-button">
    <img
      class="icon-image"
      width="18"
      height="18"
      viewBox="0 0 18 18"
      :src="logo"
    />
    네이버로 시작하기
  </button>
</template>

<script>
export default {
  name: 'SocialLoginButton',
  props: ['icon'],
  data() {
    return {
      naverPath: require('@/assets/icon/naver.png'),
      facebookPath: require('@/assets/icon/facebook.png'),
      googlePath: require('@/assets/icon/google.png'),
    };
  },
  computed: {
    logo() {
      if (this.icon === 'naver') {
        return this.naverPath;
      } else if (this.icon === 'facebook') {
        return this.facebookPath;
      } else if (this.icon === 'google') {
        return this.googlePath;
      }
      return null;
    },
  },
};
</script>

<style></style>
