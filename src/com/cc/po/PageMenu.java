package com.cc.po;

import java.util.Map;

//页面实体。（平滑树菜单需要的数据类型）
public class PageMenu {
	private String id;//主键
	private String pid;//该子节点的父节点
	private String text;//文本
	private Map<String,String> attributes;//自定义属性
	private String state;
	private boolean checked=false;//是否选中
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
