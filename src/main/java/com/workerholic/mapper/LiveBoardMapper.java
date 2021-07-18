package com.workerholic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.workerholic.vo.LiveBoardVO;

@Mapper
public interface LiveBoardMapper {
	
	public List<Map<String, Object>> getLiveBoardList();
	public void insert(LiveBoardVO vo);
	public void update(LiveBoardVO vo);
	public void delete(int bno);

}
