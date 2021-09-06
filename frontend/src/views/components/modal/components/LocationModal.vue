<template>
  <el-dialog
    width="30%"
    title="동네 태그"
    :visible.sync="locationVisible"
    append-to-body
  >
  위치정보를 보여주는 모달입니다.
  </el-dialog>
</template>

<script>
export default {
  name: 'LocationModal',
  data() {
    return {
      locationVisible: false,
    };
  },
  methods: {
    open() {
      this.locationVisible = true;
    },
  },
};
</script>

<style></style>
