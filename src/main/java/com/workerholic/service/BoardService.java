package com.workerholic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workerholic.mapper.BoardMapper;

@Service
public class BoardService implements BoardServiceIF{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<Map<String, Object>> getBoardList() {

		List<Map<String, Object>> boardList = new ArrayList<Map<String,Object>>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);

			boardList = mapper.getBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
}
