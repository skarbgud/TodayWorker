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
import boardApi from '@/api/board/index';

export default {
  name: 'SearchForm',
  data() {
    return {
      search: '',
    };
  },
  computed: {
    setParams() {
      const params = {
        content: '',
        paging: {
          fromIndex: 0,
          pageSize: 10,
        },
      };
      return params;
    },
  },
  methods: {
    submit(search) {
      this.setParams.content = search;
      boardApi
        .searchBoard(this.setParams)
        .then((response) => {
          if (response.status === 200) {
            this.$store.dispatch("POST", response.data);
          } else {
            console.log('데이터 불러오기 실패');
          }
        })
        .catch((error) => {
          this.$message.error(error.response.data.errorMessage);
        });
    },
    reset() {
      //검색 초기화 버튼(claerable)
      this.search = '';
    },
  },
};
</script>

<style></style>
