<template>
  <div>
    <b-navbar toggleable="lg" class="border-bottom border-secondary">
      <b-navbar-brand tag="h1" class="mb-0" href="/">
        <img src="../../../assets/image/logo1.png" alt="only" :width="40" :height="40">
      </b-navbar-brand>

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
            <el-menu-item index="board" v-b-hover="hoverBoardTab">
              📌게시판
            </el-menu-item>
            <el-menu-item
              v-for="(menu, index) in functionMenu"
              :index="menu.index"
              :key="index"
              v-b-hover="disableHoverTab"
            >
              {{ menu.name }}
            </el-menu-item>
          </el-menu>
        </b-navbar-nav>

        <!-- nav 오른쪽 글쓰기 검색  회원가입/로그인-->
        <b-navbar-nav class="ml-auto">
          <!-- 글쓰기 -->
          <write-button></write-button>
          <!-- 검색 -->
          <b-nav-form class="mr-2 my-2" v-on:submit.prevent>
            <search-form></search-form>
          </b-nav-form>
          <!-- 회원가입 / 로그인 -->
          <login-button></login-button>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <!-- 게시판 hover시 나오는 메뉴 -->
    <b-card v-b-hover="hoverMenuBar" v-show="isHoveredMenu || isHoveredTab">
      <b-card-group>
        <b-list-group
          class="mr-4 mt-1"
          v-for="(categori, index) in boardCategori"
          :key="index"
        >
          <b-list-group-item
            @click="goMenu(index)"
            class="flex-column align-items-start"
          >
            <small class="text-muted"
              >{{ categori.emoticon }} {{ categori.title }}</small
            >
          </b-list-group-item>
        </b-list-group>
      </b-card-group>
    </b-card>

    <!-- Navbar 하단 이미지 -->
    <main-carousel></main-carousel>
  </div>
</template>

<script>
import projectTitle from '@/constant/project-title';
import SearchForm from '@/views/components/input/SearchForm';
import boardCategori from '@/constant/board-categori';
import functionMenu from '@/constant/function-menu';
import LoginButton from '@/views/components/button/LoginButton';
import MainCarousel from '@/views/components/carousel/MainCarousel';
import WriteButton from '@/views/components/button/WriteButton';

export default {
  name: 'FrameHeader',
  components: { SearchForm, LoginButton, MainCarousel, WriteButton },
  data() {
    return {
      projectTitle,
      activeIndex: '',
      isHoveredTab: false,
      isHoveredMenu: false,
      search: '',
      boardCategori,
      functionMenu,
    };
  },

  methods: {
    handleSelect(activeIndex) {
      this.activeIndex = activeIndex;
    },
    // hoverHandling Method
    hoverBoardTab(hovered) {
      this.isHoveredTab = hovered;
      this.isHoveredMenu = true;
    },
    disableHoverTab() {
      this.isHoveredTab = false;
      this.isHoveredMenu = false;
    },
    hoverMenuBar(hovered) {
      this.isHoveredMenu = hovered;
    },
    goMenu(index) {
      this.$router.push(`/${this.boardCategori[index].path}`);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/layouts/frameheader.scss';
</style>
