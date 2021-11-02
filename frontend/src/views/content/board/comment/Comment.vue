<template>
  <div class="mt-4 row">
    <b-container>
      <div v-for="(item, index) in comments" :key="index" class="mt-3 col-xl-8">
        <!--본댓글 -->
        <comment-content :item="item" :index="index" ref="index" @clickUpdateReply=clickUpdateReply></comment-content>
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
import InputTextarea from '@/views/components/input/InputTextarea.vue';
import CommentContent from './CommentContent.vue';

export default {
  name: 'Comment',
  components: { InputTextarea, CommentContent },
  props: ['comments'],
  data() {
    return {
      isEdit: false,
      placeHolder: '댓글을 남겨주세요.',
      files: [],
      replyContent: '',
    };
  },
  methods: {
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
    },
    clickUpdateReply(index) {
      this.$refs.index[index].isEdit = true;
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
