package com.cc.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="VIEWCOSTDISTRIBUTION")
public class ViewCostDistribution implements Serializable{
	private Integer id;
	private Integer cid;
	private String carNo;
	private Double carCost;
	private String text;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@Column(name="car_no")
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name="car_cost")
	public Double getCarCost() {
		return carCost;
	}
	public void setCarCost(Double carCost) {
		this.carCost = carCost;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
