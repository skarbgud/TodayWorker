<template>
  <!-- <el-pagination style="text-align: right" @current-change="handleCurrentChange"
   :page-size="pageSize" layout="total, prev, pager, next, jumper" :total="totalCount" :params.sync="params">
  </el-pagination> -->
  <el-pagination class="table-paging" background layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize"
  :total="total" :params.sync="params"></el-pagination>
</template>

<script>
export default {
  name: 'w-paging-toolbar',
  props: ['total', 'pageSize', 'params'],
  data() {
    return { page: 1 };
  },
  computed: {
    start() {
      return (this.page - 1) * this.pageSize;
    },
  },
  methods: {
    handleCurrentChange(page) {
      this.page = page;
      this.params = Object.assign(this.params, this.getPagingParams());
      this.$emit('load');
    },
    getPagingParams() {
      return {
        page: this.page,
        start: this.start,
        limit: this.pageSize,
      };
    },
  },
};
</script>
