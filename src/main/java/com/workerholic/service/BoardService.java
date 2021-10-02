package com.workerholic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.workerholic.utils.ElasticsearchConnect;
import com.workerholic.vo.BoardVO;

@Service
public class BoardService implements BoardServiceIF {

	// es연결 정보
	ElasticsearchConnect connect = new ElasticsearchConnect("localhost", 9200);

	// Rest connection 설정
	private final RestHighLevelClient client = connect.getConnection();

	// 인덱스 name
	private String indexName = "board";

	public List<Map<String, Object>> getBoardList() throws Exception {
		List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

		// 쿼리문
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 시작위치
		sourceBuilder.from(0);
		// 가져오는 데이터양
		sourceBuilder.size(1000);
		searchRequest.source(sourceBuilder);

		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

			for (SearchHit hit : searchResponse.getHits().getHits()) {
				Map<String, Object> sourceMap = hit.getSourceAsMap();
				boardList.add(sourceMap);
			}
		} 
		catch (ElasticsearchStatusException e) 
		{
			e.printStackTrace();
		}

		return boardList;
	}

	@Override
	public Map<String, Object> getBoardDetail() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
