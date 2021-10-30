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

    <!-- 모달 content  -->
    <el-select
      v-model="categoriName"
      placeholder="카테고리"
      class="d-flex justify-content-between align-items-center"
    >
      <el-option
        v-for="(categori, index) in boardCategori"
        :key="index"
        :label="categori.emoticon + ' ' + categori.title"
        :value="categori.path"
      >
      </el-option>
    </el-select>

    <div class="input-control">
      <!-- 제목 입력 -->
      <el-input
        size="small"
        v-model="title"
        placeholder="제목을 입력해주세요"
        class="input-area"
        maxlength="120"
      ></el-input>
      <!-- 내용 입력 -->
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
      <div v-if="isHashTag">
        <hash-tag @changeTagList="changeTagList"></hash-tag>
      </div>
      <!-- 이미지 미리보기 -->
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
          :fileList="files"
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
      <span style="float:right">
        <el-button @click="insertBoardApi">등록</el-button>
      </span>
    </div>
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

export default {
  name: 'WriteModal',
  components: { InputTextarea, CameraButton, VotingWrite, HashTag },
  data() {
    return {
      // 모달창 보이기 여부
      dialogVisible: false,
      categoriName: '',
      boardCategori,
      bno: '',
      title: '',
      content: '',
      updateContent: '',
      regDate: '',
      modalFull: false,
      width: 0,
      files: [], //업로드용 파일
      isHashTag: false,
      position: [],
      voteList: [],
      tagList: [],
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
    formData() {
      const params = {
        bno: this.bno,
        categoriName: this.categoriName,
        title: this.title,
        content: this.content,
        regDate: this.regDate,
        files: this.files,
        voteList: this.voteList,
        tagList: this.tagList,
      };
      return params;
    },
  },
  methods: {
    initData() {
      this.categoriName = '';
      this.title = '';
      this.content = '';
      this.files = [];
      this.tagList = [];
      this.voteList = [];
      this.$refs.inputTextArea.initData();
    },
    addVoteItem(voteList) {
      this.voteList = voteList;
    },
    inputContent(content) {
      this.content = content;
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
    insertBoardApi() {
      this.form = {
        categoriName: this.categoriName,
        title: this.title,
        content: this.content,
        files: this.files,
        voteList: this.voteList,
        tagList: this.tagList,
      };
      console.log(this.form);
      if (!this.updateFlag) {
        boardApi
          .insertBoard(this.formData)
          .then((response) => {
            if (response.data.success) {
              alert('등록되었습니다.');
              this.close();
              this.initData();
              // 성공하게 된다면 해당 작성된 글의 상세보기로 이동
              const url = response.data.data;
              this.$router.push(`/${url}`);
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
      this.dialogVisible = false;
    },
    uploadImage(files) {
      this.files = files;
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
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
      this.tagList = data;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/registModal.scss';
</style>
