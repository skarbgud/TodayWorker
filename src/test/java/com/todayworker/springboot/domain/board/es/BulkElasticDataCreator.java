package com.todayworker.springboot.domain.board.es;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import com.todayworker.springboot.domain.board.jpa.repository.BoardJpaRepository;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.UuidUtils;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// Local에 Board 더미 데이터 생성을 위한 실행 코드
@SpringBootTest
@ActiveProfiles("local")
@Disabled // 1회성으로 사용시 disabled를 풀어주고 사용합니다.
public class BulkElasticDataCreator {

    private static final int MAX_TEST_RECORDS = 1_000;

    @Autowired
    BoardElasticSearchRepository boardElasticSearchRepository;

    @Autowired
    BoardJpaRepository boardJpaRepository;

    @Test
    @DisplayName("로컬 ES에 대량으로 테스트 데이터를 적재합니다.")
    public void setupBulkBoardDataToElasticSearch() {

        if (boardElasticSearchRepository.existsById("board10")) {
            // 기등록된 테스트 도큐먼트가 있다면 실행을 안함.
            return;
        }

        AtomicInteger index = new AtomicInteger(0);
        Stream.generate(() -> new BoardVO(
                null,
                UuidUtils.generateNoDashUUID(),
                "category " + index.incrementAndGet(),
                "title" + index.get(),
                "content" + index.get(),
                0,
                "user11",
                DateUtils.getDatetimeString(),
                null,
                null,
                null
            ))
            .limit(MAX_TEST_RECORDS)
            .parallel() // forEach가 병렬로 실행 되므로 Stream의 Element들이 생성되는 순서대로 DB에 Insert 되지 않습니다.
            .forEach((it) -> {
                boardElasticSearchRepository.save(BoardDocument.from(it, "board"));
                boardJpaRepository.save(BoardEntity.fromBoardVO(it));
            });

    }
}
