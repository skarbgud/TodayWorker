<template>
  <div>
    <b-navbar toggleable="lg" class="border-bottom border-secondary">
      <b-navbar-brand tag="h1" class="mb-0" to="/"
        ><span style="color:red;">WorkerHolic</span></b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <!-- 중앙 메뉴 -->
        <b-navbar-nav class="ml-auto">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
          >
            <el-menu-item index="board" v-b-hover="hoverHandler"
              >게시판</el-menu-item
            >
            <el-menu-item index="calendar">일정</el-menu-item>
            <el-menu-item index="salary">연봉 계산기</el-menu-item>
            <el-menu-item index="friend">친구</el-menu-item>
          </el-menu>
        </b-navbar-nav>

        <!-- nav 오른쪽 검색  회원가입/로그인-->
        <b-navbar-nav class="ml-auto">
          <b-nav-form class="mr-2 my-2" v-on:submit.prevent>
            <el-input
              v-model="search"
              placeholder="검색"
              prefix-icon="el-icon-search"
              size="small"
              v-on:keyup.enter.native="submit(search)"
            >
              <!-- 클리어 아이콘 -->
              <i
                slot="suffix"
                class="el-input__icon el-icon-circle-close el-input__clear"
                @click="reset"
                v-if="search !== ''"
              ></i>
            </el-input>
          </b-nav-form>

          <b-button class="my-2" variant="outline-dark" size="sm"
            >회원가입 / 로그인</b-button
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-card class="mb-2" v-if="isHovered">
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
      search: '',
    };
  },
  methods: {
    handleSelect(activeIndex) {
      this.activeIndex = activeIndex;
    },
    hoverHandler(hovered) {
      this.isHovered = hovered;
    },
    submit(search) {
      console.log(`${search}검색`);
    },
    reset() {
      //검색 초기화 버튼(claerable)
      this.search = '';
    },
  },
};
</script>

<style scoped>
.form-inline {
  display: block;
}
.navbar {
  padding: 0 1rem;
}
.form-control {
  width: 90%;
}
</style>
