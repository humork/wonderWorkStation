package com.cc.po;

import java.io.Serializable;
import java.sql.Timestamp;

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

//出车记录表
@Entity
@Table(name="DRIVING_RECORD")
public class DrivingRec implements Serializable {
	private Integer id;
	private Car car;//多对一
	private Dictionary dept;//多对一
	private Timestamp begin_time;	//出车时间
	private Timestamp pre_backtime;  //预计回车时间
	private Timestamp backtime;		//回车时间
	private String personnel;
	private Driver driver;//多对一
	private String destination;
	private String reason;
	private Double start_mil;//开始里程
	private Double return_mil;//结束里程
	private Double this_mil;//本次里程
	private String park_place;
	private String remarks;
	private Users creater;//多对一
	
	//分页专用
	private int page;
	private int rows;
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_DRIVING_RECORD_ID")
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
	@JoinColumn(name="DEPT_ID")
	public Dictionary getDept() {
		return dept;
	}
	public void setDept(Dictionary dept) {
		this.dept = dept;
	}	
	public Timestamp getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Timestamp begin_time) {
		this.begin_time = begin_time;
	}
	public Timestamp getPre_backtime() {
		return pre_backtime;
	}
	public void setPre_backtime(Timestamp pre_backtime) {
		this.pre_backtime = pre_backtime;
	}
	public Timestamp getBacktime() {
		return backtime;
	}
	public void setBacktime(Timestamp backtime) {
		this.backtime = backtime;
	}
	public String getPersonnel() {
		return personnel;
	}
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DRIVER_ID")
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Double getStart_mil() {
		return start_mil;
	}
	public void setStart_mil(Double start_mil) {
		this.start_mil = start_mil;
	}
	public Double getReturn_mil() {
		return return_mil;
	}
	public void setReturn_mil(Double return_mil) {
		this.return_mil = return_mil;
	}
	public Double getThis_mil() {
		return this_mil;
	}
	public void setThis_mil(Double this_mil) {
		this.this_mil = this_mil;
	}
	public String getPark_place() {
		return park_place;
	}
	public void setPark_place(String park_place) {
		this.park_place = park_place;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CREATE_UID")
	public Users getCreater() {
		return creater;
	}
	public void setCreater(Users creater) {
		this.creater = creater;
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
