package com.todayworker.springboot.domain.board.es.repository;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BoardElasticSearchRepository extends
        ElasticsearchRepository<BoardDocument, String> {
    Page<BoardDocument> findAllByOrderByRegDateDesc(Pageable pageable);
}
