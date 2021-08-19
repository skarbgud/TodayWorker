<template>
  <el-input
    v-model="search"
    placeholder="검색"
    prefix-icon="el-icon-search"
    size="small"
    v-on:keyup.enter.native="submit(search)"
  >
    <!-- 클리어 아이콘 -->
    <i
      slot="suffix"
      class="el-input__icon el-icon-circle-close el-input__clear"
      @click="reset"
      v-show="search !== ''"
    ></i>
  </el-input>
</template>

<script>
export default {
  name: 'SearchForm',
  data() {
    return {
      search: '',
    };
  },
  methods: {
    submit(search) {
      console.log(`${search}검색`);
    },
    reset() {
      //검색 초기화 버튼(claerable)
      this.search = '';
    },
  },
};
</script>

<style></style>
