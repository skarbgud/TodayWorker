<template>
  <el-header class="main-header">
    <el-row class="nav-wrapper" type="flex" justify="space-between">
      <h1>WorkerHolic</h1>
      <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1">게시판</el-menu-item>
          <el-menu-item index="2">시간표</el-menu-item>
          <el-menu-item index="3">연봉 계산기</el-menu-item>
          <el-menu-item index="4">친구</el-menu-item>
        </el-menu>
      </div>
      <div class="state-wrapper">
        <el-button class="text-round" style="margin-left: 10px;" round>로그인</el-button>
        <el-button class="text-round" style="background-color: #C62917; color:white" round>회원가입</el-button>
      </div>
    </el-row>
  </el-header>
</template>

<script>
export default {
  name: 'FrameHeader',
  data() {
    return {
      activeIndex: '1',
    };
  },
  methods: {
    handleSelect(key, keyPath) 
    {
      console.log(key, keyPath);
    }
  }
};
</script>

<style scoped>
.el-menu--horizontal > .el-menu-item
{
  float: left;
  height: 54px;
  line-height: 60px;
  margin: 0; 
}
</style>
