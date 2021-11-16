package com.todayworker.springboot.web.controller;

import com.todayworker.springboot.domain.board.BoardVO;
import com.todayworker.springboot.domain.board.ReplyVO;
import com.todayworker.springboot.domain.config.ElasticSearchVO;
import com.todayworker.springboot.domain.config.ResultVO;
import com.todayworker.springboot.web.service.BoardService;
import com.todayworker.springboot.web.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor // 생성자 주입 어노테이션 => final 필드 변수
@RestController
@RequestMapping("board/")
public class BoardController implements BoardControllerIF {

	private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

	private final BoardService service;

	private final ReplyService replyService;

	@ResponseBody
	@RequestMapping(value = "get-board-list.do", method = RequestMethod.POST)
	public ResultVO getBoardList(@RequestBody ElasticSearchVO vo) {
		LOG.info("GetBoardList");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(service.getBoardList(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] getBoardList : " + e.getMessage(), e);
		}

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "get-board-detail.do", method = RequestMethod.POST)
	public ResultVO getBoardDetail(@RequestBody BoardVO vo) {
		LOG.info("GetBoardDetail");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(service.getBoardDetail(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] getBoardDetail : " + e.getMessage(), e);
		}

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "insert-board.do", method = RequestMethod.POST)
	public ResultVO insertBoard(@RequestBody BoardVO vo) {
		LOG.info("InsertBoard");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(service.insertBoard(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] insertBoard : " + e.getMessage(), e);
		}

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "update-board.do", method = RequestMethod.POST)
	public ResultVO updateBoard(@RequestBody BoardVO vo) {
		LOG.info("UpdateBoard");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(service.updateBoard(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] updaeteBoard : " + e.getMessage(), e);
		}

		return result;

	}

	@Override
	@ResponseBody
	@RequestMapping(value = "delete-board.do", method = RequestMethod.POST)
	public ResultVO deleteBoard(@RequestBody BoardVO vo) {
		LOG.info("DeleteBoard");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(service.deleteBoard(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] deleteBoard : " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "regist-reply.do", method = RequestMethod.POST)
	public ResultVO registReply(@RequestBody ReplyVO vo) {
		LOG.info("registReply");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(replyService.registReply(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] registReply : " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "update-reply.do", method = RequestMethod.POST)
	public ResultVO updateReply(@RequestBody ReplyVO vo) {
		LOG.info("updateReply");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(replyService.updateReply(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] updateReply : " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "delete-reply.do", method = RequestMethod.POST)
	public ResultVO deleteReply(@RequestBody ReplyVO vo) {
		LOG.info("deleteReply");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setData(replyService.deleteReply(vo));
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] deleteReply : " + e.getMessage(), e);
		}

		return result;
	}
}