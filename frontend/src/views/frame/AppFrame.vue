<template>
  <b-container fluid class="p-0">
    <frame-header/>
    <router-view></router-view>
  </b-container>
</template>

<script>
import FrameHeader from './components/FrameHeader';

export default {
  name: 'AppFrame',
  components: {
    FrameHeader,
  },
};
</script>

<style>
</style>
