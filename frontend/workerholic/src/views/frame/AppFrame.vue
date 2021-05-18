<template>
  <el-container class="portal-wrapper">
    <div class="portal-main-wrapper">
      <frame-header />
      <frame-section/>
    </div>
  </el-container>
</template>

<script>
import FrameHeader from './components/FrameHeader';
import FrameSection from './components/FrameSection';

export default {
  name: 'WAppFrame',
  components: {
    FrameHeader,
    FrameSection,
  },
};
</script>

<style></style>
