<template>
  <div :class="{ isRecomment: item.isRecomment === true }">
    <div class="mt-2 comment-user">
      <!-- {{ item.company }} | {{ item.userId }} -->
      {{ item.user }}
    </div>
    <div v-if="!isEdit" :ref="index">
      <div class="mt-2">{{ item.content }}</div>
      <b-row align-h="between">
        <b-col cols="4">
          <div class="mt-2 comment-bottom">
            <i class="far fa-clock mx-1" />{{
              item.regDate | moment('from', 'now')
            }}
            <a href="#"><i class="far fa-thumbs-up mx-1 ml-1"></i>좋아요</a>
            <a href="#"><b-icon class="mx-1 ml-2" icon="chat" />0</a>
          </div>
        </b-col>
        <b-col cols="4" style="text-align: right;">
          <el-popover
            placement="right"
            width="80"
            trigger="click"
            content="popOverContent"
          >
            <el-button @click="clickUpdateReply(index)">수정하기</el-button>
            <el-button
              @click="clickDeleteReply(item.rno)"
              style="margin-left:0px;margin-top:10px;"
              >삭제하기</el-button
            >
            <b-icon slot="reference" class="mx-1 ml-3" icon="three-dots" />
          </el-popover>
        </b-col>
      </b-row>
      <hr />
    </div>
    <div v-else class="write-box mt-2">
      <input-textarea
        :placeHolder="placeHolder"
        :minRows="5"
        :maxRows="1000000"
        :updateContent="item.content"
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
          <b-button variant="danger" size="sm" @click="clickEditReplyButton"
            >등록</b-button
          >
          <b-button class="ml-2 mr-2" size="sm" @click="isWrite = false"
            >취소</b-button
          >
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import registApi from '@/api/board/reply';
import InputTextarea from '@/views/components/input/InputTextarea.vue';
import CameraButton from '@/views/components/button/CameraButton.vue';

export default {
  name: 'CommentContent',
  components: { InputTextarea, CameraButton },
  props: ['item', 'index'],
  data() {
    return {
      isEdit: false,
      placeHolder: '댓글을 남겨주세요.',
      files: [],
      replyContent: '',
    };
  },
  computed: {
    setParams() {
      const params = {
        bno: this.item.bno,
        rno: this.item.rno,
        content: this.item.content,
        user: this.item.user,
      };
      return params;
    },
  },
  methods: {
    inputContent(content) {
      this.setParams.content = content;
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
    },
    clickUpdateReply(index) {
      this.$emit('clickUpdateReply', index);
    },
    clickEditReplyButton() {
      registApi
        .updateReply(this.setParams)
        .then((response) => {
          if (response.data.success) {
            // 성공시 페이지리로드
            if (response.data.data) {
              this.$router.go();
            }
          } else {
            console.log('댓글 수정 실패');
          }
        })
        .catch(function(error) {
          console.log(error);
        });
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
