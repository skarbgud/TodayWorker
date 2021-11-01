import axios from 'axios';
import { baseUrl } from '@/config';

export default {
  // 게시판 > 게시글 > 댓글 등록
  registReply(params) {
    return axios.post(`${baseUrl}/board/regist-reply.do`, params);
  },
  // 게시판 > 게시글 > 댓글 수정
  updateReply(params) {
    return axios.post(`${baseUrl}/board/update-reply.do`, params);
  },
  // 게시판 > 게시글 > 댓글 삭제
  deleteReply(params) {
    return axios.post(`${baseUrl}/board/delete-reply.do`, params);
  }
}