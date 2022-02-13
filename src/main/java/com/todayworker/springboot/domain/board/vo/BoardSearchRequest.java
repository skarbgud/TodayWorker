package com.todayworker.springboot.domain.board.vo;

import com.todayworker.springboot.domain.common.dto.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchRequest {

    private String writer;
    private String content;
    private String tagKeyword;
    private PageableRequest paging; // 페이징 요청이 필요한 경우.
}
