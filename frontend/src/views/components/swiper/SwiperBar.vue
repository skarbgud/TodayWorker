<template>
  <div>
    <b-row class="mb-4">
      <swiper class="swiper" :options="swiperOption">
        <div class="swiper-pagination" slot="pagination" />
        <swiper-slide
          v-for="(title, index) in boardCategori"
          :key="index"
          class="item"
        >
          <a @click="golive(index)">
              {{ boardCategori[index].title }}
              {{boardCategori[index].emoticon}}
            </a>
        </swiper-slide>
      </swiper>
    </b-row>
  </div>
</template>

<script>
import boardCategori from '@/constant/board-categori';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';

export default {
  name: 'SwiperBar',
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      boardCategori,
      swiperOption: {
        slidesPerView: 'auto',
        spaceBetween: 6, 
        slidesOffsetBefore: 0, 
        slidesOffsetAfter: 0, 
        freeMode: true, 
        centerInsufficientSlides: true, 
      }
    
    };
  },
  methods: {
    golive(index) {
      const a = document.getElementById("class")
      a.classList.add
      this.$router.push(this.boardCategori[index].path).catch(() => {});
    },
  },
};
</script>
<style lang="scss" scoped>
.swiper-container {
   padding: 0 20px; 
  &:before,
  &:after { 
    display: block;
    position: absolute;
    top: 0;
    width: 20px;
    height: 100%;
    z-index: 10;
    content: "";
  }
  &:before {
    left: 0;
    background: linear-gradient(90deg, #fff -20.19%, rgba(255, 255, 255, 0.8) 18.31%, rgba(255, 255, 255, 0) 75%);
  }
  &:after {
    right: 0;
    background: linear-gradient(270deg, #fff -20.19%, rgba(255, 255, 255, 0.8) 18.31%, rgba(255, 255, 255, 0) 75%);
  }
  .swiper-wrapper {
    .swiper-slide {
      width: auto; 
      min-width: 56px; 
      padding: 0px 14px;
      font-size: 14px;
      line-height: 36px;
      text-align: center;
      color: #84868c;
      border: 0;
      border-radius: 18px;
      background: #f3f4f7;
      appearance: none;
      cursor: pointer;
      &[aria-selected="true"] {
        color: #fff;
        background: #000;
      }
    }
  }
}
</style>
