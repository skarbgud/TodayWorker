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
    <el-collapse v-model="activeNames" @change="handleCategori">
      <el-collapse-item name="categori">
        <!-- 선택된 카테고리  -->
        <template slot="title">
          {{ categoriName }}
        </template>
        <!-- 카테고리 목록 -->
        <b-list-group v-if="isShowCategori">
          <b-list-group-item
            v-for="(categori, index) in boardCategori"
            :key="index"
            class="d-flex justify-content-between align-items-center"
            @click="clickCategori(index)"
          >
            {{ categori.emoticon }}
            {{ categori.title }}
          </b-list-group-item>
        </b-list-group>
      </el-collapse-item>
    </el-collapse>

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
        :minRows="14"
        :maxRows="1000000"
        :placeHolder="
          '주제에 맞지 않는 글로 판단되어 다른 유저로부터 일정 수 이상의 신고를 받는 경우 글이 자동으로 숨김 처리 될 수 있습니다.'
        "
      >
      </input-textarea>
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
    </div>
    <div class="modal-footer">
      <p class="float-left">
        <!-- 사진 업로드 -->
        <camera-button @uploadImage="uploadImage"></camera-button>
        <!-- 투표기능 -->
        <span class="mr-3" @click="clickVoting()">
          <label class="input-file-button">
            🗳️
          </label>
        </span>
        <!-- 위치태그 -->
        <span class="mr-3" @click="getLocation()">
          <label class="input-file-button">
            <i class="fas fa-map-marker-alt"></i>
          </label>
        </span>
        <!-- 해시태그 기능 -->
        <span class="mr-3">
          <label class="input-file-button">
            <i class="fas fa-hashtag"></i>
          </label>
        </span>
      </p>
      <span style="float:right">
        <el-button>등록</el-button>
      </span>
    </div>
  </el-dialog>
</template>

<script>
import boardCategori from '@/constant/board-categori';
import InputTextarea from '@/views/components/input/InputTextarea';
import CameraButton from '@/views/components/button/CameraButton';

export default {
  name: 'WriteModal',
  components: { InputTextarea, CameraButton },
  data() {
    return {
      // 모달창 보이기 여부
      dialogVisible: false,
      activeNames: [],
      categoriName: '카테고리',
      boardCategori,
      isShowCategori: false,
      title: '',
      content: '',
      form: {},
      modalFull: false,
      width: 0,
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
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
  methods: {
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
    // 모달 창 닫기 => 선택된 카테고리 초기화
    close() {
      // 모달 닫기
      this.dialogVisible = false;
      // 초기화
      this.activeNames = [];
      this.categoriName = '카테고리';
    },
    // 카테고리 목록 보이기
    handleCategori() {
      this.isShowCategori = true;
    },
    // 카테고리 클릭 => 선택된 카테고리를 collapse 타이틀로
    clickCategori(index) {
      this.isShowCategori = false;
      this.activeNames = [];
      this.categoriName =
        this.boardCategori[index].emoticon + this.boardCategori[index].title;
    },
    uploadImage(files) {
      this.files = files;
    },
    fileDeleteButton(e) {
      console.log(e);
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
      // console.log(this.files);
    },
    clickVoting() {
      console.log('투표 기능');
    },
    // 위치 버튼을 클릭
    getLocation() {
      // GPS를 지원하면
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          function(position) {
            console.log('성공');
            // 좌표 (위도[latitude], 경도[longitude])
            console.log(
              position.coords.latitude + ' ' + position.coords.longitude,
            );
          },
          /* eslint-disable */
          // 접근 권한 실패  -> GeolocationPositionError {code: 1, message: "User denied Geolocation"}
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
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/registModal.scss';
</style>
