<template>
  <div>
    <b-card
      :title="post.title"
      class="card-link pt-3 pb-2"
      @click="goDetailRouter()"
    >
      <b-card-text class="mt-4 mb-4">
        <b-link class="card-link overtext">
          {{ post.content }}
        </b-link>
      </b-card-text>

      <!--유저 정보-->
      <div class="user-info">
        <a href="#" class="card-link">새회사</a>
        <b-link href="#" class="card-link">{{post.writer}}</b-link><br/>
      </div>

      <!-- 조회수, 좋아요, 댓글 갯수-->
      <div class="icon-color mt-1">
        <b-icon class="ml-0 mx-2" icon="hand-index-thumb" />{{post.cnt}}
        <b-icon class="mx-2 ml-3 " icon="hand-thumbs-up" />좋아요
        <b-icon class="mx-2 ml-3 " icon="chat" />{{post.recomment}}
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: 'CardList',
  props: {
    post: {
      type: Object,
    },
  },
  data() {
    return {};
  },
  computed: {},
  methods: {
    // TODO. 상세보기 라우터 구현
    goDetailRouter() {
      console.log(this.$route.path);
      //받은데이터 post의 bno로 상세페이지 params 설정
      this.$router.push(`${this.$route.path}/${this.post.bno}`);
    },
  },
};
</script>

<style>
.icon-color {
  color: rgb(145, 141, 141);
  font-size: 14px;
}
a {
  color: black;
}
.user-info {
  font-size: 14px;
}
.overtext {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  height: 40px;
  line-height: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
