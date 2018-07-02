package com.cc.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

//字典表实体
@Entity
@Table(name="DICTIONARY")
public class Dictionary implements Serializable{
	private Integer id=-1;//主键
	private Dictionary dic;//自身之间的多对一对应关系
	private String text;//描述
	private Integer lev;//级别
	private Integer isdisable=1;//是否停用	1是使用，0是禁止
	private boolean selected=false;
	
	//传值专用，不做数据映射
	private Integer pid=-1;
	
	//分页专用
	private int page;
	private int rows;	
	
	public Integer getLev() {
		return lev;
	}
	public void setLev(Integer lev) {
		this.lev = lev;
	}
	@Transient
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Transient
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Transient
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="seq_dictionary_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	public Dictionary getDic() {
		return dic;
	}
	public void setDic(Dictionary dic) {
		this.dic = dic;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getIsdisable() {
		return isdisable;
	}
	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
	}
	@Transient
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
