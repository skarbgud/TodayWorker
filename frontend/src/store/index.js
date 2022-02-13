import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        email: '',
        name: '',
        picture: '',
        post: [],
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
        },
        getPost(state) {
            return state.post;
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
        },
        POST(state, postData) {
            state.post = postData;
        }
    },
    actions: {
        LOGIN({commit}, {userInfo}) {
            commit("LOGIN", userInfo)
        },
        LOGOUT({commit}) {
            commit("LOGOUT")
        },
        POST({commit}, postData) {
            commit("POST", postData);
        },
    }
})