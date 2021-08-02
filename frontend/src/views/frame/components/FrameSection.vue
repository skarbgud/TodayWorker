<template>
  <div>
    <b-container fluid>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="1"
        cols-lg="2"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[0]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[1]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[2]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[3]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[4]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[5]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[6]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[7]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[8]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[9]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[10]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[11]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[12]"></card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5 mb-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[13]"></card-group>
        </b-col>
        <b-col class="mb-5">
          <card-group :cardTitle="cardTitles[14]"></card-group>
        </b-col>
      </b-row>
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
      cardTitles: [
        '인기게시글',
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
