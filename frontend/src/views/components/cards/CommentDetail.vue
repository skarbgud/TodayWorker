<template>
  <div>
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
      end: 2,
    };
  },
  methods: {
    getReplyRange(replyList, start, end) {
      return replyList.slice(start, end);
    },
    loadMoreData() {
      if (this.end < this.commentItem.length) {
        this.end += 10;
        this.$emit('loadData', this.end);
      }
    },
  },
};
</script>

<style></style>
