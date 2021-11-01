package com.workerholic.service;

import com.workerholic.vo.ReplyVO;

public interface ReplyServiceIF {

	public boolean registReply(ReplyVO vo) throws Exception;
	
	public boolean updateReply(ReplyVO vo) throws Exception;
	
	public boolean deleteReply(ReplyVO vo) throws Exception;
}
