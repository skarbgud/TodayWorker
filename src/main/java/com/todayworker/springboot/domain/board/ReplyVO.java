package com.todayworker.springboot.domain.board;

import lombok.Data;

@Data
public class ReplyVO {
	
	// 댓글
	private String bno;
	private String rno;
	private String content;
	private String writer;
	private String regDate;
	private Boolean isRecomment;

}