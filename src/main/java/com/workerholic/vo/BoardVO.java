package com.workerholic.vo;

import java.util.Date;

public class BoardVO {
	private int bno;
	private	String type;
	private String title;
	private String content;
	private String cnt;
	private String writer;
	private Date regdate;
	
	// 투표 항목
	String [] voteList;
	// 해시태그 목록
	String [] tagList;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
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
