package com.workerholic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.workerholic.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	public List<Map<String, Object>> getBoardList();
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void delete(int bno);

}
