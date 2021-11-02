<template>
  <div class="mt-4 row">
    <b-container>
      <div v-for="(item, index) in comments" :key="index" class="mt-3 col-xl-8">
        <!--본댓글 -->
        <comment-content
          :item="item"
          :index="index"
          ref="index"
          @clickUpdateReply="clickUpdateReply"
        ></comment-content>
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
import CommentContent from './CommentContent.vue';

export default {
  name: 'Comment',
  components: { CommentContent },
  props: ['comments'],
  data() {
    return {};
  },
  methods: {
    clickUpdateReply(index) {
      this.$refs.index[index].isEdit = true;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/comment.scss';
</style>
