<template>
  <div>
    <b-container fluid>
      <div style="flex-wrap:wrap;">
        <b-row
          align-h="center"
          class="mt-5 justify-content-center"
          cols="1"
          cols-sm="1"
          cols-md="2"
          cols-lg="2"
        >
          <!-- 인기게시글 카드 -->
          <b-col class="mb-5 mr-1" lg="8">
            <card-group :boardCategori="hotTitle"></card-group>
          </b-col>
          <!-- 나머지 카드 영역 -->
          <b-col
            class="mb-5 mr-1"
            v-for="(categori, index) in boardCategori"
            lg="4"
            :key="index"
          >
            <card-group :boardCategori="boardCategori[index]"></card-group>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import CardGroup from '@/views/components/cards/CardGroup';
import boardCategori from '@/constant/board-categori';

export default {
  name: 'FrameSection',
  components: {
    CardGroup,
  },
  data() {
    return {
      hotTitle: { title: '인기게시글', path: 'hot', emoticon: '🔥' },
      boardCategori,
    };
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/layouts/framesection.scss';
</style>
