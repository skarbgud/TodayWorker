package com.todayworker.springboot.domain.board.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardVO {

    private Long boardId; // board의 PK
    private String bno; // TODO : 이게 ID 아닌가요?
    private String categoriName;
    private String title;
    private String content;
    private long cnt;
    private String user;
    private String regDate;

    // TODO : 투표 / 해시태그 설명이 필요할 듯 합니다.
    // 투표 항목
    List<String> voteList;
    // 해시태그 목록
    List<String> tagList;
    List<ReplyVO> reply;

    public List<ReplyVO> saveNewComment(ReplyVO replyVO) {
        if (reply == null || reply.isEmpty()) {
            this.reply = new ArrayList<>();
        }

        this.reply.add(replyVO);
        return this.reply;
    }
}
