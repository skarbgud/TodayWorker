package com.workerholic.controller;

import org.springframework.web.bind.annotation.RequestBody;

import com.workerholic.vo.BoardVO;
import com.workerholic.vo.ReplyVO;
import com.workerholic.vo.ResultVO;

public interface BoardControllerIF {

	public ResultVO getBoardList();

	public ResultVO getBoardDetail(@RequestBody BoardVO vo);

	public ResultVO insertBoard(@RequestBody BoardVO vo);

	public ResultVO updateBoard(@RequestBody BoardVO vo);

	public ResultVO deleteBoard(@RequestBody BoardVO vo);
	
	public ResultVO registReply(@RequestBody ReplyVO vo);
}
