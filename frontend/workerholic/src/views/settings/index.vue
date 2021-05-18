<template>
  <!-- 설정 화면 -->
  <div class="layout">
    <bread-crumb></bread-crumb>

    <el-row :gutter="10" style="height: 100%;">
      <el-col :span="4" style="height: calc(100% - 30px);">
        <search-service></search-service>
      </el-col>

      <el-col :span="20">
        <el-row>
          <tree-label></tree-label>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BreadCrumb from '@/views/layout/BreadCrumb';
import SearchService from '@/views/layout/SearchService';
import TreeLabel from '@/views/layout/TreeLabel';

export default {
  name: 'WSettings',
  components: {
    BreadCrumb,
    SearchService,
    TreeLabel,
  },
  data() {
    return {};
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
</style>
