package com.todayworker.springboot.domain.common;

import java.util.Date;
import lombok.Data;

@Data
public class PageableRequest {

    private Date startDatetime;
    private Date endDatetime;

    private int pageSize;
    private int fromIndex;
}
