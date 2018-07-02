package com.cc.po;

//柱状图数据结构
public class ReportColumn<T> {
	private String[] x;
	private String name;
	private T[] y;
	
	public String[] getX() {
		return x;
	}
	public void setX(String[] x) {
		this.x = x;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T[] getY() {
		return y;
	}
	public void setY(T[] y) {
		this.y = y;
	}
	
	
}
