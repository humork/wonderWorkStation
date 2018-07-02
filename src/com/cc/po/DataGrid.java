package com.cc.po;

import java.util.ArrayList;
import java.util.List;
//页面容器类
public class DataGrid<T> {
	private Long total=0L;//总数
	private List<T> rows=new ArrayList<T>();
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
