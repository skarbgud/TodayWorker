<template>
  <b-container>
    <board-detail :post="post" :user="user">
      <template v-slot:recommendedPost>
        <recommended-post :post="recommendPost" ></recommended-post>  
      </template>
    </board-detail>
    <comment :comments="comments"></comment>
  </b-container>
</template>

<script>
import BoardDetail from "@/views/content/board/BoardDetail";
import Comment from "@/views/components/cards/Comment";
import RecommendedPost from '../../components/item/RecommendedPost.vue';

export default {
  name: "BoardContent",
  components: { BoardDetail, Comment, RecommendedPost },
  data() {
    return { 
      path:"",
      recommendPost: [
        {
          index: 1,
          postIndex: 1,
          title: "지금 인기게시글이 이거다1",
        },
         {
          index: 2,
          postIndex: 2,
          title: "지금 인기게시글이 이거다2",
        },
         {
          index: 3,
          postIndex: 3,
          title: "지금 인기게시글이 이거다3",
        },
         {
          index: 4,
          postIndex: 4,
          title: "지금 인기게시글이 이거다4",
        },
         {
          index: 5,
          postIndex: 5,
          title: "지금 인기게시글이 이거다5",
        },
      ],
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
          isRecomment: false,
          order: 0,
          groupNum: 1,
        },
      
         {
          postIndex: 1,
          index: 5,
          userId: "jiniini",
          company: "bbbb",
          content: "aasdfd",
          isRecomment: false,
          order: 2,
          groupNum: 2,
        },
        {
          postIndex: 1,
          index: 2,
          userId: "jinnn",
          company: "avbb",
          isRecomment: true,
          order: 0,
          content: "1의 대댓글",
           groupNum: 1,
        },
         {
          postIndex: 1,
          index: 5,
          userId: "냐미냐미",
          company: "쩝쩝회사",
          content: "1의대댓글 2",
          isRecomment: true,
          order: 1,
          groupNum: 1,
        },
  
        {
          postIndex: 1,
          index: 3,
          userId: "jinnn",
          company: "카카오",
          isRecomment: true,
          order: 0,
          content: "2대댓글",
           groupNum: 2,
        },
              {
          postIndex: 1,
          index: 5,
          userId: "jiniini",
          company: "bbbb",
          content: "aasdfd",
          isRecomment: true,
          order: 2,
          groupNum: 2,
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
