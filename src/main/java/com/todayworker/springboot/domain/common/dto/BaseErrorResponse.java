package com.todayworker.springboot.domain.common.dto;

import com.todayworker.springboot.domain.common.exception.BaseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseErrorResponse {

    int errorCode;
    String errorMessage;

    public static BaseErrorResponse createErrorResponse(BaseException baseException) {
        return new BaseErrorResponse(baseException.getErrorCode(), baseException.getErrorMessage());
    }
}
