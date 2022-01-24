package com.todayworker.springboot.web.controller;

import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import com.todayworker.springboot.domain.common.PageableRequest;
import com.todayworker.springboot.domain.config.ResultVO;
import com.todayworker.springboot.web.service.BoardServiceIF;
import com.todayworker.springboot.web.service.ReplyServiceIF;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor // 생성자 주입 어노테이션 => final 필드 변수
@RestController
@RequestMapping("board/")
public class BoardController {

    private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

    private final BoardServiceIF service;

    private final ReplyServiceIF replyService;

    @ResponseBody
    @RequestMapping(value = "get-board-list.do", method = RequestMethod.POST)
    public ResultVO getBoardList(@RequestBody PageableRequest request) throws Exception {
        LOG.info("GetBoardList");
        ResultVO result = new ResultVO(false, null);
        result.setData(service.getBoardList(request));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "get-board-detail.do", method = RequestMethod.POST)
    public ResultVO getBoardDetail(@RequestBody BoardVO vo) throws Exception {
        LOG.info("GetBoardDetail");
        ResultVO result = new ResultVO(false, null);
        result.setData(service.getBoardDetail(vo));
        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "insert-board.do", method = RequestMethod.POST)
    public ResultVO insertBoard(@RequestBody BoardVO vo) throws Exception {
        LOG.info("InsertBoard");
        ResultVO result = new ResultVO(false, null);
        result.setData(service.insertBoard(vo));
        result.setSuccess(true);
        return result;

    }

    @ResponseBody
    @RequestMapping(value = "update-board.do", method = RequestMethod.POST)
    public ResultVO updateBoard(@RequestBody BoardVO vo) throws Exception {
        LOG.info("UpdateBoard");
        ResultVO result = new ResultVO(false, null);
        result.setData(service.updateBoard(vo));
        result.setSuccess(true);
        return result;

    }

    @ResponseBody
    @RequestMapping(value = "delete-board.do", method = RequestMethod.POST)
    public ResultVO deleteBoard(@RequestBody BoardVO vo) throws Exception {
        LOG.info("DeleteBoard");
        ResultVO result = new ResultVO(false, null);
        result.setData(service.deleteBoard(vo));
        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "regist-reply.do", method = RequestMethod.POST)
    public ResultVO registReply(@RequestBody ReplyVO vo) throws Exception {
        LOG.info("registReply");
        ResultVO result = new ResultVO(false, null);
        result.setData(replyService.registerReply(vo));
        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "update-reply.do", method = RequestMethod.POST)
    public ResultVO updateReply(@RequestBody ReplyVO vo) throws Exception {
        LOG.info("updateReply");
        ResultVO result = new ResultVO(false, null);
        result.setData(replyService.updateReply(vo));
        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "delete-reply.do", method = RequestMethod.POST)
    public ResultVO deleteReply(@RequestBody ReplyVO vo) throws Exception {
        LOG.info("deleteReply");
        ResultVO result = new ResultVO(false, null);
        result.setData(replyService.deleteReply(vo));
        result.setSuccess(true);
        return result;
    }
}