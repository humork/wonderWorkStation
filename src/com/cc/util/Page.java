package com.cc.util;

//分页工具类[适用于hibernate]
public class Page {
	private int pageIndex=1;
	private int begin=0;
	private int pageSize=10;
	
	public Page(int pageIndex, int pageSize) {		
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		
		this.begin=(pageIndex-1)*pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
