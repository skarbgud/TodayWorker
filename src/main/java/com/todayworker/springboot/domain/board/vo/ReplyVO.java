package com.todayworker.springboot.domain.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyVO {

    // 댓글
    private String bno;
    private String rno;
    private String content;
    private String user;
    private String regDate;
    private Boolean isRecomment; // TODO : 이건 무슨 필드 일까요?

}