package com.workerholic.vo;

public class ReplyVO {
	
	// 댓글
	private String bno;
	private String rno;
	private String content;
	private String writer;
	private String regDate;
	private Boolean isRecomment;
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Boolean getIsRecomment() {
		return isRecomment;
	}
	public void setIsRecomment(Boolean isRecomment) {
		this.isRecomment = isRecomment;
	}
}