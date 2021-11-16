package com.todayworker.springboot.web.service;


import com.todayworker.springboot.domain.board.ReplyVO;

public interface ReplyServiceIF {

	public boolean registReply(ReplyVO vo) throws Exception;
	
	public boolean updateReply(ReplyVO vo) throws Exception;
	
	public boolean deleteReply(ReplyVO vo) throws Exception;
}
