package com.workerholic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.workerholic.mapper.LiveBoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class LiveBoardServiceTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void getLiveBoardList() {

		List<Map<String, Object>> liveBoardList = new ArrayList<Map<String, Object>>();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			LiveBoardMapper mapper = session.getMapper(LiveBoardMapper.class);

			liveBoardList = mapper.getLiveBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(liveBoardList);
	}

}
