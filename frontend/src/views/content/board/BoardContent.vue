<template>
  <b-container>
    <board-detail :post="post" :user="user"></board-detail>
    <comment :comments="comments"></comment>
  </b-container>
</template>

<script>
import BoardDetail from "@/views/content/board/BoardDetail";
import Comment from "@/views/components/cards/Comment";

export default {
  name: "BoardContent",
  components: { BoardDetail, Comment },
  data() {
    return {
      post: {
        index: 1,
        title: "강원도여행 가려는데",
        content:
          "호에에에에엥ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ",
        hashTag: {
          "1": "#여행",
          "2": "#휴가",
          "3": "#20대",
          "4": "#당일치기",
          "5": "#강원도",
          "6": "#강릉",
        },
      },
      comments: [
        {
          postIndex: 1,
          index: 1,
          userId: "jinnn",
          company: "avbb",
          content: "아진짜????",
          class: 0,
          order: 0,
          groupNum: "1",
        },
        {
          postIndex: 1,
          index: 5,
          userId: "냐미냐미",
          company: "쩝쩝회사",
          content: "대댓글????",
          class: 1,
          order: 1,
          groupNum: "1",
        },
        {
          postIndex: 1,
          index: 5,
          userId: "jiniini",
          company: "bbbb",
          content: "aasdfd",
          class: 1,
          order: 2,
          groupNum: "1",
        },
        {
          postIndex: 1,
          index: 2,
          userId: "jinnn",
          company: "avbb",
          class: 0,
          order: 0,
          content: "어떻게 생각해",
        },
        {
          postIndex: 1,
          index: 3,
          userId: "jinnn",
          company: "avbb",
          class: 0,
          order: 0,
          content: "테스트테스트",
        },
      ],
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
