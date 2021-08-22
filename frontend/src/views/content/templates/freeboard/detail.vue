<template>
  <b-container>
    <board-detail :post="post" :user="user"> </board-detail>
  </b-container>
</template>

<script>
import boardDetail from "../../../components/boardDetail.vue";
export default {
  name: "FreeBoardDetail",
  components: { boardDetail },
  data() {
    return {
      post: {
        index: 1,
        title: "강원도여행 가려는데",
        content:
          "호에에에에엥ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ",
      },
      user: {
        index: 1,
        userId: "지니",
        company: "새회사",
        do: "경기도",
        si: "수원시",
        dong: "인계동",
      },
    };
  },
};
</script>

<style></style>
