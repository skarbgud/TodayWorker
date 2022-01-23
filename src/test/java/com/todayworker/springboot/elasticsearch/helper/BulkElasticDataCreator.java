package com.todayworker.springboot.elasticsearch.helper;

import com.todayworker.springboot.elasticsearch.document.BoardDocument;
import com.todayworker.springboot.elasticsearch.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.utils.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

// Local에 Board 더미 데이터 생성을 위한 실행 코드
@SpringBootTest
@ActiveProfiles("local")
@Disabled // 1회성으로 사용시 disabled를 풀어주고 사용합니다.
public class BulkElasticDataCreator {

    private static final int MAX_TEST_RECORDS = 1_000;

    @Autowired
    BoardElasticSearchRepository boardElasticSearchRepository;

    @Test
    @DisplayName("로컬 ES에 대량으로 테스트 데이터를 적재합니다.")
    public void setupBulkBoardDataToElasticSearch() {

        if(boardElasticSearchRepository.existsById("board10")){
            // 기등록된 테스트 도큐먼트가 있다면 실행을 안함.
            return;
        }

        AtomicInteger index = new AtomicInteger(0);
        Stream.generate(()->new BoardDocument(
                "board" + index.incrementAndGet(),
                UUID.randomUUID().toString().replace("-",""),
                "category" + RandomUtils.nextInt(10),
                "title" + index.get(),
                "content" + index.get(),
                RandomUtils.nextInt(100),
                "user" + index.get(),
                DateUtils.getDatetimeString(new Date())
        ))
                .limit(MAX_TEST_RECORDS)
                .parallel()
                .forEach((it)->{
                    boardElasticSearchRepository.save(it);
                });

    }
}
