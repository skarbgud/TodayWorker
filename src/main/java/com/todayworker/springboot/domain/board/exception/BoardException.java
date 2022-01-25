package com.todayworker.springboot.domain.board.exception;

import com.todayworker.springboot.domain.common.exception.BaseException;
import com.todayworker.springboot.domain.common.exception.enums.BaseErrorCodeIF;

public class BoardException extends BaseException {

    public BoardException(
        BaseErrorCodeIF errorCode) {
        super(errorCode);
    }

    public BoardException(
        BaseErrorCodeIF errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
