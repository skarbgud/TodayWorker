package com.workerholic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workerholic.service.LiveBoardService;
import com.workerholic.vo.ResultVO;

@Controller
@RequestMapping("liveboard/")
public class LiveBoardController {

	private static final Logger LOG = LoggerFactory.getLogger(LiveBoardController.class);

	@Autowired
	LiveBoardService service;
	
	@ResponseBody
	@RequestMapping(value = "get-live-board-list.do", method = RequestMethod.GET)
	public ResultVO getLiveBoardList() {
		LOG.info("GetLiveBoardList");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setResult(service.getLiveBoardList());
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[LiveBoard] getLiveBoardList : " + e.getMessage(), e);
		}

		return result;
	}
}