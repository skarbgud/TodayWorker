<template>
  <b-card-group deck>
    <b-card no-body header-tag="header" border-variant="light">
      <template #header>
        <div class="d-flex justify-content-between align-items-center py-1">
          {{ boardCategori.emoticon }} {{ boardCategori.title }}
          <div class="d-flex align-items-center" @click="golive()">
            더보기 <i class="fas fa-angle-right"></i>
          </div>
        </div>
      </template>
      <b-list-group flush>
        <b-list-group-item
          class="d-flex justify-content-between align-items-center py-1"
          >회사에 누가 홀터넥 입고 오면 어떨고같아?
          <div class="d-flex align-items-center">
            <b-icon class="mx-2" icon="hand-thumbs-up" />12<b-icon
              class="mx-2 ml-3"
              icon="chat"
            />10
          </div></b-list-group-item
        >
        <b-list-group-item
          class="d-flex justify-content-between align-items-center py-1"
          >친구 아들 곧 출산인데 유모차 사주고 싶은데
          <div class="d-flex align-items-center">
            <b-icon class="mx-2" icon="hand-thumbs-up" />23<b-icon
              class="mx-2 ml-3"
              icon="chat"
            />34
          </div></b-list-group-item
        >
        <b-list-group-item
          class="d-flex justify-content-between align-items-center py-1"
          >농협은행은 성과급 없나요
          <div class="d-flex align-items-center">
            <b-icon class="mx-2" icon="hand-thumbs-up" />18<b-icon
              class="mx-2 ml-3"
              icon="chat"
            />12
          </div></b-list-group-item
        >
         <b-list-group-item
          class="d-flex justify-content-between align-items-center py-1"
          >헬스 2년만에 다시 시작합니다.. 같이 달리실 분
          <div class="d-flex align-items-center">
            <b-icon class="mx-2" icon="hand-thumbs-up" />18<b-icon
              class="mx-2 ml-3"
              icon="chat"
            />12
          </div></b-list-group-item
        >
        <b-list-group-item
          class="d-flex justify-content-between align-items-center py-1"
          >나 생산직 계약직인데 다른데 붙었는데 옮기는게 낫지?
          <div class="d-flex align-items-center">
            <b-icon class="mx-2" icon="hand-thumbs-up" />18<b-icon
              class="mx-2 ml-3"
              icon="chat"
            />12
          </div></b-list-group-item
        >
      </b-list-group>
    </b-card>
  </b-card-group>
</template>

<script>
export default {
  name: 'CardGroup',
  props: {
    boardCategori: {
      type: Object,
    },
  },
  data() {
    return {
      golive() {
        this.$router.push(this.boardCategori.path);
      },
    };
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/cardgroup.scss';
</style>
