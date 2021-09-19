<template>
  <div>
    <b-container>
      <div style="justify-content-md-center" class="mt-4">
        <!-- <b-breadcrumb :items="items"/> -->
        <b-row>
          <b-col cols-sm="12" cols-md="7" cols-lg="8" cols-xl="8" class="mt-4">
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
                <i class="far fa-clock mx-1" />2시간전
                <i class="fas fa-eye mx-1"></i> 505
                <b-icon class="mx-1 ml-2" icon="chat" />38
                <div class="info-right">
                  <a href="#"><b-icon class="mx-1 ml-3" icon="bookmark"/></a>
                  <a href="#" v-b-popover.click.html="popoverMethod" ><b-icon class="mx-1 ml-3" icon="three-dots"/></a>
                </div>
              </div>
              <hr />
              <div class="mt-4 mb-4">{{ post.content }}</div>
              <div class="info-bottom">
                <a href="#"><i class="far fa-thumbs-up mx-1 mr-1" />505</a>
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
              <div class="mt-3">
              <el-tag v-for="tag in tagList" :key="tag" class="tag mx-1">{{tag}}</el-tag>
              </div>
              <div style="clear:both" />
              <hr />
            </div>
            <div class="article-comments pt-2">
              <h6>댓글 127</h6>

              <div
                class="comment-box mt-2"
                v-if="isWrite === false"
                @click="isWrite = true"
              >
                <span class="comment">댓글을 입력하세요</span>
              </div>
              <div v-else class="write-box mt-2">
                <input-textarea
                  :placeHolder="placeHolder"
                  :minRows="5"
                  :maxRows="1000000"
                ></input-textarea>
                <div class="file-preview-container">
                  <div
                    v-for="(file, index) in files"
                    :key="index"
                    class="file-preview-wrapper"
                  >
                    <div
                      class="file-close-button"
                      @click="fileDeleteButton"
                      :name="file.number"
                    >
                      x
                    </div>
                    <img :src="file.preview" />
                  </div>
                </div>
                <div class="rcmd-btn">
                  <span class="ml-3">
                    <camera-button @uploadImage="uploadImage" :fileList="files">
                    </camera-button>
                  </span>
                  <span class="float-right">
                    <b-button
                      variant="danger"
                      class="mr-3"
                      size="sm"
                      @click="isWrite = false"
                      >취소</b-button
                    >
                    <b-button variant="light" size="sm">등록</b-button>
                  </span>
                </div>
              </div>
            </div>
          </b-col>
          <div class="ml-5 d-none d-lg-none d-xl-block" />
          <b-col class="mt-4 box d-none d-lg-none d-xl-block" cols="3">
            <slot name="recommendedPost"></slot>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import InputTextarea from "../../components/input/InputTextarea.vue";
import CameraButton from "../../components/button/CameraButton.vue";

export default {
  name: "BoardDetail",
  props: ["post", "user"],
  components: { InputTextarea, CameraButton },
  computed: {},
  data() {
    return {
      isWrite: false,
      disabled: true,
      placeHolder: "댓글을 입력 해 주세요",
      files: [],
      tagList:["태그","맛집", "여행", "리스트"]
    };
  },
  methods: {
    popoverMethod() {
     return '<a href="#">' + '공유하기' + '</a>' + '<hr />'+'<a href="#">' + '신고하기' + '</a>' 
    },
    uploadImage(files) {
      console.log(files);
      this.files = files;
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute("name");
      this.files = this.files.filter((data) => data.number !== Number(name));
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/content/boardContent.scss";
</style>
