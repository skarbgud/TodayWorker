package com.workerholic.vo;

import java.util.Date;

import lombok.Data;

@Data
public class LiveBoardVO {
//    LIVE_BNO            INT NOT NULL AUTO_INCREMENT,
//    LIVE_TYPE            VARCHAR(20) NOT NULL,
//    LIVE_TITLE            VARCHAR(30),
//    LIVE_CONTENT        TEXT,
//    LIVE_CNT            INT DEFAULT 0,
//    LIVE_USER            VARCHAR(20) NOT NULL,
//    LIVE_REGDATE             TIMESTAMP DEFAULT NOW(),
	int bno;
	String type;
	String title;
	String content;
	String cnt;
	String user;
	Date regdate;
}
