<template>
  <el-dialog
    :visible.sync="dialogVisible"
    width="35%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :fullscreen="modalFull"
    @close="close"
  >
    <span slot="title">
      <span class="write-modal-title">
        글쓰기
      </span>
    </span>

    <el-form :model="formData" :rules="rules" ref="formData">
      <!-- 모달 content  -->
      <div class="input-control">
        <el-form-item required prop="categoriName">
          <el-select
            v-model="formData.categoriName"
            placeholder="카테고리"
            class="d-flex justify-content-between align-items-center"
            style="padding: 3px;"
          >
            <el-option
              v-for="(categori, index) in boardCategori"
              :key="index"
              :label="categori.emoticon + ' ' + categori.title"
              :value="categori.path"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </div>

      <div class="input-control">
        <!-- 제목 입력 -->
        <el-form-item required prop="title">
          <el-input
            size="small"
            v-model="formData.title"
            placeholder="제목을 입력해주세요"
            class="input-area"
            maxlength="120"
          ></el-input>
        </el-form-item>
        <!-- 내용 입력 -->
        <el-form-item required prop="content">
          <input-textarea
            :updateContent="updateContent"
            :minRows="14"
            :maxRows="1000000"
            :placeHolder="
              '주제에 맞지 않는 글로 판단되어 다른 유저로부터 일정 수 이상의 신고를 받는 경우 글이 자동으로 숨김 처리 될 수 있습니다.'
            "
            v-on:inputContent="inputContent"
            ref="inputTextArea"
          >
          </input-textarea>
        </el-form-item>
        <div v-if="isHashTag">
          <hash-tag @changeTagList="changeTagList"></hash-tag>
        </div>
        <!-- 이미지 미리보기 -->
        <div class="file-preview-container">
          <div
            v-for="(file, index) in formData.files"
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
        <!-- 투표 영역 -->
        <voting-write ref="votingComponent"></voting-write>
        <!-- 위치 팝업 -->
        <!-- <location-modal ref="locationModal" :position="position"></location-modal> -->
      </div>
      <div class="modal-footer">
        <p class="float-left">
          <!-- 사진 업로드 -->
          <camera-button
            @uploadImage="uploadImage"
            :fileList="formData.files"
          ></camera-button>
          <!-- 투표기능 -->
          <span class="mr-3" @click="clickVoting()">
            <label class="input-file-button">
              🗳️
            </label>
          </span>
          <!-- 위치태그 -->
          <!-- <span class="mr-3" @click="getLocation()">
          <label class="input-file-button">
            <i class="fas fa-map-marker-alt"></i>
          </label>
        </span> -->
          <!-- 해시태그 기능 -->
          <span class="mr-3" @click="isHashTag = !isHashTag">
            <label class="input-file-button">
              <i class="fas fa-hashtag"></i>
            </label>
          </span>
        </p>
        <el-form-item>
          <span style="float:right">
            <el-button @click="insertBoardApi('formData')">등록</el-button>
          </span>
        </el-form-item>
      </div>
    </el-form>
  </el-dialog>
</template>

<script>
import boardCategori from '@/constant/board-categori';
import InputTextarea from '@/views/components/input/InputTextarea';
import CameraButton from '@/views/components/button/CameraButton';
import HashTag from '../item/HashTag.vue';
import VotingWrite from './components/VotingWrite';
import boardApi from '@/api/board/index';
// import LocationModal from './components/LocationModal';
import { mapGetters } from 'vuex';

export default {
  name: 'WriteModal',
  components: { InputTextarea, CameraButton, VotingWrite, HashTag },
  data() {
    return {
      // 모달창 보이기 여부
      dialogVisible: false,
      // categoriName: '',
      boardCategori,
      // bno: '',
      // title: '',
      // content: '',
      updateContent: '',
      // regDate: '',
      modalFull: false,
      width: 0,
      // files: [], //업로드용 파일
      isHashTag: false,
      // position: [],
      // voteList: [],
      // tagList: [],
      formData: {
        categoriName: '',
        title: '',
        content: '',
        files: [],
        voteList: [],
        tagList: [],
        user: '',
      },
      rules: {
        categoriName: [
          {
            required: true,
            message: '카테고리를 선택해 주새요.',
            trigger: 'blur',
          },
        ],
        title: [
          {
            required: true,
            message: '제목을 입력해 주세요.',
            trigger: 'blur',
          },
        ],
        content: [
          {
            required: true,
            message: '내용을 입력해 주세요.',
            trigger: 'blur',
          },
        ],
      },
      updateFlag: false,
    };
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
  watch: {
    width: {
      immediate: true,
      handler() {
        this.handleResize();
      },
    },
  },
  computed: {
    ...mapGetters(['getEmail']),
    // formData() {
    //   const params = {
    //     bno: this.bno,
    //     categoriName: this.categoriName,
    //     title: this.title,
    //     content: this.content,
    //     regDate: this.regDate,
    //     files: this.files,
    //     voteList: this.voteList,
    //     tagList: this.tagList,
    //   };
    //   return params;
    // },
  },
  methods: {
    initData() {
      this.$refs.formData.resetFields();
      this.$refs.inputTextArea.initData();
    },
    addVoteItem(voteList) {
      this.formData.voteList = voteList;
    },
    inputContent(content) {
      this.formData.content = content;
    },
    loadEditData(post) {
      console.log(post);
      this.bno = post.bno;
      this.categoriName = post.categoriName;
      this.title = post.title;
      this.updateContent = post.content;
      // TODO. FILE바인딩
      // this.files = post.files;
      this.voteList = post.voteList;
      this.tagList = post.tagList;
      this.regDate = post.regDate;

      // updateFlag => 수정창인지 구분위해
      this.updateFlag = true;
    },
    insertBoardApi(formName) {
      this.formData.user = this.getEmail;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.updateFlag) {
            boardApi
              .insertBoard(this.formData)
              .then((response) => {
                if (response.data.success) {
                  alert('등록되었습니다.');
                  this.close();
                  // 성공하게 된다면 해당 작성된 글의 상세보기로 이동
                  const url = response.data.data;
                  this.$router.push(`/${url}`);
                  this.$router.go();
                } else {
                  console.log('등록 실패하였습니다.');
                }
              })
              .catch(function(error) {
                console.log(error);
              });
          } else {
            boardApi
              .updateBoard(this.formData)
              .then((response) => {
                if (response.data.success) {
                  alert('수정되었습니다.');
                  this.close();
                  // 성공하게 된다면 해당 작성된 글의 상세보기로 이동
                  const url = response.data.data;
                  this.$router.push(`/${url}`);
                  this.$router.go();
                } else {
                  console.log('등록 실패하였습니다.');
                }
              })
              .catch(function(error) {
                console.log(error);
              });
          }
        }
      });
    },
    // 반응형을 위한 사이즈
    handleResize() {
      this.width = window.innerWidth;
      if (this.width < 950) {
        this.modalFull = true;
      } else {
        this.modalFull = false;
      }
    },
    // 모달창 열기
    open() {
      this.dialogVisible = true;
    },
    // 모달 창 닫기
    close() {
      this.initData();
      this.dialogVisible = false;
    },
    uploadImage(files) {
      this.formData.files = files;
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.formData.files = this.formData.files.filter(
        (data) => data.number !== Number(name),
      );
    },
    // 투표버튼 클릭
    clickVoting() {
      this.$refs.votingComponent.clickVoting();
    },
    // 위치 버튼을 클릭
    getLocation() {
      // GPS를 지원하면
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            this.$refs.locationModal.open();
            console.log('성공');
            // 좌표 (위도[latitude], 경도[longitude])
            this.position = {
              len: position.coords.latitude,
              ren: position.coords.longitude,
            };
            console.log(
              position.coords.latitude + ' ' + position.coords.longitude,
            );
          },
          function(error) {
            alert('위치 권한을 허용해주세요');
            console.log('위치 접근 권한 실패');
            console.error(error);
          },
          {
            enableHighAccuracy: false,
            maximumAge: 0,
            timeout: Infinity,
          },
        );
      }
      // 위치 정보를 미지원
      else {
        navigator.geolocation.watchPosition();
        alert('위치 정보를 지원하지 않습니다');
      }
    },
    // 해시 태그 버튼을 클릭
    clickHashTag() {
      console.log('해시태그');
    },
    changeTagList(data) {
      this.formData.tagList = data;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/registModal.scss';
</style>
