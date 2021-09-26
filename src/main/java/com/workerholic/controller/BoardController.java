package com.workerholic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workerholic.service.BoardService;
import com.workerholic.vo.ResultVO;

@Controller
@RequestMapping("board/")
public class BoardController {

	private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService service;
	
	@ResponseBody
	@RequestMapping(value = "get-board-list.do", method = RequestMethod.POST)
	public ResultVO getBoardList() {
		LOG.info("GetBoardList");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setResult(service.getBoardList());
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Board] getBoardList : " + e.getMessage(), e);
		}

		return result;
	}
}