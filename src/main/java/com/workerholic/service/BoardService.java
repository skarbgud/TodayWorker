package com.workerholic.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workerholic.utils.ElasticsearchConnect;
import com.workerholic.vo.BoardVO;

@Service
public class BoardService implements BoardServiceIF {

	// es연결 정보
	ElasticsearchConnect connect = new ElasticsearchConnect("52.79.73.9", 9200, "elastic", "password123");

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

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		for (SearchHit hit : searchResponse.getHits().getHits()) {
			Map<String, Object> sourceMap = hit.getSourceAsMap();
			boardList.add(sourceMap);
		}

		return boardList;
	}

	@Override
	public Map<String, Object> getBoardDetail(BoardVO boardVO) throws Exception {
		Map<String, Object> board = new HashMap<String, Object>();

		// bno
		String boardNumer = boardVO.getBno();
		// id (인덱스이름 + bno)
		String id = indexName + boardNumer;

		// 쿼리문
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		boolQueryBuilder.must(QueryBuilders.termQuery("_id", id));
		sourceBuilder.query(boolQueryBuilder);

		searchRequest.source(sourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		for (SearchHit hit : searchResponse.getHits().getHits()) {
			board = hit.getSourceAsMap();
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
		boardVO.setregDate(new Date());

		Map<String, String> boardMap = null;

		try {
			boardMap = BeanUtils.describe(boardVO);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// id는 인덱스 + bno로 unique
		IndexRequest request = new IndexRequest(indexName).id(indexName + boardVO.getBno()).source(boardMap,
				XContentType.JSON);

		client.index(request, RequestOptions.DEFAULT);

	}

	@Override
	public boolean updateBoard(BoardVO vo) throws Exception {

		// bno
		String bno = vo.getBno();
		// id (인덱스 이름 + bno)
		String id = indexName + bno;

		String json = null;
		json = new ObjectMapper().writeValueAsString(vo);

		UpdateRequest request = new UpdateRequest(indexName, id).doc(json, XContentType.JSON);

		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
		
		RestStatus status = response.status();
		
		if (status == RestStatus.OK)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean deleteBoard(BoardVO vo) throws Exception {
		
		// bno
		String bno = vo.getBno();
		// id (인덱스 이름 + bno)
		String id = indexName + bno;
		
		DeleteRequest request = new DeleteRequest(indexName, id);
		
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		
		RestStatus status = response.status();
		
		if (status == RestStatus.OK)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
