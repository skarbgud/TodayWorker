package com.workerholic.vo;

import java.util.Date;

public class ElasticSearchVO {

	private Date startDatetime;
    private Date endDatetime;

    private Integer pageSize = 0;
    private Integer fromIndex = 0;

    private String[] includes = {};
    private String[] excludes = {};

    private String indicName;

    private String analyticsInterval;
    public Date getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	public Date getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getFromIndex() {
		return fromIndex;
	}
	public void setFromIndex(Integer fromIndex) {
		this.fromIndex = fromIndex;
	}
	public String[] getIncludes() {
		return includes;
	}
	public void setIncludes(String[] includes) {
		this.includes = includes;
	}
	public String[] getExcludes() {
		return excludes;
	}
	public void setExcludes(String[] excludes) {
		this.excludes = excludes;
	}
	public String getIndicName() {
		return indicName;
	}
	public void setIndicName(String indicName) {
		this.indicName = indicName;
	}
	public String getAnalyticsInterval() {
		return analyticsInterval;
	}
	public void setAnalyticsInterval(String analyticsInterval) {
		this.analyticsInterval = analyticsInterval;
	}
	public String getAnalyticsLimitOrder() {
		return analyticsLimitOrder;
	}
	public void setAnalyticsLimitOrder(String analyticsLimitOrder) {
		this.analyticsLimitOrder = analyticsLimitOrder;
	}
	public int getAnalyticsLimitSize() {
		return analyticsLimitSize;
	}
	public void setAnalyticsLimitSize(int analyticsLimitSize) {
		this.analyticsLimitSize = analyticsLimitSize;
	}
	private String analyticsLimitOrder = "top";
    private int analyticsLimitSize = 5;
    
}
