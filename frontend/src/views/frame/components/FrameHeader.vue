<template>
<div>
  <b-navbar toggleable="lg" class="border-bottom border-secondary">
    <b-navbar-brand tag="h1" class="mb-0">WorkerHolic</b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-nav>
        <b-nav-item active>게시판</b-nav-item>
        <b-nav-item>시간표</b-nav-item>
        <b-nav-item>연봉 계산기</b-nav-item>
        <b-nav-item>친구</b-nav-item>
      </b-nav>

      <!-- nav 오른쪽 검색  회원가입/로그인-->
      <b-navbar-nav class="ml-auto">
        <b-nav-form id="search-form">
          <b-form-input size="sm" class="mr-sm-2"></b-form-input>
          <b-button size="sm" class="my-2 my-sm-0 mr-sm-2" type="submit">검색</b-button>
        </b-nav-form>
        
        <b-button variant="outline-primary" size="sm">회원가입 / 로그인</b-button>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>

<script>
export default {
  name: 'FrameHeader',
  data() {
    return {};
  },
  methods: {
  }
};
</script>

<style>
#search-form
{
  display: contents;
}
</style>
