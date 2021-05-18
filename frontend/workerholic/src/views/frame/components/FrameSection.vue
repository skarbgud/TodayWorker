<template>
  <el-main>
    <el-row class="main-wrapper" type="flex" justify="space-between">
      <router-view></router-view>
    </el-row>
  </el-main>
</template>

<script>
import MainFrame from '@/views/main/MainFrame';

export default {
  name: 'FrameSection',
  components: {
    MainFrame,
  },
};
</script>

<style></style>
