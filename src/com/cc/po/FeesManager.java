package com.cc.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
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

//规费管理
@Entity
@Table(name="FEES_MANAGER")
public class FeesManager implements Serializable{
	private Integer id;
	private Car car;//多对一
	private String feesName;
	private Timestamp feesDate;
	private Double feesAmount;
	private CurrentUnit feesUnit;//多对一
	private Driver operator;//多对一
	private String remarks;
	
	//分页专用
	private int page;
	private int rows;
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_FEES_MANAGER_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CAR_ID")
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Column(name="fees_name")
	public String getFeesName() {
		return feesName;
	}
	public void setFeesName(String feesName) {
		this.feesName = feesName;
	}
	@Column(name="fees_date")
	public Timestamp getFeesDate() {
		return feesDate;
	}
	public void setFeesDate(Timestamp feesDate) {
		this.feesDate = feesDate;
	}
	@Column(name="fees_amount")
	public Double getFeesAmount() {
		return feesAmount;
	}
	public void setFeesAmount(Double feesAmount) {
		this.feesAmount = feesAmount;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FEES_UNIT")
	public CurrentUnit getFeesUnit() {
		return feesUnit;
	}
	public void setFeesUnit(CurrentUnit feesUnit) {
		this.feesUnit = feesUnit;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPERATOR")
	public Driver getOperator() {
		return operator;
	}
	public void setOperator(Driver operator) {
		this.operator = operator;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
