<template>
  <div class="layout">
    <el-row :gutter="20">
      <el-col :span="12" class="bg-purple">
        <div class="grid-content ">
          <i class="fas fa-align-justify"></i> 자유게시판
          <router-link to="/live"><span class="grid-more">더보기></span></router-link>
        </div>
        <el-col>목록내용</el-col>
      </el-col>
      <el-col :span="12" class="bg-purple">
        <div class="grid-content">
          <i class="fas fa-utensils"></i> 맛집노트
          <span class="grid-more">더보기></span>
        </div>
        <el-col>목록내용</el-col>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12" class="bg-purple">
        <div class="grid-content ">
          <i class="fas fa-comments"></i> 사는얘기
            <span class="grid-more">
              <el-button size="mini" v-on:click="MoreLive"> 더보기>
              </el-button>
            </span>
        </div>
        <el-col>목록내용</el-col>
      </el-col>
      <el-col :span="12" class="bg-purple">
        <div class="grid-content">
          <i class="fas fa-bed"></i> 자취이야기
          <span class="grid-more">더보기></span>
        </div>
        <el-col>목록내용</el-col>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12" class="bg-purple">
        <div class="grid-content ">
          <i class="fas fa-building"></i> 회사생활
          <span class="grid-more">더보기></span>
        </div>
        <el-col>목록내용</el-col>
      </el-col>
      <el-col :span="12" class="bg-purple">
        <div class="grid-content">
          <i class="fas fa-walking"></i> 취미생활
          <span class="grid-more">더보기></span>
        </div>
        <el-col>목록내용</el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'MainFrame',
  components: {
  },
  data() {
    return {
    };
  },
  methods: {
    MoreLive() {
      let router = this.$router;
      router.push('/live');
    }
  },
  created() {
  },
};
</script>

<style>
.layout {
  width: 100%;
  background-color: #f8f8f8;
  padding: 20px;
  height: calc(100% - 50px);
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
/* .bg-purple {
  background: #d3dce6;
} */
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  padding: 5px;
  border-bottom: 1px solid #e2e2e2;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.grid-more {
  float: right;
  margin: -4px;
}
</style>
