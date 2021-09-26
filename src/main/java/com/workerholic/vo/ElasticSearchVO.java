package com.workerholic.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ElasticSearchVO {

	private Date startDatetime;
    private Date endDatetime;

    private Integer pageSize = 0;
    private Integer fromIndex = 0;

    private String[] includes = {};
    private String[] excludes = {};

    private String indicName;

    private String analyticsInterval;
    private String analyticsLimitOrder = "top";
    private int analyticsLimitSize = 5;
    
}
