import axios from 'axios';
import { baseUrl } from '@/config';

export default {
  // 게시판 > 게시글 목록 불러오기
  getBoardList(params) {
    return axios.post(`${baseUrl}/board/get-board-list.do`, params);
  },
  // 게시판 > 게시글 등록 
  insertBoard(params) {
    return axios.post(`${baseUrl}/board/insert-board.do`, params);
  },
  // 게시판 > 게시글 상세보기
  getBoardDetail(bno) {
    return axios.get(`${baseUrl}/board/get-board-detail.do/${bno}`);
  },
  // 게시판 > 게시글 업데이트
  updateBoard(params) {
    return axios.post(`${baseUrl}/board/update-board.do`, params);
  },
  // 게시판 > 삭제
  deleteBoard(bno) {
    return axios.delete(`${baseUrl}/board/delete-board.do/${bno}`);
  }
}