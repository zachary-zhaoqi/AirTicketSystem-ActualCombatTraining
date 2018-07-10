package com.softfz.model;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable{
	/**
	 * 分页数据模型
	 */
	private static final long serialVersionUID = -59432160528478346L;
	public int DEFAULT_PAGESIZE = 5;//默认每页显示条目
	private int currentPage = 1; //当前页
	private int pageSize = DEFAULT_PAGESIZE;//每页显示条数
	private int allCount;//总记录数
	private List<T> result;//存放分页表格需要显示的记录集合
	/**
	 * 通过总记录数和每页显示条目计算总页数
	 * @return
	 */
	public int getAllPage(){
		return (this.allCount - 1) / this.pageSize + 1;
	}	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	
	
}
