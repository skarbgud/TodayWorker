package com.workerholic.service;

import java.util.List;
import java.util.Map;

import com.workerholic.vo.LiveBoardVO;

public interface LiveBoardServiceIF {

	public List<Map<String, Object>> getLiveBoardList();
}
