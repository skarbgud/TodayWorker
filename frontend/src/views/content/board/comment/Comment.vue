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
          <div class="mt-2 comment-bottom">
             <i class="far fa-clock mx-1" />{{ item.regDate }}
            <a href="#"><i class="far fa-thumbs-up mx-1 ml-1"></i>좋아요</a>
            <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
          </div>
          <hr/>
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
import CommentDetail from './CommentDetail';

export default {
  name: 'Comment',
  components: { CommentDetail },
  props: ['comments'],
  computed: {},
  data() {
    return {};
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/comment.scss';
</style>
