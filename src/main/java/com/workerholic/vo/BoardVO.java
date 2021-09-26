package com.workerholic.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
//    BNO            INT NOT NULL AUTO_INCREMENT,
//    TYPE            VARCHAR(20) NOT NULL,
//    TITLE            VARCHAR(30),
//    CONTENT        TEXT,
//    CNT            INT DEFAULT 0,
//    WRITER            VARCHAR(20) NOT NULL,
//    REGDATE             TIMESTAMP DEFAULT NOW(),
	int bno;
	String type;
	String title;
	String content;
	String cnt;
	String writer;
	Date regdate;
}
