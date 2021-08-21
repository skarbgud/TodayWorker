<template>
  <div>
    <b-navbar toggleable="lg" class="border-bottom border-secondary">
      <b-navbar-brand tag="h1" class="mb-0" @click="goMainPage()"
        ><span style="color: red">WorkerHolic</span></b-navbar-brand
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
            <el-menu-item index="board" v-b-hover="hoverBoardTab"
              >게시판</el-menu-item
            >
            <el-menu-item index="calendar" v-b-hover="disableHoverTab"
              >일정</el-menu-item
            >
            <el-menu-item index="salary" v-b-hover="disableHoverTab"
              >연봉 계산기</el-menu-item
            >
            <el-menu-item index="friend" v-b-hover="disableHoverTab"
              >친구</el-menu-item
            >
          </el-menu>
        </b-navbar-nav>

        <!-- nav 오른쪽 글쓰기 검색  회원가입/로그인-->
        <b-navbar-nav class="ml-auto">
          <b-button
            size="sm"
            squared
            class="my-2 mr-2 px-3"
            variant="outline-danger"
            >글쓰기</b-button
          >

          <b-nav-form class="mr-2 my-2" v-on:submit.prevent>
            <search-form></search-form>
          </b-nav-form>

          <!-- <b-button class="my-2" variant="outline-danger" size="sm"
            >회원가입 / 로그인</b-button
          > -->
          <login-button></login-button>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <!-- 게시판 hover시 나오는 메뉴 -->
    <b-card v-b-hover="hoverMenuBar" v-show="isHoveredMenu || isHoveredTab">
      <b-card-group>
        <b-list-group
          class="mr-4 mt-1"
          v-for="(name, index) in cardTitles"
          :key="index"
        >
          <b-list-group-item
            href="#"
            disabled
            class="flex-column align-items-start"
          >
            <small class="text-muted">{{ name.title }}</small>
          </b-list-group-item>
        </b-list-group>
      </b-card-group>
    </b-card>

    <!-- Navbar 하단 이미지 -->
    <div class="d-none d-xl-block">
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="white"
        img-width="1024"
        img-height="180"
        style="text-shadow: 1px 1px 2px #333"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
        v-show="showMainImage"
      >
        <!-- Text slides with image -->
        <b-carousel-slide caption="Worker Holic" text="지금 시작하세요">
          <template #img>
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="180"
              src="../../../assets/image/main1.png"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>

        <!-- Slides with custom text -->
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="180"
              src="../../../assets/image/main2.png"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>

        <!-- Slides with custom text -->
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="180"
              src="../../../assets/image/main3.png"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>
  </div>
</template>

<script>
import SearchForm from '@/views/components/input/SearchForm.vue';
import cardTitles from '@/constant/index';
import LoginButton from '@/views/components/button/LoginButton';

export default {
  name: 'FrameHeader',
  components: { SearchForm, LoginButton },
  data() {
    return {
      activeIndex: '',
      isHoveredTab: false,
      isHoveredMenu: false,
      search: '',
      cardTitles,
      slide: 0,
      sliding: null,
      showMainImage: false,
    };
  },
  mounted() {
    if (this.$route.path === '/') {
      this.showMainImage = true;
    }
  },
  watch: {
    $route() {
      // 라우터 감지로 메인페이지일때만 Navbar하단 이미지 출력
      if (this.$route.path === '/') {
        this.showMainImage = true;
      } else {
        this.showMainImage = false;
      }
    },
  },
  methods: {
    goMainPage() {
      // 중복 url 이동때문에 오류 catch
      this.$router.push('/').catch(() => {});
    },
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
    // 슬라이딩 method
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
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
.border-secondary {
  border-color: #dee2e6 !important;
}
.el-menu-item:hover {
  border-bottom: 2px solid #909399 !important;
}
img {
  height: 400px;
}
.carousel-item {
  height: 400px;
}
</style>
