<template>
  <div class="mt-4">
    <b-container>
      <div v-for="(item, index) in comments" :key="index" class="mt-3 col-8">
        <!--본댓글 -->
        <div :class="{ isRecomment: item.isRecomment === true }">
          <div class="mt-2 comment-user">
            {{ item.company }} | {{ item.userId }}
          </div>
          <div class="mt-2">{{ item.content }}</div>
          <div class="mt-2 comment-bottom">
            <b-icon class="mx-1" icon="clock" />2시간전 {{ item.isRecomment }}
            <a href="#"
              ><b-icon class="mx-1 ml-1" icon="hand-thumbs-up" />좋아요</a
            >
            <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
          </div>
          <hr />
        </div>
        <!-- 본댓글 끝 -->
        <!-- 대댓글이 3개가 넘어가면 대댓글 더보기 -->
        <!-- <div
          v-if="item.reply.length > 3"
          class="recomment-hide col-8"
          @click="clickMoreReply(item)"
        >
          + 대댓글 {{ item.reply.length - end }} 개 더 보기
          <hr />
        </div> -->
        <!-- 대댓글 영역 -->
        <div v-if="item.reply.length !== 0">
          <comment-detail
            :commentItem="item.reply"
            :ref="item.postIndex"
          ></comment-detail>
          <!-- <div
            v-for="(reply, index) in getReplyRange(item.reply, 0, end)"
            :key="index"
            :ref="item.reply.index"
            class="mt-3 col-8"
          >
            <div class="mt-2 comment-user">
              {{ reply.company }} | {{ reply.userId }}
            </div>
            <div class="mt-2">{{ reply.content }}</div>
            <div class="mt-2 comment-bottom">
              <b-icon class="mx-1" icon="clock" />2시간전
              {{ reply.isRecomment }}
              <a href="#"
                ><b-icon class="mx-1 ml-1" icon="hand-thumbs-up" />좋아요</a
              >
              <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
            </div>
            <hr />
          </div> -->
        </div>
        <!-- 대댓글 영역 끝 -->
      </div>
      <!-- 대댓글 영역 -->
      <!-- <div class="recomment-hide col-8" @click="moreComments = !moreComments">
        + 대댓글 {{ recommentSelect.length }} 개 더 보기
        <hr />
      </div> -->
      <!-- <div v-if="moreComments">
        <div v-for="(item, index) in comments" :key="index" class="mt-3 col-8">
          <div
            v-if="item.isRecomment"
            :class="{ isRecomment: item.isRecomment === true }"
          >
            <div class="mt-2 comment-user">
              {{ item.company }} | {{ item.userId }}
            </div>
            <div class="mt-2">{{ item.content }}</div>
            <div class="mt-2 comment-bottom">
              <b-icon class="mx-1" icon="clock" />2시간전 {{ item.isRecomment }}
              <a href="#"
                ><b-icon class="mx-1 ml-1" icon="hand-thumbs-up" />좋아요</a
              >
              <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
            </div>
            <hr />
          </div>
        </div>
      </div> -->
    </b-container>
  </div>
</template>

<script>
import CommentDetail from './CommentDetail.vue';

export default {
  name: 'Comment',
  components: { CommentDetail },
  props: ['comments'],
  computed: {
    recommentSelect() {
      return this.comments.filter((item) => item.isRecomment);
    },
  },
  created() {
    // this.commentSort();
  },
  data() {
    return {
      start: 0,
      end: 2,
      moreComments: false,
      comment: undefined,
      // list: [],
    };
  },
  // watch: {
  //   end: {
  //     immediate: true,
  //     handler() {
  //       this.getReplyRange();
  //     },
  //   },
  // },
  methods: {
    // getReplyRange(replyList, start, end) {
    //   return replyList.slice(start, end);
    // },
    // clickMoreReply(val) {
    //   // this.end += 10;
    //   // this.list = val.reply;
    //   console.log(this.$refs[val.postIndex][0]);
    //   this.$refs[val.postIndex][0].loadMoreData();
    // },
    // setEndData(val) {
    //   this.end = val;
    // },
    //코멘트 댓글그룹별 묶어서 정렬
    // commentSort() {
    //   this.comment = this.comments.sort(function(a, b) {
    //     return a.groupNum < b.groupNum ? -1 : a.groupNum > b.groupNum ? 1 : 0;
    //   });
    // },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/comment.scss';
</style>
