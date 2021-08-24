<template>
  <div>
    <b-container>
      <div style="justify-content-md-center" class="mt-4">
        <!-- <b-breadcrumb :items="items"/> -->
        <b-row>
          <b-col cols="8" class="mt-4">
            <h2>{{ post.title }}</h2>
            <div class="content">
              <div class="userbox mt-3">
                <div class="user-info  mr-2">
                  {{ user.do }}
                  {{ user.si }} |
                  {{ user.userId }}
                  {{ user.company }}
                </div>
                <div>
                  <a href="#">
                    <b-icon
                      icon="bell-fill"
                      class="rounded-circle bg-primary  p-1"
                      variant="light"
                  /></a>
                </div>
              </div>
              <div class="post-info mt-1">
                <b-icon class="mx-1" icon="clock" />2시간전
                <b-icon class=" ml-2 " icon="hand-index-thumb" /> 505
                <b-icon class="mx-1 ml-2" icon="chat" />38
                <div class="info-right">
                  <a href="#"><b-icon class="mx-1 ml-3" icon="bookmark"/></a>
                  <a href="#"><b-icon class="mx-1 ml-3" icon="three-dots"/></a>
                </div>
              </div>
              <hr />
              <div class="mt-4 mb-4">{{ post.content }}</div>
              <div class="info-bottom">
                <a href="#"
                  ><b-icon class="mx-1 ml-2" icon="hand-thumbs-up" />505</a
                >
                <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />38</a>
                <a href="">
                  <img
                    src="https://storage.googleapis.com/storage.chris-chris.ai/images/facebook.gif"
                    title="페이스북으로 공유하기"
                    class="sharebtn_custom"
                /></a>
                <a href=""
                  ><img
                    src="https://storage.googleapis.com/storage.chris-chris.ai/images/twitter.gif"
                    title="트위터로 공유하기"
                    class="sharebtn_custom"
                /></a>
                <a href=""
                  ><img
                    src="https://storage.googleapis.com/storage.chris-chris.ai/images/story.gif"
                    title="카카오스토리로 공유하기"
                    class="sharebtn_custom"
                /></a>
              </div>
              <hash-tag :hashTag="post.hashTag" />
              <div style="clear:both" />
              <hr />
            </div>
            <div class="article-comments pt-2">
              <h6>댓글 127</h6>
              <b-link>
                <div class="comment-box mt-2">
                  <b-icon icon="camera" font-scale="2"></b-icon>
                  <span class="comment">댓글을 입력하세요</span>
                </div>
              </b-link>
            </div>
          </b-col>
          <b-col cols="4" class="mt-4 box"> </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import hashTag from '@/views/components/item/hashTag.vue';

export default {
  name: 'BoardDetail',
  props: ['post', 'user'],
  components: { hashTag },
  computed: {},
  methods: {},
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/pages/content/boardDetail.scss';
</style>
