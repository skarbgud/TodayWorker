package com.workerholic.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workerholic.mapper.LiveBoardMapper;
import com.workerholic.vo.LiveBoardVO;

@Service
public class LiveBoardService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<LiveBoardVO> getLiveBoardList() {
		List<LiveBoardVO> liveBoardList = new ArrayList<LiveBoardVO>();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			LiveBoardMapper mapper = session.getMapper(LiveBoardMapper.class);

			liveBoardList = mapper.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liveBoardList;
	}
}
