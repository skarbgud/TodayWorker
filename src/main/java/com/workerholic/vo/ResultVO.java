package com.workerholic.vo;

import lombok.Data;

@Data
public class ResultVO {
	
	// 서버 로직에 대한 Response Data
	Object data;
	// 서버 로직에 대한 성공 여부 설정
	boolean success;
	
	// 생성자
	public ResultVO(boolean success, Object data) {
		this.setSuccess(success);
		this.setData(data);
	}
}
