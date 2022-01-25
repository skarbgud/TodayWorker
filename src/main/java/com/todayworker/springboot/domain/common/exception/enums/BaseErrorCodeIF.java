package com.todayworker.springboot.domain.common.exception.enums;

import org.springframework.http.HttpStatus;

public interface BaseErrorCodeIF {

    HttpStatus getErrorStatus();

    String getErrorMessage();
}
