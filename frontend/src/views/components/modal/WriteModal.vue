<template>
  <b-modal id="write-modal" size="xl" centered>
    <template #modal-header="{ close }">
      <h5 class="modal-title text-center">
        WorkerHolic
      </h5>
      <button
          type="button"
          aria-label="Close"
          class="close"
          @click="close()"
        >
          ×
        </button>
    </template>

    <!-- 모달 소개  -->
    <div class="text-center m-5">
      <h1 class="content-expain">
        당신 근처의 직장인들을 위한
      </h1>
    </div>
    <h2 class="content-intro">
      지금 당신의 근처에 사는 직장인과 교류해보세요.
    </h2>

    <!-- 로그인 버튼 시작 -->
    <div class="login-button">
      <div class="email-text">
        이메일
      </div>

      <el-form :model="dynamicValidateForm" ref="dynamicValidateForm">
        <el-form-item
          prop="email"
          :rules="[
            {
              required: true,
              message: '올바른 이메일 형식을 입력해주세요.',
              trigger: 'blur',
            },
            {
              type: 'email',
              message: '올바른 이메일 형식을 입력해주세요.',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <div class="input-email">
            <el-input
              type="email"
              placeholder="이메일을 입력해 주세요."
              id="email"
              v-model="dynamicValidateForm.email"
            />
          </div>
        </el-form-item>
        <el-form-item id="email-item">
          <el-button
            pill
            id="email-login-button"
            class="mt-3"
            @click="submitForm('dynamicValidateForm')"
          >
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
          </el-button>
        </el-form-item>
      </el-form>

      <div class="or-text">
        or
      </div>

      <social-login-button :icon="naver"></social-login-button>
      <social-login-button :icon="facebook"></social-login-button>
      <social-login-button :icon="google"></social-login-button>

      <p class="footer-info">
        걱정마세요! 여러분의 활동은 SNS에 노출되지 않습니다.
      </p>
    </div>
  </b-modal>
</template>

<script>
import SocialLoginButton from '../button/SocialLoginButton.vue';

export default {
  components: { SocialLoginButton },
  name: 'LoginModal',
  data() {
    return {
      dynamicValidateForm: {
        email: '',
      },
      naver: 'naver',
      facebook: 'facebook',
      google: 'google',
    };
  },
  methods: {
    submitForm(formName) {
      console.log(this.dynamicValidateForm.email);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  },
};
</script>

<style lang="scss">
@import '@/assets/scss/components/index.scss';
</style>
