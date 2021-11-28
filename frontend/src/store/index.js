import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        email: '',
        name: '',
        picture: '',
    },
    getters: {
        getEmail(state) {
            return state.email;
        },
        getName(state) {
            return state.name;
        },
        getPicture(state) {
            return state.picture;
        }
    },
    mutations: {
        LOGIN(state, userInfo) {
            state.email = userInfo.email;
            state.name = userInfo.name;
            state.picture = userInfo.picture;
        },
        LOGOUT(state) {
            state.email = null;
            state.name = null;
            state.picture = null;
        }
    },
    actions: {
        LOGIN({commit}, {userInfo}) {
            commit("LOGIN", userInfo)
        },
        LOGOUT({commit}) {
            commit("LOGOUT")
        },
    }
})