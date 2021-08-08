<template>
<div>
  <b-navbar toggleable="lg" class="border-bottom border-secondary">
    <router-link to="/"><b-navbar-brand tag="h1" class="mb-0">WorkerHolic</b-navbar-brand></router-link>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse  id="nav-collapse" is-nav>
      <!-- 중앙 메뉴 -->
      <b-navbar-nav class="ml-auto">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="board" v-b-hover="hoverHandler">게시판</el-menu-item>
          <el-menu-item index="calendar">일정</el-menu-item>
          <el-menu-item index="salary">연봉 계산기</el-menu-item>
          <el-menu-item index="friend">친구</el-menu-item>
        </el-menu>
      </b-navbar-nav>

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
  <b-card
    class="mb-2"
    v-if="isHovered"
  >
    <b-card-text>
      메뉴들
    </b-card-text>

    <b-button href="#" variant="primary">메뉴</b-button>
  </b-card>
</div>
</template>

<script>
export default {
  name: 'FrameHeader',
  data() {
    return {
      activeIndex: '',
      isHovered: false,
    };
  },
  methods: {
    handleSelect(activeIndex) {
      this.activeIndex = activeIndex;
    },
    hoverHandler(hovered) {
      this.isHovered = hovered
    }
  }
};
</script>

<style scoped>
#search-form
{
  display: contents;
}
.navbar
{
  padding: 0 1rem;
}
.form-control
{
  width: 90%;
}
</style>
