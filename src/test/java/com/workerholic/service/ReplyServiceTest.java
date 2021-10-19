package com.workerholic.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workerholic.utils.ElasticsearchConnect;
import com.workerholic.vo.BoardVO;
import com.workerholic.vo.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class ReplyServiceTest {

	private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();

	private String hostname = "localhost";
	private Integer port = 9200;

	// es연결 정보
	ElasticsearchConnect connect = new ElasticsearchConnect("localhost", 9200);

	// Rest connection 설정
	private final RestHighLevelClient client = connect.getConnection();

	// 인덱스 name
	String indexName = "board";

	@Test
	public void getBoardList() {

		List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

		// 쿼리문
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(0);
		sourceBuilder.size(1000);

		searchRequest.source(sourceBuilder);

		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

			for (SearchHit hit : searchResponse.getHits().getHits()) {
				Map<String, Object> sourceMap = hit.getSourceAsMap();
				boardList.add(sourceMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(gson.toJson(boardList));
	}

	@Test
	public void getBoardDetail() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno("2672725de75d46f3868acad9cda040db");
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

		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			;

			for (SearchHit hit : searchResponse.getHits().getHits()) {
				board = hit.getSourceAsMap();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(gson.toJson(board));
	}
	
	
	private BoardVO getBoardVO() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno("408ea6eefe1645d78135c166270ea88f");
		
		return boardVO;
	}

	@Test
	public void insertBoard() {
		BoardVO boardVO = getBoardVO();
		ReplyVO replyVO = new ReplyVO();
		
		// bno 가져와서 해당 게시글에 댓글 등록
		String bno = boardVO.getBno();
		
		// rno 고유 키 생성
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		replyVO.setRno(uuid);
		// 현재 날짜
		replyVO.setRegDate(new Date());
		
		
		replyVO.setTitle("댓글제목");
		replyVO.setContent("댓글 내용");
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(replyVO);
		} 
		catch (JsonProcessingException jsonException) {
			// TODO Auto-generated catch block
			jsonException.printStackTrace();
		}
		
		try {
			IndexRequest request = new IndexRequest(indexName).id(indexName).source(boardVO);

			client.index(request, RequestOptions.DEFAULT);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	

	@Test
	public void insertBoardUUID() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("UUID제목테스트");
		boardVO.setContent("UUID내용테스트");
		boardVO.setType("live");

		try {
			insertBoardUUIDMethod(boardVO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertBoardUUIDMethod(BoardVO boardVO) throws IOException {
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

		try {
			// id는 인덱스 + bno로 unique
			IndexRequest request = new IndexRequest(indexName).id(indexName + boardVO.getBno()).source(boardMap,
					XContentType.JSON);

			client.index(request, RequestOptions.DEFAULT);

		} catch (ElasticsearchStatusException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void updateBoard() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno("408ea6eefe1645d78135c166270ea88f");
		boardVO.setTitle("변경된 제목입니다10/18");
		boardVO.setContent("변경된 내용입니다10/18");

		// bno
		String boardNumer = boardVO.getBno();
		// id (인덱스이름 + bno)
		String id = indexName + boardNumer;
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(boardVO);
		} 
		catch (JsonProcessingException jsonException) {
			// TODO Auto-generated catch block
			jsonException.printStackTrace();
		}
	
		try {
			UpdateRequest request = new UpdateRequest(indexName, id).doc(json, XContentType.JSON);
			
			UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
			
			RestStatus status = response.status();
			 
			System.out.println(status);
				 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void deleteBoard() {
		String bno = "1c90ad87728c45eeb44a1a5b9c0e7802";
		
		// id (인덱스이름 + bno)
		String id = indexName + bno;
		
		DeleteRequest request = new DeleteRequest(indexName, id);

		try {
			DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
			
			RestStatus status = response.status();
			 
			System.out.println(status);

			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void deleteAndCreateIndexData() {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);

		createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));

		try {
			client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
			client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
