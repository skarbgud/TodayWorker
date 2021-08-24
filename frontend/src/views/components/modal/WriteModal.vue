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
            v-for="(cardTitle, index) in cardTitles"
            :key="index"
            class="d-flex justify-content-between align-items-center"
            @click="clickCategori(index)"
          >
            {{ cardTitle.emoticon }}
            {{ cardTitle.title }}
          </b-list-group-item>
        </b-list-group>
      </el-collapse-item>
    </el-collapse>
    <textarea></textarea>
  </b-modal>
</template>

<script>
import cardTitles from '@/constant/index';

export default {
  name: 'LoginModal',
  data() {
    return {
      modalShow: false,
      activeNames: [],
      categoriName: '카테고리',
      cardTitles,
      isShowCategori: false,
    };
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
      this.categoriName = this.cardTitles[index].title;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/writemodal.scss';
</style>
