package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import java.util.List;

public interface BoardServiceIF {

    List<BoardVO> getBoardList(PageableRequest request);

    BoardVO getBoard(BoardVO vo);

    BoardVO insertBoard(BoardVO vo);

    BoardVO updateBoard(BoardVO vo);

    boolean deleteBoard(BoardVO vo);
}
