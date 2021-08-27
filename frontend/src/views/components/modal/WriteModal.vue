<template>
  <b-modal
    id="write-modal"
    size="lg"
    centered
    scrollable
    no-close-on-esc
    no-close-on-backdrop
    v-model="modalShow"
  >
    <!-- 모달 header -->
    <template #modal-header>
      <b-button variant="light" size="sm" class="post-button">
        등록
      </b-button>
      <div class="title-wraper">
        <span class="mx-auto modal-title text-center write-modal-title">
          글쓰기
        </span>
      </div>
      <button type="button" aria-label="Close" class="close" @click="close()">
        ×
      </button>
    </template>

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
      <b-input
        class="mt-3 input-area"
        v-model="title"
        autocomplete="off"
        placeholder="제목을 입력해주세요"
      ></b-input>
      <!-- 내용 입력 -->
      <text-area
        :rows="14"
        :maxRow="10000000"
        :contentPlaceholder="
          '주제에 맞지 않는 글로 판단되어 다른 유저로부터 일정 수 이상의 신고를 받는 경우 글이 자동으로 숨김 처리 될 수 있습니다.'
        "
      ></text-area>
    </div>
    <!-- 모달 하단 -->
    <template #modal-footer>
      <div class="w-100">
        <p class="float-left">
          <!-- 사진 업로드 -->
          <span class="mr-3" @click="uploadImage()">
            <label class="input-file-button" for="input-file">
              📷
            </label>
            <input
              type="file"
              id="input-file"
              style="display:none;"
              accept="image/jiff, image/pjpeg, image/jpeg, image/pjp, image/jpg, image/png, image/gif, image/tiff, image/tif"
            />
          </span>
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
      </div>
    </template>
  </b-modal>
</template>

<script>
import boardCategori from '@/constant/board-categori';
import TextArea from '../input/TextArea.vue';

export default {
  components: { TextArea },
  name: 'WriteModal',
  data() {
    return {
      modalShow: false,
      activeNames: [],
      categoriName: '카테고리',
      boardCategori,
      isShowCategori: false,
      title: '',
      content: '',
    };
  },
  watch: {
    title: {
      immediate: true,
      handler() {
        if (this.content !== null) {
          this.title == '';
        }
      },
    },
    content: {
      immediate: true,
      handler() {
        if (this.content !== null) {
          this.content == '';
        }
      },
    },
  },
  methods: {
    // 모달 창 닫기 => 선택된 카테고리 초기화
    close() {
      this.modalShow = false;
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
    uploadImage() {
      console.log('사진 업로드');
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
@import '@/assets/scss/components/writemodal.scss';
</style>
