package com.workerholic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workerholic.mapper.LiveBoardMapper;

@Service
public class LiveBoardService implements LiveBoardServiceIF{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<Map<String, Object>> getLiveBoardList() {

		List<Map<String, Object>> liveBoardList = new ArrayList<Map<String,Object>>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LiveBoardMapper mapper = session.getMapper(LiveBoardMapper.class);

			liveBoardList = mapper.getLiveBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liveBoardList;
	}
}
