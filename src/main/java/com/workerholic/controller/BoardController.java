package com.workerholic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workerholic.service.BoardService;
import com.workerholic.service.ReplyService;
import com.workerholic.vo.BoardVO;
import com.workerholic.vo.ElasticSearchVO;
import com.workerholic.vo.ReplyVO;
import com.workerholic.vo.ResultVO;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("board/")
public class BoardController implements BoardControllerIF {

	private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService service;
	
	@Autowired
	ReplyService replyService;

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
			service.insertBoard(vo);
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
			service.updateBoard(vo);
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] updaeteBoard : " + e.getMessage(), e);
		}

		return result;

	}

	@Override
	@RequestMapping(value = "delete-board.do", method = RequestMethod.POST)
	public ResultVO deleteBoard(BoardVO vo) {
		LOG.info("DeleteBoard");
		ResultVO result = new ResultVO(false, null);

		try {
			service.deleteBoard(vo);
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] deleteBoard : " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public ResultVO registReply(ReplyVO vo) {
		LOG.info("registReply");
		ResultVO result = new ResultVO(false, null);

		try {
			replyService.registReply(vo);
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] registReply : " + e.getMessage(), e);
		}

		return result;
	}
}