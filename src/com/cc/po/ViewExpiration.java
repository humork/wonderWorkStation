package com.cc.po;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//单个车辆费用分布视图
@Entity
@Table(name="VIEWEXPIRATION")
public class ViewExpiration implements Serializable{
	private Integer id;
	private String carNo;
	private Date exDate;
	private String text;
	
	//分页专用
	private int page;
	private int rows;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="CAR_NO")
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public Date getExDate() {
		return exDate;
	}
	public void setExDate(Date exDate) {
		this.exDate = exDate;
	} 
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	
	
}
