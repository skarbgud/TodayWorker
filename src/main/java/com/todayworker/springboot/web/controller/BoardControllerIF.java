package com.todayworker.springboot.web.controller;

import com.todayworker.springboot.domain.board.BoardVO;
import com.todayworker.springboot.domain.board.ReplyVO;
import com.todayworker.springboot.domain.config.ElasticSearchVO;
import com.todayworker.springboot.domain.config.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;


public interface BoardControllerIF {

	public ResultVO getBoardList(@RequestBody ElasticSearchVO vo);

	public ResultVO getBoardDetail(@RequestBody BoardVO vo);

	public ResultVO insertBoard(@RequestBody BoardVO vo);

	public ResultVO updateBoard(@RequestBody BoardVO vo);

	public ResultVO deleteBoard(@RequestBody BoardVO vo);
	
	public ResultVO registReply(@RequestBody ReplyVO vo);
	
	public ResultVO updateReply(@RequestBody ReplyVO vo);
	
	public ResultVO deleteReply(@RequestBody ReplyVO vo);
}
