<template>
  <div>
    <b-container fluid>
      <div style="display:flex; flex-wrap:wrap;">
        <b-row
          align-h="center"
          class="mt-5 justify-content-center"
          cols="1"
          cols-sm="1"
          cols-md="2"
          cols-lg="2"
        >
          <b-col class="mb-5 mr-1" lg="8">
            <card-group :cardTitle="hotTitle"></card-group>
          </b-col>
          <b-col
            class="mb-5 mr-1"
            v-for="title in cardTitles"
            lg="4"
            :key="title"
          >
            <card-group :cardTitle="title"></card-group>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import CardGroup from '../card';

export default {
  name: 'FrameSection',
  components: {
    CardGroup,
  },
  data() {
    return {
      hotTitle: '인기게시글',
      cardTitles: [
        '자유게시판',
        '비밀게시판',
        '사는얘기',
        '이직·커리어',
        '회사생활',
        '취미생활',
        '시사·이슈',
        '정보게시판',
        '커피타임',
        '홍보게시판',
        '맛집노트',
        '중고거래',
        '동호회',
        '유머',
      ],
    };
  },
};
</script>

<style>
.cols-sm {
  margin-top: 1;
}
</style>
