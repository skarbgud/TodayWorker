import Vue from 'vue';
import Vuex from 'vuex';
import Constant from '../utils/constant';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    treeKey: '1',
  },
  mutations: {
    [Constant.CHANGETREE]: (state, payload) => {
      state.treeKey = payload.treeKey;
    },
  },
});
