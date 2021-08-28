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
      </div>
      <div class="recomment-hide" @click="moreComments = !moreComments">
        + 대댓글 {{ recommentSelect.length }} 개 더 보기
        <hr />
      </div>
      <div v-if="moreComments">
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
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: ["comments"],
  computed: {
    recommentSelect() {
      return this.comments.filter((item) => item.isRecomment);
    },
  },
  created() {
    this.commentSort();
  },
  data() {
    return {
      moreComments: false,
      comment: undefined,
    };
  },
  methods: {
    //코멘트 댓글그룹별 묶어서 정렬
    commentSort() {
      this.comment = this.comments.sort(function(a, b) {
        return a.groupNum < b.groupNum ? -1 : a.groupNum > b.groupNum ? 1 : 0;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/scss/components/comment.scss";
</style>
