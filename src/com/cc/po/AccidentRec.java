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

//事故记录表
@Entity
@Table(name="ACCIDENT_RECORD")
public class AccidentRec implements Serializable{
	private Integer id;
	private Car car;	//多对一
	private Driver driver; //多对一
	private Timestamp accDate;
	private String accPlace;
	private String accExplain;//事故说明
	private String weSituation;//我方情况
	private String otherSituation;//对方情况
	private String result;//处理结果
	private Double weAmount;//我方承担金额
	private Double otherAmount;//对方承担金额
	private Double insAmount;//保险承担金额	
	private String remarks;
	
	//分页专用
	private int page;
	private int rows;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="seq_accident_record_id")
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driver_id")
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	@Column(name="acc_date")
	public Timestamp getAccDate() {
		return accDate;
	}
	public void setAccDate(Timestamp accDate) {
		this.accDate = accDate;
	}
	@Column(name="acc_place")
	public String getAccPlace() {
		return accPlace;
	}
	public void setAccPlace(String accPlace) {
		this.accPlace = accPlace;
	}
	@Column(name="acc_explain")
	public String getAccExplain() {
		return accExplain;
	}
	public void setAccExplain(String accExplain) {
		this.accExplain = accExplain;
	}
	@Column(name="we_situation")
	public String getWeSituation() {
		return weSituation;
	}
	public void setWeSituation(String weSituation) {
		this.weSituation = weSituation;
	}
	@Column(name="other_situation")
	public String getOtherSituation() {
		return otherSituation;
	}
	public void setOtherSituation(String otherSituation) {
		this.otherSituation = otherSituation;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="we_amount")
	public Double getWeAmount() {
		return weAmount;
	}
	public void setWeAmount(Double weAmount) {
		this.weAmount = weAmount;
	}
	@Column(name="other_amount")
	public Double getOtherAmount() {
		return otherAmount;
	}
	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}
	@Column(name="ins_amount")
	public Double getInsAmount() {
		return insAmount;
	}
	public void setInsAmount(Double insAmount) {
		this.insAmount = insAmount;
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
