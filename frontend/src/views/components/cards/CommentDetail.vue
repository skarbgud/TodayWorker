<template>
  <div>
    <div
      v-if="commentItem.length > 3 && totalReplyCount > 0"
      class="recomment-hide col-8"
      @click="clickMoreReply()"
    >
      + 대댓글 {{ totalReplyCount }} 개 더 보기
      <hr />
    </div>
    <div
      v-for="(reply, index) in getReplyRange(commentItem, 0, end)"
      :key="index"
      class="mt-3 col-8"
    >
      <div class="mt-2 comment-user">
        {{ reply.company }} | {{ reply.userId }}
      </div>
      <div class="mt-2">{{ reply.content }}</div>
      <div class="mt-2 comment-bottom">
        <b-icon class="mx-1" icon="clock" />2시간전
        {{ reply.isRecomment }}
        <a href="#"><b-icon class="mx-1 ml-1" icon="hand-thumbs-up" />좋아요</a>
        <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
      </div>
      <hr />
    </div>
  </div>
</template>

<script>
export default {
  name: 'CommentDetail',
  props: ['commentItem'],
  data() {
    return {
      start: 0,
      end: 3,
      totalReplyCount: this.commentItem.length - 3,
    };
  },
  methods: {
    clickMoreReply() {
      // this.end += 10;
      // this.list = val.reply;
      // console.log(this.$refs[val.postIndex][0]);
      this.loadMoreData();
    },
    getReplyRange(replyList, start, end) {
      return replyList.slice(start, end);
    },
    loadMoreData() {
      if (this.end < this.commentItem.length && this.totalReplyCount > 0) {
        this.end += 10;
        this.totalReplyCount -= 10;
        // if(this.end + 10 > this.commentItem.length)
        // {
        //   console.log(this.totalReplyCount);
        // }
        // this.$emit('loadData', this.end);
      }
    },
  },
};
</script>

<style></style>
