package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardSearchService implements BoardSearchServiceIF {

    private final BoardElasticSearchRepository boardElasticSearchRepository;

    @Override
    public List<BoardDocument> searchBoardWithContent(String contentKeyword,
        PageableRequest pageableRequest) {
        return boardElasticSearchRepository.findByContentIgnoreCase(
                contentKeyword,
                PageRequest.of(pageableRequest.parseFromIndexToPageOffset(),
                    pageableRequest.getPageSize()))
            .toList();
    }
}
