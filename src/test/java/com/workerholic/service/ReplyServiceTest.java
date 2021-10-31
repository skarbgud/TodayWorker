package com.workerholic.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
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
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workerholic.utils.ConvertUtils;
import com.workerholic.utils.DateUtils;
import com.workerholic.utils.ElasticsearchConnect;
import com.workerholic.vo.BoardVO;
import com.workerholic.vo.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class ReplyServiceTest {

	private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();

	private String hostname = "192.168.1.103";
	private Integer port = 9200;

	// es연결 정보
	ElasticsearchConnect connect = new ElasticsearchConnect(hostname, port);

	// Rest connection 설정
	private final RestHighLevelClient client = connect.getConnection();

	// 인덱스 name
	String indexName = "board";

	private static BoardVO getBoardVO() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno("1e690a686a9b48b29c20b27a4c969041");
		boardVO.setCategoriName("live");
		boardVO.setCnt(0);
		boardVO.setContent("테스트내용");
		boardVO.setTitle("테스트제목");

		return boardVO;
	}

	private static ReplyVO getReplyVO() {
		ReplyVO replyVO = new ReplyVO();
		replyVO.setContent("테스트댓글입니다.1");
		replyVO.setRegDate(DateUtils.getDatetimeString());
		replyVO.setRno(null);
		replyVO.setWriter("테스트 댓글 작성자1");

		return replyVO;
	}

	// 댓글 추가
	@Test
	public void insertReply() {
		BoardVO boardVO = getBoardVO();
		ReplyVO replyVO = getReplyVO();

		// bno 가져와서 해당 게시글에 댓글 등록
		String bno = boardVO.getBno();

		// rno 고유 키 생성
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		replyVO.setRno(uuid);

		Map<String, Object> replyMap = new HashMap<String, Object>();
		try {
			replyMap = ConvertUtils.convertToMap(replyVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> singletonMap = Collections.singletonMap("reply", replyMap);
		
		try {
			
			UpdateRequest request = new UpdateRequest(indexName, indexName + bno).script(new Script(ScriptType.INLINE, "painless", "if (ctx._source.reply == null) {ctx._source.reply=[]} ctx._source.reply.add(params.reply)", singletonMap));
			

			UpdateResponse response;

			response = client.update(request, RequestOptions.DEFAULT);

			RestStatus status = response.status();

			System.out.println(status);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// 인덱스 초기화
	@Test
	public void deleteAndCreateIndexData() {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);

		createIndexRequest
				.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));

		try {
			client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
			client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
