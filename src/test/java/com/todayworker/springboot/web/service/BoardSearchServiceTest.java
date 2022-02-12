package com.todayworker.springboot.web.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.UuidUtils;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(ElasticSearchExtension.class)
public class BoardSearchServiceTest {

    @Autowired
    BoardSearchService boardSearchService;

    @Autowired
    BoardElasticSearchRepository elasticSearchRepository;


    @Value("${todayworker.elasticsearch.index.board}")
    private String boardIndexName;

    private static String[] testkeywords = {"Car", "Beauty", "Economy", "Politics", "Economic"};

    private static String[] korTestkeywords = {"자동차", "미용", "경제", "정치", "재테크"};

    @BeforeEach
    public void setUp() {
        AtomicInteger index = new AtomicInteger(0);

        List<BoardDocument> testBoards = Stream.generate(() -> new BoardVO(
                null,
                UuidUtils.generateNoDashUUID(),
                "category " + index.incrementAndGet(),
                "title" + index.get(),
                "content " + index.get() + " " + testkeywords[(index.get() / 5)],
                0,
                "user11",
                DateUtils.getDatetimeString(),
                null,
                null,
                null
            ))
            .limit(20)
            .map(it -> BoardDocument.from(it, boardIndexName))
            .collect(Collectors.toList());

        elasticSearchRepository.saveAll(testBoards);
    }

    @AfterEach
    public void clear() {
        elasticSearchRepository.deleteAll();
    }

    @Test
    @DisplayName("Content에 대한 키워드 검색 페이징이 성공해야 한다.")
    public void contentSearchSimilarTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        String searchKeyword = "Car";
        PageableRequest pageableRequest = new PageableRequest();
        pageableRequest.setFromIndex(0);
        pageableRequest.setPageSize(2);
        List<BoardDocument> documentList = boardSearchService.searchBoardWithContent(searchKeyword,
            pageableRequest);

        assertEquals(2, documentList.size());
        documentList.forEach((it) -> {
            try {
                System.out.println("searched Documents : " + objectMapper.writeValueAsString(it));
                assertTrue(it.getContent().contains(searchKeyword));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });


    }

    @Test
    @DisplayName("대소문자가 다르더라도 Content에 대한 키워드 검색 페이징이 성공해야 한다.")
    public void contentSearchSimilarDifferentCaseTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        String searchKeyword = "CAR";
        PageableRequest pageableRequest = new PageableRequest();
        pageableRequest.setFromIndex(0);
        pageableRequest.setPageSize(2);
        List<BoardDocument> documentList = boardSearchService.searchBoardWithContent(searchKeyword,
            pageableRequest);

        assertEquals(2, documentList.size());
        documentList.forEach((it) -> {
            try {
                System.out.println("searched Documents : " + objectMapper.writeValueAsString(it));
                assertTrue(it.getContent().contains("Car"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    @DisplayName("한글 키워드 검색 확인")
    public void contentSearchWithKorean() {
        AtomicInteger index = new AtomicInteger(0);
        List<BoardDocument> koreanTestBoards = Stream.generate(() -> new BoardVO(
                null,
                UuidUtils.generateNoDashUUID(),
                "category " + index.incrementAndGet(),
                "title" + index.get(),
                "content " + index.get() + " " + korTestkeywords[(index.get() / 5)],
                0,
                "user11",
                DateUtils.getDatetimeString(),
                null,
                null,
                null
            ))
            .limit(20)
            .map(it -> BoardDocument.from(it, boardIndexName))
            .collect(Collectors.toList());

        elasticSearchRepository.saveAll(koreanTestBoards);

        ObjectMapper objectMapper = new ObjectMapper();

        String searchKeyword = "자동차";
        PageableRequest pageableRequest = new PageableRequest();
        pageableRequest.setFromIndex(0);
        pageableRequest.setPageSize(2);
        List<BoardDocument> documentList = boardSearchService.searchBoardWithContent(searchKeyword,
            pageableRequest);

        assertEquals(2, documentList.size());
        documentList.forEach((it) -> {
            try {
                System.out.println("searched Documents : " + objectMapper.writeValueAsString(it));
                assertTrue(it.getContent().contains("자동차"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
