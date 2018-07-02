package com.cc.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ViewCostContrast")
public class ViewCostContrast implements Serializable{
	private Integer id;
	private String carNo;
	private Double carCost;
	
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
	@Column(name="CAR_COST")
	public Double getCarCost() {
		return carCost;
	}
	public void setCarCost(Double carCost) {
		this.carCost = carCost;
	}
	
	
	
}
