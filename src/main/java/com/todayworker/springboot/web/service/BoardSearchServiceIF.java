package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import java.util.List;

public interface BoardSearchServiceIF {

    List<BoardDocument> searchBoardWithContent(String contentKeyword,
        PageableRequest pageableRequest);
}
