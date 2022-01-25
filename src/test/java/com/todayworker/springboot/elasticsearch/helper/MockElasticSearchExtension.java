package com.todayworker.springboot.elasticsearch.helper;

import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MockElasticSearchExtension implements BeforeAllCallback {

    @MockBean
    BoardElasticSearchRepository boardElasticSearchRepository;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        System.setProperty("spring.elasticsearch.rest.uris", "http://localhost:9200");

    }
}
