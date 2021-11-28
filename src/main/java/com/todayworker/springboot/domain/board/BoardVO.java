package com.todayworker.springboot.domain.board;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardVO {
	private String bno;
	private	String categoriName;
	private String title;
	private String content;
	private int cnt;
	private String user;
	private String regDate;
	
	// 투표 항목
	String [] voteList;
	// 해시태그 목록
	String [] tagList;
	
	List<ReplyVO> reply;
}
