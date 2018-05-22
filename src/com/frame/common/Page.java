package com.frame.common;

import javax.servlet.http.HttpServletRequest;

import com.yhl.f22.frame.utils.StringUtils;

public class Page {
	
	public static final int DEFAULT_PAGE_SIZE = 10; // 每页的记录数 默认为10
    
    private int pageNo = 1; // 当前页 默认为第一页
    
    private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数 默认为10
    
    private int totalCount = 0; // 总记录数据条数 默认为0
    
    private String sortName = ""; // 排序字段名称 默认为空
    
    private String sortOrder = "desc"; // 排序顺序
    
    public void executePage(String sort_name, String sort_order, HttpServletRequest request, int totalcount) {
    	if(StringUtils.isEmpty(sortName)){
    		sortName = sort_name;
    	}
    	if(StringUtils.isEmpty(sortOrder)){
    		sortOrder = sort_order;
    	}
        
        totalCount = totalcount;
        long totalPages = 0;
        if ((totalCount % pageSize) == 0) {
            totalPages = totalCount / pageSize;
        }
        else {
            totalPages = totalCount / pageSize + 1;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        else if (pageNo > totalPages) {
            pageNo = (int)totalPages;
        }
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
    
    
    
}
