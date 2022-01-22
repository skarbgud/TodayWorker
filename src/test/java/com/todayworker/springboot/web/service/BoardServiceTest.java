package com.todayworker.springboot.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.todayworker.springboot.domain.board.BoardVO;
import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.ElasticsearchConnect;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.ElasticsearchStatusException;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// TODO : 이름은 BoardServiceTest인데 BoardService를 테스트 하지 않네요
@SpringBootTest
@ExtendWith(ElasticSearchExtension.class)
@ActiveProfiles("test")
@DirtiesContext
public class BoardServiceTest {

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();

    private ElasticsearchConnect connect = new ElasticsearchConnect();

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
        boardVO.setBno("a937f17b9ee54dcf84931b0f0cd856fe");
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

    @Test
    @Disabled // TODO: java.net.ConnectException 때문에 TestHold 합니다.
    public void insertBoard() throws IOException{

        Map<String, Object> boardVO = new HashMap<String, Object>();

        for (int i = 0; i < 1000; i++) {
            boardVO.put("bno", i);
            boardVO.put("boardType", "live" + i);
            boardVO.put("title", "테스트제목" + i);
            boardVO.put("content", "테스트내용" + i);
            boardVO.put("cnt", i);
            boardVO.put("", "testUser" + i);
            boardVO.put("registDate", DateUtils.getDatetimeString(new Date()));
            boardVO.put("registDate", new Date()); // KEY 중복???

            try {
                IndexRequest request = new IndexRequest(indexName).id(indexName + i).source(boardVO);

                // TODO : 이거 호출하면 ConnectException 떨어지네요, 로컬에 붙어서 테스트 하셨을 때 데이터 잘 들어갔나요?
                client.index(request, RequestOptions.DEFAULT);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                throw e; // 처음에 이거 throw 하는게 없어서 예외 떨어져도 테스트는 통과 했을 것 같네요
            }
        }
    }

    @Test
    @Disabled // ConnectException 떨어집니다.
    public void insertBoardUUID() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("UUID제목테스트");
        boardVO.setContent("UUID내용테스트");
        boardVO.setCategoriName("live");

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
        boardVO.setRegDate(DateUtils.getDatetimeString(new Date()));

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
        boardVO.setBno("230e97293bbc4332932c71fee00b25d6");
        boardVO.setTitle("변경된 제목입니다10/29 with tagList");
        boardVO.setContent("변경된 내용입니다10/29 with tagList");
        String [] tagList = {"변경태그1", "변경태그2"};
        boardVO.setTagList(tagList);

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

}
