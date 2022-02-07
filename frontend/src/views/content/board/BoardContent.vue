<template>
  <b-container>
    <board-detail :post="post" :user="user" :comments="comments">
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
      post: [],
      comments: [],
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
  created() {
    this.getBoardDetailApi();
  },
  methods: {
    getBoardDetailApi() {
      //상세 페이지 조회시 게시글 bno로 조회
      const bno = this.$route.params.index;

      boardApi
        .getBoardDetail(bno)
        .then((response) => {
          if (response.status === 200) {
            this.post = response.data;
            if (response.data.commentList != null)
            {
              this.comments = response.data.commentList;
            }
          } else {
            console.log('데이터 불러오기 실패');
          }
        })
        .catch((error) => {
          this.$message.error(error.response.data.errorMessage);
        })
    },
  },
};
</script>

<style></style>
