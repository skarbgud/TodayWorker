package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.vo.BoardDetailVO;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.common.PageableRequest;
import java.util.List;

public interface BoardServiceIF {

    List<BoardVO> getBoardList(PageableRequest request) throws Exception;

    BoardDetailVO getBoardDetail(BoardVO vo) throws Exception;

    String insertBoard(BoardVO vo) throws Exception;

    String updateBoard(BoardVO vo) throws Exception;

    boolean deleteBoard(BoardVO vo) throws Exception;
}
