<template>
  <!-- 서비스 검색  -->
  <div class="search-service">
    <el-input placeholder="서비스검색" v-model="filterText"> <i class="el-icon-search el-input__icon" slot="suffix"> </i> </el-input>

    <el-tree node-key="tempTreeKey" @node-click="handleNodeClick" :default-expanded-keys="['1']" class="filter-tree" :data="data" :props="defaultProps" :filter-node-method="filterNode" ref="tree"> </el-tree>
  </div>
</template>

<script>
import Constant from '@/utils/constant';
const axios = require('axios');

export default {
  name: 'SearchService',
  data() {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'children',
        label: 'label',
      },
    };
  },
  created() {
    let treeKey = this.$router.params;
    // this.$router.push(`${this.router.path}/${treeKey}`)
    // ,
    axios
      .get('http://192.168.143.115:8080/modules/common/target/tree.do', {
        params: {
          viewType: 'LOGICAL',
          treeType: 'STATIC',
          managetypeId: 'NMS',
          managetypeKey: 'ND',
          tempTreeKey: 'root',
        },
      })
      .then((response) => {
        this.data = response.data.children;
      });
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    handleNodeClick(data, node, vuecomponent) {
      console.log('data:', data, '\n', 'node:', node, '\n', 'vuecomponent', vuecomponent);
      this.$store.commit(Constant.CHANGETREE, {treeKey:data.label});
    },
    // routePath(){
    //   console.log(this.$store.state.treeKey);
    //   console.log(this.$route.path);
    //   this.$router.push({path:`${this.$route.path}/${this.$store.state.treeKey}`});
    // },
  },

};
</script>

<style>
.search-service {
  border: 1px solid #e4e7ed;
  background-color: white;
  padding: 7px;
  height: 100%;
}

.search-service > .el-input--mini .el-input__inner {
  border-radius: 40px;
}
</style>
