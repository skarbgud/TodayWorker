package com.todayworker.springboot.domain.common.dto;

import com.todayworker.springboot.domain.common.exception.BaseException;
import com.todayworker.springboot.domain.common.exception.enums.BaseErrorCode;
import java.util.Date;
import lombok.Data;

/* FE의 페이징 관련 요청을 처리하기 위한 Request Dto */
@Data
public class PageableRequest {

    private Date startDatetime;
    private Date endDatetime;

    private int pageSize;
    private int fromIndex;

    // Spring의 Pageable 구현체에 들어가는 페이지 index와 요청으로 들어오는 fromIndex라는 값에 차이가 있음.
    // PageIndex로 변환해주기 위한 변환 함수 추가.
    public int parseFromIndexToPageOffset() {
        if (pageSize <= 0) {
            throw new BaseException(BaseErrorCode.ZERO_PAGING_SIZE);
        }

        // Page Index로 변환해주기 위해 size로 나눠줌.
        return (fromIndex / pageSize);
    }
}
