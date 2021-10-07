package com.workerholic.service;

import java.util.List;
import java.util.Map;

import com.workerholic.vo.BoardVO;

public interface BoardServiceIF {

	public List<Map<String, Object>> getBoardList() throws Exception;
	
	public Map<String, Object> getBoardDetail(BoardVO vo) throws Exception;
	
	public void insertBoard(BoardVO vo) throws Exception;
	
	public void updateBoard(BoardVO vo) throws Exception;
	
	public void deleteBoard(BoardVO vo) throws Exception;
}
