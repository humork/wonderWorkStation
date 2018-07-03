package com.cc.po;

import java.io.Serializable;
import java.sql.Date;

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

//保险记录表
@Entity
@Table(name="insurance_record")
public class InsuranceRec implements Serializable{
	private Integer id;
	private Car car;//多对一
	private String insNo;//保险编号
	private Date insDate;//保险开始日期
	private Dictionary insType;//多对一
	private Double insAmount;
	private CurrentUnit insUnit;//多对一
	private Date expireDate;
	private Driver operator;//多对一
	private String remarks;
	
	//分页专用页数和总数
	private int page;
	private int rows;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="seq_insurance_record_id")
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
	@Column(name="ins_no")
	public String getInsNo() {
		return insNo;
	}
	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}
	@Column(name="ins_date")
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ins_type")
	public Dictionary getInsType() {
		return insType;
	}
	public void setInsType(Dictionary insType) {
		this.insType = insType;
	}
	@Column(name="ins_amount")
	public Double getInsAmount() {
		return insAmount;
	}
	public void setInsAmount(Double insAmount) {
		this.insAmount = insAmount;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ins_unit")
	public CurrentUnit getInsUnit() {
		return insUnit;
	}
	public void setInsUnit(CurrentUnit insUnit) {
		this.insUnit = insUnit;
	}
	@Column(name="expire_date")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="operator")
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
