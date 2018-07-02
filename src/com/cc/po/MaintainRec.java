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

//保养记录
@Entity
@Table(name="maintain_record")
public class MaintainRec implements Serializable{
	private Integer id;
	private Car car;//多对一
	private Dictionary mainType;//多对一
	private Date mainDate=new Date(System.currentTimeMillis());
	private Double mainAmount;
	private CurrentUnit mainUnit;//多对一
	private Double mainMil=0d;
	private String mainContent;
	private Date nextDate;
	private Double nextMil;
	private Driver operator;//多对一
	private String remarks;
	
	//分页专用
	private int page;
	private int rows;
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_MAINTAIN_RECORD_ID")
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="main_type")
	public Dictionary getMainType() {
		return mainType;
	}
	public void setMainType(Dictionary mainType) {
		this.mainType = mainType;
	}
	@Column(name="main_date")
	public Date getMainDate() {
		return mainDate;
	}
	public void setMainDate(Date mainDate) {
		this.mainDate = mainDate;
	}
	@Column(name="main_amount")
	public Double getMainAmount() {
		return mainAmount;
	}
	public void setMainAmount(Double mainAmount) {
		this.mainAmount = mainAmount;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="main_unit")
	public CurrentUnit getMainUnit() {
		return mainUnit;
	}
	public void setMainUnit(CurrentUnit mainUnit) {
		this.mainUnit = mainUnit;
	}
	@Column(name="MAIN_MIL")
	public Double getMainMil() {
		return mainMil;
	}
	public void setMainMil(Double mainMil) {
		this.mainMil = mainMil;
	}
	@Column(name="main_content")
	public String getMainContent() {
		return mainContent;
	}
	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}
	@Column(name="next_date")
	public Date getNextDate() {
		return nextDate;
	}
	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}
	@Column(name="NEXT_MIL")
	public Double getNextMil() {
		return nextMil;
	}
	public void setNextMil(Double nextMil) {
		this.nextMil = nextMil;
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
