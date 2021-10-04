package com.workerholic.vo;


public class ResultVO {
	
	// 서버 로직에 대한 Response Data
	private Object data;
	// 서버 로직에 대한 성공 여부 설정
	private boolean success;
	
	// 생성자
	public ResultVO(boolean success, Object data) {
		this.setSuccess(success);
		this.setData(data);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
