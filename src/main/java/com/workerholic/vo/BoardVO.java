package com.workerholic.vo;

import java.util.Date;
import java.util.List;

public class BoardVO {
	private String bno;
	private	String categoriName;
	private String title;
	private String content;
	private int cnt;
	private String writer;
	private String regDate;
	
	// 투표 항목
	String [] voteList;
	// 해시태그 목록
	String [] tagList;
	
	List<ReplyVO> replys;
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getCategoriName() {
		return categoriName;
	}
	public void setCategoriName(String categoriName) {
		this.categoriName = categoriName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String[] getVoteList() {
		return voteList;
	}
	public void setVoteList(String[] voteList) {
		this.voteList = voteList;
	}
	public String[] getTagList() {
		return tagList;
	}
	public void setTagList(String[] tagList) {
		this.tagList = tagList;
	}
}
