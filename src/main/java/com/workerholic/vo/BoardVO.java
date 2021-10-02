package com.workerholic.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	int bno;
	String type;
	String title;
	String content;
	String cnt;
	String writer;
	Date regdate;
	
	// 투표 항목
	String [] voteList;
	// 해시태그 목록
	String [] tagList;
}
