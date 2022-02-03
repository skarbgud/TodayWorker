package com.todayworker.springboot.domain.board.exception;

import com.todayworker.springboot.domain.common.exception.enums.BaseErrorCodeIF;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BoardErrorCode implements BaseErrorCodeIF {

    public static final String INVALID_WRITER = "올바르지 않은 작성자 ";
    public static final String INVALID_BOARD = "유효하지 않은 게시글 ";
    public static final String NON_EXIST_BOARD = "게시글이 존재하지 않습니다. ";
    public static final String BOARD_TRANSACTION_PROCESSING_ERROR = "게시글 처리 중 에러가 발생하였습니다.";

    public static final String BOARD_UPDATE_ERROR_FOR_COUNTER = "게시글 Counter update 실패";

    private HttpStatus errorStatus;
    private String errorMessage;

    public static BoardErrorCode of(HttpStatus httpStatus, String errorMessage) {
        return new BoardErrorCode(httpStatus, errorMessage);
    }

    public static BoardErrorCode of(HttpStatus httpStatus, String errorMessage, String param) {
        return new BoardErrorCode(httpStatus, errorMessage, param);
    }


    private BoardErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.errorStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    private BoardErrorCode(HttpStatus httpStatus, String errorMessage, String param) {
        this.errorStatus = httpStatus;
        this.errorMessage = errorMessage + "[" + param + "]";
    }

}
