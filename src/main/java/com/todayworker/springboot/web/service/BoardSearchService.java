package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardSearchService implements BoardSearchServiceIF {

    private final BoardElasticSearchRepository boardElasticSearchRepository;

    @Override
    public List<BoardDocument> searchBoardWithContent(String contentKeyword,
        PageableRequest pageableRequest) {
        return boardElasticSearchRepository.findBoardDocumentByContentContains(
                contentKeyword,
                PageRequest.of(pageableRequest.parseFromIndexToPageOffset(),
                    pageableRequest.getPageSize()))
            .toList();
    }
}
