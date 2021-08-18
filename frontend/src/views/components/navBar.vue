<template>
  <div>
    <b-row class="mb-4">
      <swiper class="swiper" :options="swiperOption">
        <div class="swiper-pagination" slot="pagination" />
        <swiper-slide
          v-for="(title, index) in cardTitles"
          :key="index"
          class="item"
        >
          <b-nav>
            <b-nav-item @click="golive(index)">
              {{ cardTitles[index].title }}
            </b-nav-item>
          </b-nav>
        </swiper-slide>
      </swiper>
    </b-row>
  </div>
</template>

<script>
import cardTitles from "@/constant/index";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/css/swiper.css";

export default {
  name: "NavBar",
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      cardTitles,
      swiperOption: {
        slidesPerView: 5,
        spaceBetween: 50,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        breakpoints: {
          1024: {
            slidesPerView: 8,
            spaceBetween: 20,
          },
          768: {
            slidesPerView: 4,
            spaceBetween: 20,
          },
          640: {
            slidesPerView: 2,
            spaceBetween: 20,
          },
          320: {
            slidesPerView: 1,
            spaceBetween: 10,
          },
        },
      },
    };
  },
  methods: {
    golive(index) {
      console.log(this.cardTitles[index]);
      this.$router.push(this.cardTitles[index].path).catch(() => {});
    },
  },
};
</script>

<style>
 .item:hover {
  /* border-bottom: 1px solid #0b0c0c; */
} 
.swiper-pagination {
  margin-bottom: 40px;
}
.swiper-container {
  padding-top: 40px;
}
</style>
