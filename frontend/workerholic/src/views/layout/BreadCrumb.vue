<template lang="html">
  <el-col class="section-nav">
    <span class="root-menu">{{getTitle}} > </span>
  </el-col>
</template>

<script>
export default {
  name: 'BreadCrumb',
  data() {
    return {};
  },
  computed: {
    getTitle() {
      // return this.$store.state.title;
      if (this.$route.path === '/perf') {
        return '성능/운영';
      }
      return '설정';
    },
  },
};
</script>

<style lang="css">
.section-nav {
  padding-bottom: 1%;
}
.root-menu {
  color: gray;
}
</style>
