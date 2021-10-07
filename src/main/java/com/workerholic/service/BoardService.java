package com.workerholic.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
	public Map<String, Object> getBoardDetail(BoardVO boardVO) throws Exception  {
		
		boardVO.setBno("2672725de75d46f3868acad9cda040db");
		Map<String, Object> board = new HashMap<String, Object>();
		
		// bno
		String boardNumer = boardVO.getBno();
		// id (인덱스이름 + bno)
		String id = indexName + boardNumer;
		
		//쿼리문
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		boolQueryBuilder.must(QueryBuilders.termQuery("_id", id));
		sourceBuilder.query(boolQueryBuilder);
		
		searchRequest.source(sourceBuilder);
		
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);;
			
			for (SearchHit hit : searchResponse.getHits().getHits())
			{
				board = hit.getSourceAsMap();
			}			
			
		} catch (ElasticsearchStatusException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return board;
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		
		// bno는 uuid로 고유화
		boardVO.setBno(uuid);
		// 조회수는 기본값 0
		boardVO.setCnt(0);
		// 현재 날짜
		boardVO.setRegdate(new Date());
		
		Map<String, String> boardMap = null;
		
		try {
			boardMap = BeanUtils.describe(boardVO);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			// id는 인덱스 + bno로 unique
			IndexRequest request = new IndexRequest(indexName).id(indexName+boardVO.getBno()).source(boardMap, XContentType.JSON);
			
			client.index(request, RequestOptions.DEFAULT);
			
		} catch (ElasticsearchStatusException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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
