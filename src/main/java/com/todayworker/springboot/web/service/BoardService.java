package com.todayworker.springboot.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.todayworker.springboot.domain.board.BoardVO;
import com.todayworker.springboot.domain.config.ElasticSearchVO;
import com.todayworker.springboot.utils.ConvertUtils;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.ElasticsearchConnect;
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
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class BoardService implements BoardServiceIF {
	
	// es연결 정보
	ElasticsearchConnect connect = new ElasticsearchConnect("192.168.1.103", 9200);

	// Rest connection 설정
	private final RestHighLevelClient client = connect.getConnection();

	// 인덱스 name
	private final String indexName = "board";

	public List<Map<String, Object>> getBoardList(ElasticSearchVO vo) throws Exception {
		List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

		// 쿼리문
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 시작위치
		sourceBuilder.from(vo.getFromIndex());
		// 가져오는 데이터양
		sourceBuilder.size(vo.getPageSize());
		// 최신글로 정렬하여 가져오기
		sourceBuilder.sort("regDate", SortOrder.DESC);
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
	public String insertBoard(BoardVO boardVO) throws Exception {

		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");

		// bno는 uuid로 고유화
		boardVO.setBno(uuid);
		// 조회수는 기본값 0
		boardVO.setCnt(0);
		// 현재 날짜
		boardVO.setRegDate(DateUtils.getDatetimeString());

		Map<String, Object> boardMap = ConvertUtils.convertToMap(boardVO);

		// id는 인덱스 + bno로 unique
		IndexRequest request = new IndexRequest(indexName).id(indexName + boardVO.getBno()).source(boardMap, XContentType.JSON);

		client.index(request, RequestOptions.DEFAULT);
		
		// vue 라우터 이동을 위한 urlpath(boardType/bno)
		String urlPath = boardVO.getCategoriName() + "/" + boardVO.getBno(); 
		
		return urlPath;
	}
 
	@Override
	public String updateBoard(BoardVO boardVO) throws Exception {

		// bno
		String bno = boardVO.getBno();
		// id (인덱스 이름 + bno)
		String id = indexName + bno;

		String json = null;
		json = new ObjectMapper().writeValueAsString(boardVO);

		UpdateRequest request = new UpdateRequest(indexName, "_doc", id).doc(json, XContentType.JSON);

		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
		
		RestStatus status = response.status();
		
		if (status == RestStatus.OK)
		{
			// vue 라우터 이동을 위한 urlpath(boardType/bno)
			String urlPath = boardVO.getCategoriName() + "/" + boardVO.getBno();
			return urlPath;
		}
		else
		{
			return "/";
		}
	}

	@Override
	public boolean deleteBoard(BoardVO vo) throws Exception {
		
		// bno
		String bno = vo.getBno();
		// id (인덱스 이름 + bno)
		String id = indexName + bno;
		
		DeleteRequest request = new DeleteRequest(indexName, "_doc", id);
		
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
