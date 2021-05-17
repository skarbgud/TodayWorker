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

@Controller(value = "liveboard")
public class LiveBoardController {

	private static final Logger logger = LoggerFactory.getLogger(LiveBoardController.class);

	@Autowired
	private LiveBoardService service;

	@RequestMapping(value="/get-live-board-list.do",method=RequestMethod.GET)
	@ResponseBody
	public ResultVO getLiveBoardList()
	{
		logger.info("GetLiveBoardList");
		ResultVO vo = new ResultVO();
		
		try {
			vo.setResult(service.getLiveBoardList());
			vo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo; 
	}
}
