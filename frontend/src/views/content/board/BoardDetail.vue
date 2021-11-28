/* eslint-disable no-undef */
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
                  {{ post.user }}
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
                <i class="far fa-clock mx-1" />{{ post.regDate | moment('YYYY-MM-DD HH:mm:ss') }}
                <i class="fas fa-eye mx-1"></i> {{ post.cnt }}
                <b-icon class="mx-1 ml-2" icon="chat" />{{ post.recomment }}
                <div class="info-right">
                  <a href="#"><b-icon class="mx-1 ml-3" icon="bookmark"/></a>
                  <!-- <a href="#" v-b-popover.click.html="popoverMethod" ><b-icon class="mx-1 ml-3" icon="three-dots"/></a> -->
                  <!-- 팝오버 -->
                  <el-popover
                    placement="right"
                    width="80"
                    trigger="click"
                    content="popOverContent"
                  >
                    <el-button @click="clickEditBoard">수정하기</el-button>
                    <el-button
                      @click="clickDeleteBoard"
                      style="margin-left:0px;margin-top:10px;"
                      >삭제하기</el-button
                    >
                    <b-icon
                      slot="reference"
                      class="mx-1 ml-3"
                      icon="three-dots"
                    />
                  </el-popover>
                </div>
              </div>
              <hr />
              <div class="mt-4 mb-4">{{ post.content }}</div>
              <div class="info-bottom">
                <a href="#"><i class="far fa-thumbs-up mx-1 mr-1" />0</a>
                <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />{{ replyCount }}</a>
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
                <img
                  src="https://storage.googleapis.com/storage.chris-chris.ai/images/story.gif"
                  title="카카오스토리로 공유하기"
                  class="sharebtn_custom"
                  @click="sendkakao"
                />
              </div>
              <div class="mt-3">
                <el-tag
                  v-for="tag in post.tagList"
                  :key="tag"
                  class="tag mx-1"
                  >{{ tag }}</el-tag
                >
              </div>
              <div style="clear:both" />
              <hr />
            </div>
            <div class="article-comments pt-2">
              <h6>댓글 {{ replyCount }}</h6>

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
                  v-on:inputContent="inputContent"
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
                    <b-button variant="danger" size="sm" @click="registReply"
                      >등록</b-button
                    >
                    <b-button
                      class="ml-2 mr-2"
                      size="sm"
                      @click="isWrite = false"
                      >취소</b-button
                    >
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
    <write-modal ref="modal"></write-modal>
  </div>
</template>

<script>
import WriteModal from '@/views/components/modal/WriteModal';
import boardApi from '@/api/board/index';
import registApi from '@/api/board/reply';
import InputTextarea from '../../components/input/InputTextarea.vue';
import CameraButton from '../../components/button/CameraButton.vue';

export default {
  head() {
    return { script: [{ src: '//developers.kakao.com/sdk/js/kakao.min.js' }] };
  },
  name: 'BoardDetail',
  props: ['post', 'user', 'comments'],
  components: { WriteModal, InputTextarea, CameraButton },
  data() {
    return {
      isWrite: false,
      disabled: true,
      placeHolder: '댓글을 입력 해 주세요',
      files: [],
      tagList: ['태그', '맛집', '여행', '리스트'],
      replyContent: '',
    };
  },
  computed: {
    replyCount() {
      return this.comments === null ? 0 : this.comments.length;
    },
    setParams() {
      const params = {
        bno: this.$route.params.index,
      };
      return params;
    },
    setReplyParams() {
      const params = {
        bno: this.$route.params.index,
        content: this.replyContent,
        // TODO. 댓글 작성자
        // writer: this.replyWriter,
      };
      return params;
    }
  },
  methods: {
    popoverMethod() {
      return (
        '<a href="#">' +
        '공유하기' +
        '</a>' +
        '<hr />' +
        '<a href="#">' +
        '신고하기' +
        '</a>'
      );
    },
    clickEditBoard() {
      this.$refs.modal.loadEditData(this.post);
      this.$refs.modal.open();
    },
    clickDeleteBoard() {
      boardApi
        .deleteBoard(this.setParams)
        .then((response) => {
          if (response.data.success) {
            // 삭제시 메인페이지로
            if (response.data.data) {
              this.$router.push('/');
            }
          } else {
            console.log('데이터 불러오기 실패');
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    uploadImage(files) {
      console.log(files);
      this.files = files;
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
    },
    sendkakao: function() {
      // eslint-disable-next-line no-undef
      Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: this.post.title,
          description: this.post.content,
          imageUrl: 'http://localhost:8080/test.png',
          link: {
            mobileWebUrl: 'http://localhost:8080',
            webUrl: 'http://localhost:8080',
          },
        },
      });
    },
    // 댓글 작성 내용
    inputContent(content) {
      this.replyContent = content;
    },
    registReply() {
      registApi
        .registReply(this.setReplyParams)
        .then((response) => {
          if (response.data.success) {
            // 성공시 페이지리로드
            if (response.data.data) {
              this.$router.go();
            }
          } else {
            console.log('댓글 등록 실패');
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
@import '@/assets/scss/pages/content/boardContent.scss';
</style>
