<template>
  <b-container>
    <board-detail :post="post" :user="user">
      <template v-slot:recommendedPost>
        <recommended-post :post="recommendPost"></recommended-post>
      </template>
    </board-detail>
    <comment :comments="comments"></comment>
  </b-container>
</template>

<script>
import BoardDetail from '@/views/content/board/BoardDetail';
import Comment from './comment/Comment';
import RecommendedPost from '../../components/item/RecommendedPost';
import boardApi from '@/api/board/index';

export default {
  name: 'BoardContent',
  components: { BoardDetail, Comment, RecommendedPost },
  created() {
    this.getBoardDetailApi();
  },
  data() {
    return {
      path: '',
      recommendPost: [
        {
          index: 1,
          postIndex: 1,
          title: '지금 인기게시글이 이거다1',
        },
        {
          index: 2,
          postIndex: 2,
          title: '지금 인기게시글이 이거다2',
        },
        {
          index: 3,
          postIndex: 3,
          title: '지금 인기게시글이 이거다3',
        },
        {
          index: 4,
          postIndex: 4,
          title: '지금 인기게시글이 이거다4',
        },
        {
          index: 5,
          postIndex: 5,
          title: '지금 인기게시글이 이거다5',
        },
      ],
      post: {
        index: 1,
        title: '',
        content: '',
        hashTag: {
          '1': '#여행',
          '2': '#휴가',
          '3': '#20대',
          '4': '#당일치기',
          '5': '#강원도',
          '6': '#강릉',
        },
      },
      comments: [
        {
          postIndex: 1,
          index: 1,
          userId: 'use1',
          company: 'avbb',
          content: '아진짜????',
          isRecomment: false,
          order: 0,
          groupNum: 1,
          reply: [
            {
              postIndex: 1,
              index: 1,
              userId: 'jinnn',
              company: 'avbb',
              isRecomment: true,
              order: 0,
              content: '1의 대댓글1',
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 2,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 2',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 3,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 3',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 4',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 5',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 6',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 7',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 8',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 9',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 10',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 11',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 12',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 13',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
            {
              postIndex: 1,
              index: 4,
              userId: '냐미냐미',
              company: '쩝쩝회사',
              content: '1의대댓글 14',
              isRecomment: true,
              order: 1,
              groupNum: 1,
              reply: [],
            },
          ],
        },
        {
          postIndex: 2,
          index: 2,
          userId: 'jiniini',
          company: 'bbbb',
          content: 'aasdfd',
          isRecomment: false,
          order: 2,
          groupNum: 2,
          reply: [
            {
              postIndex: 2,
              index: 1,
              userId: 'jinnn',
              company: '카카오',
              isRecomment: true,
              order: 0,
              content: '2-1대댓글',
              groupNum: 2,
              reply: [],
            },
            {
              postIndex: 2,
              index: 2,
              userId: 'jiniini',
              company: 'bbbb',
              content: '2-2대댓글',
              isRecomment: true,
              order: 2,
              groupNum: 2,
              reply: [],
            },
          ],
        },
        {
          postIndex: 3,
          index: 3,
          userId: 'jiniini',
          company: 'bbbb',
          content: '3번댓글',
          isRecomment: false,
          order: 2,
          groupNum: 2,
          reply: [
            {
              postIndex: 3,
              index: 1,
              userId: 'jinnn',
              company: '카카오',
              isRecomment: true,
              order: 0,
              content: '3-1 대댓글',
              groupNum: 2,
              reply: [],
            },
            {
              postIndex: 3,
              index: 2,
              userId: 'jiniini',
              company: 'bbbb',
              content: '3-2 대댓글',
              isRecomment: true,
              order: 2,
              groupNum: 2,
              reply: [],
            },
            {
              postIndex: 3,
              index: 3,
              userId: 'jiniini',
              company: 'bbbb',
              content: '3-3 대댓글',
              isRecomment: true,
              order: 2,
              groupNum: 2,
              reply: [],
            },
            {
              postIndex: 3,
              index: 4,
              userId: 'jiniini',
              company: 'bbbb',
              content: '3-4 대댓글',
              isRecomment: true,
              order: 2,
              groupNum: 2,
              reply: [],
            },
          ],
        },
        {
          postIndex: 4,
          index: 4,
          userId: 'jiniini',
          company: 'bbbb',
          content: '4번댓글',
          isRecomment: false,
          order: 2,
          groupNum: 2,
          reply: [
            {
              postIndex: 4,
              index: 1,
              userId: 'jinnn',
              company: '카카오',
              isRecomment: true,
              order: 0,
              content: '4-1대댓글',
              groupNum: 2,
              reply: [],
            },
            {
              postIndex: 4,
              index: 2,
              userId: 'jiniini',
              company: 'bbbb',
              content: '4-2대댓글',
              isRecomment: true,
              order: 2,
              groupNum: 2,
              reply: [],
            },
          ],
        },
      ],
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
  methods: {
    getBoardDetailApi() {
      //상세 페이지 조회시 게시글 bno로 조회
      const params = {
        bno: this.$route.params.index,
      };
      boardApi
        .getBoardDetail(params)
        .then((response) => {
          if (response.data.success) {
            this.post = response.data.data;
          } else {
            console.log('데이터 불러오기 실패');
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
