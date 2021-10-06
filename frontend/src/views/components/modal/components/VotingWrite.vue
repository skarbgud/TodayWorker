<template>
  <div class="voting-area" v-if="showVoting">
    <p>
      * 투표 항목은 글 등록 이후에는 수정할 수 없습니다.
    </p>
    <!-- 투표 항목 (기본 2개) -->
    <el-input
      v-for="(voteItem, index) in voteList"
      :key="index"
      class="input-area"
      v-model="voteList[index]"
      @change="emitVoteList"
      placeholder="투표항목"
      clearable
    ></el-input>
    <div class="plus-vote">
      <div>
        <div class="vote-left">
          <el-button @click="plusVoteItem" size="small" plain
            >➕ 항목 추가</el-button
          >

          <el-checkbox
            class="ml-3"
            v-model="manyChecked"
            label="manyChecked"
            border
            size="small"
            >복수 투표 허용</el-checkbox
          >
        </div>
        <div class="vote-right">
          <el-button
            @click="deleteVoting"
            type="primary"
            size="small"
            icon="el-icon-delete"
            >투표 삭제</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VotingWrite',
  data() {
    return {
      showVoting: false, // 투표기능 on/off 여부
      voteList: ['', ''], //투표 항목 리스트
      manyChecked: false, // 복수 투표 가능
    };
  },
  methods: {
    // 투표버튼 클릭
    clickVoting() {
      this.showVoting = true;
    },
    // 투표 항목 버튼
    plusVoteItem() {
      this.voteList.push('');
    },
    emitVoteList() {
      console.log(this.voteList)
      this.$emit('addVoteItem', this.voteList)
    },
    // 투표 삭제
    deleteVoting() {
      // 모든 변수 초기화
      this.showVoting = false;
      this.manyChecked = false;
      this.voteList = ['', ''];
    },
  },
};
</script>

<style></style>
