<template>
  <div class="mt-4">
    <b-container>
      <div v-for="(item, index) in comments" :key="index" class="mt-3 col-8">
        <div v-if="item.class === 0">
          <div class="mt-2 comment-user">{{ item.company }} | {{ item.userId }} </div>
          <div class="mt-2">{{ item.content }}</div>
          <div class="mt-2 comment-bottom">
            <b-icon class="mx-1" icon="clock" />2시간전
            <a href="#"><b-icon class="mx-1 ml-1" icon="hand-thumbs-up" />좋아요</a>
            <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />1</a>
          </div>
          <hr />
        </div>
      </div>
      <div>
        대댓글
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: ["comments"],
  computed: {},
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/comment.scss';
</style>

