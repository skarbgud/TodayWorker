package com.todayworker.springboot.elasticsearch.repository;

import com.todayworker.springboot.elasticsearch.document.BoardDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardElasticSearchRepository extends ElasticsearchRepository<BoardDocument, String> {
    Page<BoardDocument> findAllByOrderByRegDateDesc(Pageable pageable);
}
