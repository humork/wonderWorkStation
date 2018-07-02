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

//违章记录表
@Entity
@Table(name="PECCANCY_RECORD")
public class PeccancyRec implements Serializable {
	private Integer id;
	private Car car;//多对一
	private Dictionary option;//多对一
	private Timestamp pecDate;
	private Double fine;
	private Integer points;
	private Driver driver;//多对一
	private String place;
	private String remarks;
	
	//分页专用
	private int page;
	private int rows;
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="seq_peccancy_record_id")
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
	@JoinColumn(name="pec_option")
	public Dictionary getOption() {
		return option;
	}
	public void setOption(Dictionary option) {
		this.option = option;
	}
	@Column(name="PEC_DATE")
	public Timestamp getPecDate() {
		return pecDate;
	}
	public void setPecDate(Timestamp pecDate) {
		this.pecDate = pecDate;
	}
	@Column(name="FINE")
	public Double getFine() {
		return fine;
	}
	public void setFine(Double fine) {
		this.fine = fine;
	}	
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driver_id")
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	@Column(name="pec_place")
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
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
