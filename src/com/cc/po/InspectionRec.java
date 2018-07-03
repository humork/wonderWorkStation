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

//年检记录
@Entity
@Table(name="INSPECTION_RECORD")
public class InspectionRec implements Serializable{
	private Integer id;
	private Car car;//多对一
	private String insNo;//年检编号
	private Date insDate;//年检日期
	private Double insAmount;
	private CurrentUnit vao;//vehicle administrative office缩写。多对一
	private Date expireDate;
	private Driver operator;//多对一
	private String remarks;
	
	//分页专用
	private int page;
	private int rows;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="seq_inspection_record_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="car_id")
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
	@Column(name="ins_amount")
	public Double getInsAmount() {
		return insAmount;
	}
	public void setInsAmount(Double insAmount) {
		this.insAmount = insAmount;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vao_id")
	public CurrentUnit getVao() {
		return vao;
	}
	public void setVao(CurrentUnit vao) {
		this.vao = vao;
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
	//获取行
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
}
