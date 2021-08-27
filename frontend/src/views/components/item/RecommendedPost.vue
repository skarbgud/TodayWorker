<template>
  <div class="ml-1 m-3">
    <div class="title">{{ currentBoardTitle }} 추천 글</div>
    <ul class="mt-3 list">
      <li v-for="(item, index) in post" :key="index" class="mt-2 list-item">
        <a href="#">{{ item.title }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
import boardCategori from '@/constant/board-categori';

export default {
  name: 'RecommendedPost',
  props: ['post'],
  computed: {
    currentBoardTitle() {
      let index = boardCategori.findIndex(
        (i) => i.path == this.$route.params.board,
      );
      return boardCategori[index].title;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/recommendedPost.scss';
</style>
