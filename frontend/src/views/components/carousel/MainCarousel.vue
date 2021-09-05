<template>
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
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="180"
            src="../../../assets/image/mainImage1.png"
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
            src="../../../assets/image/mainImage2.png"
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
            src="../../../assets/image/mainImage3.png"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  name: 'MainCarousel',
  data() {
    return {
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

<style lang="scss" scoped>
@import '@/assets/scss/components/maincarousel.scss';
</style>
