package com.todayworker.springboot.domain.common.exception.handler;

import com.todayworker.springboot.domain.common.dto.BaseErrorResponse;
import com.todayworker.springboot.domain.common.exception.BaseException;
import com.todayworker.springboot.domain.common.exception.enums.BaseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler({BaseException.class})
    @ResponseBody
    public ResponseEntity<BaseErrorResponse> handleCommonException(
        BaseException e, HttpServletRequest request) {
        log.error(e.toString(), e);
        return new ResponseEntity(BaseErrorResponse.createErrorResponse(e), e.getErrorStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BaseErrorResponse> handleException(
        Exception e, HttpServletRequest request) {
        request.getSession();

        BaseException boxedException = new BaseException(BaseErrorCode.UNKNOWN_SERVER_ERROR, e);
        log.error(boxedException.toString(), e);
        return new ResponseEntity(
            BaseErrorResponse.createErrorResponse(boxedException),
            boxedException.getErrorStatus());
    }
}
