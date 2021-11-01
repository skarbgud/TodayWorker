<template>
  <div class="mt-4 row">
    <b-container>
      <div v-for="(item, index) in comments" :key="index" class="mt-3 col-xl-8">
        <!--본댓글 -->
        <div :class="{ isRecomment: item.isRecomment === true }">
          <div class="mt-2 comment-user">
            <!-- {{ item.company }} | {{ item.userId }} -->
            {{ item.writer }}
          </div>
          <div class="mt-2">{{ item.content }}</div>
          <b-row align-h="between">
            <b-col cols="4">
              <div class="mt-2 comment-bottom">
                <i class="far fa-clock mx-1" />{{
                  item.regDate | moment('from', 'now')
                }}
                <a href="#"><i class="far fa-thumbs-up mx-1 ml-1"></i>좋아요</a>
                <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
              </div>
            </b-col>
            <b-col cols="4" style="text-align: right;">
              <el-popover
                placement="right"
                width="80"
                trigger="click"
                content="popOverContent"
              >
                <el-button @click="clickUpdateReply">수정하기</el-button>
                <el-button
                  @click="clickDeleteReply(item.rno)"
                  style="margin-left:0px;margin-top:10px;"
                  >삭제하기</el-button
                >
                <b-icon slot="reference" class="mx-1 ml-3" icon="three-dots" />
              </el-popover>
            </b-col>
          </b-row>
          <hr />
        </div>
        <!-- [더보기] , 대댓글   -->
        <!-- // TODO. 대댓글 구현 -->
        <!-- <div v-if="item.reply.length !== 0">
          <comment-detail :reply-comment="item.reply"></comment-detail>
        </div>    -->
      </div>
    </b-container>
  </div>
</template>

<script>
// import CommentDetail from './CommentDetail';
import registApi from '@/api/board/reply';

export default {
  name: 'Comment',
  // components: { CommentDetail },
  props: ['comments'],
  computed: {
    setReplyParams() {
      
      return params;
    }
  },
  data() {
    return {};
  },
  methods: {
    clickUpdateReply() {

    },
    clickDeleteReply(rno) {
      const params = { 
        bno: this.$route.params.index,
        rno: rno,
      };
      registApi
        .deleteReply(params)
        .then((response) => {
          if (response.data.success) {
            // 성공시 페이지리로드
            if (response.data.data) {
              this.$router.go();
            }
          } else {
            console.log('댓글 삭제 실패');
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/comment.scss';
</style>
