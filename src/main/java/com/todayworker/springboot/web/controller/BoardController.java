package com.todayworker.springboot.web.controller;

import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import com.todayworker.springboot.web.service.BoardServiceIF;
import com.todayworker.springboot.web.service.CommentServiceIF;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor // 생성자 주입 어노테이션 => final 필드 변수
@RestController
@RequestMapping("board")
public class BoardController {

    private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

    private final BoardServiceIF boardService;

    private final CommentServiceIF commentService;

    @ApiOperation(value = "전체 게시글 조회(페이징 처리)")
    @PostMapping(value = "/get-board-list.do")
    public List<BoardVO> getBoardList(@RequestBody PageableRequest request) {
        LOG.info("GetBoardList");
        return boardService.getBoardList(request);
    }

    @ApiOperation(value = "단일 게시글 조회(bno base)")
    @PostMapping(value = "/get-board-detail.do")
    public BoardVO getBoardDetail(@RequestBody BoardVO vo) {
        LOG.info("GetBoardDetail");
        return boardService.getBoard(vo);
    }

    @ApiOperation(value = "게시글 등록(create)")
    @PostMapping(value = "insert-board.do")
    public BoardVO insertBoard(@RequestBody BoardVO vo) {
        LOG.info("InsertBoard");
        return boardService.insertBoard(vo);
    }

    @ApiOperation(value = "게시글 수정(update)")
    @PostMapping(value = "update-board.do")
    public BoardVO updateBoard(@RequestBody BoardVO vo) {
        LOG.info("UpdateBoard");
        return boardService.updateBoard(vo);
    }

    @ApiOperation(value = "게시글 삭제(delete)")
    @PostMapping(value = "delete-board.do")
    public boolean deleteBoard(@RequestBody BoardVO vo) {
        LOG.info("DeleteBoard");
        return boardService.deleteBoard(vo);
    }

    @ApiOperation(value = "댓글 등록(create)")
    @PostMapping(value = "regist-reply.do")
    public boolean registReply(@RequestBody ReplyVO vo) throws Exception {
        LOG.info("registReply");
        return commentService.registerReply(vo);
    }

    @ApiOperation(value = "댓글 수정(update)")
    @PostMapping(value = "update-reply.do")
    public boolean updateReply(@RequestBody ReplyVO vo) throws Exception {
        LOG.info("updateReply");
        return commentService.updateReply(vo);
    }

    @ApiOperation(value = "댓글 삭제(delete)")
    @PostMapping(value = "delete-reply.do")
    public boolean deleteReply(@RequestBody ReplyVO vo) throws Exception {
        LOG.info("deleteReply");
        return commentService.deleteReply(vo);
    }
}