package com.workerholic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.workerholic.vo.LiveBoardVO;

@Mapper
public interface LiveBoardMapper {
	
	public List<LiveBoardVO> getLiveBoardList();
	public void insert(LiveBoardVO vo);
	public void update(LiveBoardVO vo);
	public void delete(int bno);

}
