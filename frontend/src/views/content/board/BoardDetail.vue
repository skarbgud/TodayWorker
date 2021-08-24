<template>
  <b-container>
    <board-content :post="post" :user="user"></board-content>
  </b-container>
</template>

<script>
import BoardContent from './BoardContent';

export default {
  name: 'BoardDetail',
  components: { BoardContent },
  data() {
    return {
      post: {
        index: 1,
        title: '강원도여행 가려는데',
        content:
          '호에에에에엥ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ',
        hashTag: {
          '1': '#여행',
          '2': '#휴가',
          '3': '20대',
          '4': '당일치기',
          '5': '강원도',
          '6': '강릉',
        },
      },
      user: {
        index: 1,
        userId: '지니',
        company: '새회사',
        do: '경기도',
        si: '수원시',
        dong: '인계동',
      },
    };
  },
};
</script>

<style></style>
