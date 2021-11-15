package com.todayworker.springboot.web.service;

import java.util.List;
import java.util.Map;

import com.todayworker.springboot.domain.board.BoardVO;
import com.todayworker.springboot.domain.config.ElasticSearchVO;

public interface BoardServiceIF {

	public List<Map<String, Object>> getBoardList(ElasticSearchVO vo) throws Exception;
	
	public Map<String, Object> getBoardDetail(BoardVO vo) throws Exception;
	
	public String insertBoard(BoardVO vo) throws Exception;
	
	public String updateBoard(BoardVO vo) throws Exception;
	
	public boolean deleteBoard(BoardVO vo) throws Exception;
}
