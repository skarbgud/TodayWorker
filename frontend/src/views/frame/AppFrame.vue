<template>
  <b-container fluid class="p-0">
    <frame-header/>
    <frame-section/>
  </b-container>
</template>

<script>
import FrameHeader from './components/FrameHeader';
import FrameSection from './components/FrameSection';

export default {
  name: 'AppFrame',
  components: {
    FrameHeader,
    FrameSection,
  },
};
</script>

<style>
</style>
