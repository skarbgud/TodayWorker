import axios from 'axios';
import {baseUrl} from '@/config';

export default {
    // 로그인 정보 조회
    getUserInfo(params) {
        return axios.get(`${baseUrl}/login/login.do`, params);
    },
    // 로그 아웃
    getUserLogOut(params) {
        return axios.get(`${baseUrl}/logout`, params);
    },

}