<template>
  <b-modal
    id="write-modal"
    size="lg"
    centered
    scrollable
    no-close-on-esc
    no-close-on-backdrop
  >
    <!-- 모달 header -->
    <template #modal-header="{ close }">
      <b-button variant="light" size="sm">
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
      <el-collapse-item title="카테고리" name="categori">
        <!-- 카테고리 목록 -->
        <b-list-group>
          <b-list-group-item variant="light" v-for="(cardTitle, index) in cardTitles" :key="index">
            {{ cardTitle.emoticon }} {{ cardTitle.title }}
          </b-list-group-item>
        </b-list-group>
      </el-collapse-item>
    </el-collapse>
  </b-modal>
</template>

<script>
import cardTitles from '@/constant/index';

export default {
  name: 'LoginModal',
  data() {
    return {
      activeNames: [],
      cardTitles,
    };
  },
  methods: {
    handleCategori(val) {
      console.log(val);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/writemodal.scss';
</style>
